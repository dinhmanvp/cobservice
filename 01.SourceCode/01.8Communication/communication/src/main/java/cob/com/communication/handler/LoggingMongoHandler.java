package cob.com.communication.handler;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import cob.com.communication.log.LogMongoTemplate;
import cob.com.communication.log.models.AddLogInfo;
import cob.com.communication.utils.ConfigUtility;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;

public class LoggingMongoHandler extends ChannelDuplexHandler {

	private final ConfigUtility configUtil;

	public static String logId;
	
	public static String getLogId() {
		return logId;
	}

	public LoggingMongoHandler(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SaveLog(logId, "READ", format(ctx, "READ", msg));
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logId = UUID.randomUUID().toString();
		SaveLog(logId, "REGISTERED", format(ctx, "READ"));
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		SaveLog(logId, "UNREGISTERED", format(ctx, "UNREGISTERED"));
		super.channelUnregistered(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		SaveLog(logId, "EXCEPTION", format(ctx, "EXCEPTION", cause));
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SaveLog(logId, "ACTIVE", format(ctx, "ACTIVE"));
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		SaveLog(logId, "INACTIVE", format(ctx, "INACTIVE"));
		super.channelInactive(ctx);
	}

	/**
	 * Formats an event and returns the formatted message.
	 *
	 * @param eventName the name of the event
	 */
	protected String format(ChannelHandlerContext ctx, String eventName) {
		String chStr = ctx.channel().toString();
		return new StringBuilder(chStr.length() + 1 + eventName.length()).append(chStr).append(' ').append(eventName)
				.toString();
	}

	/**
	 * Formats an event and returns the formatted message.
	 *
	 * @param eventName the name of the event
	 * @param arg       the argument of the event
	 */
	protected String format(ChannelHandlerContext ctx, String eventName, Object arg) {
		if (arg instanceof ByteBuf) {
			return formatByteBuf(ctx, eventName, (ByteBuf) arg);
		} else if (arg instanceof ByteBufHolder) {
			return formatByteBufHolder(ctx, eventName, (ByteBufHolder) arg);
		} else {
			return formatSimple(ctx, eventName, arg);
		}
	}

	/**
	 * Formats an event and returns the formatted message. This method is currently
	 * only used for formatting
	 * {@link ChannelOutboundHandler#connect(ChannelHandlerContext, SocketAddress, SocketAddress, ChannelPromise)}.
	 *
	 * @param eventName the name of the event
	 * @param firstArg  the first argument of the event
	 * @param secondArg the second argument of the event
	 */
	protected String format(ChannelHandlerContext ctx, String eventName, Object firstArg, Object secondArg) {
		if (secondArg == null) {
			return formatSimple(ctx, eventName, firstArg);
		}

		String chStr = ctx.channel().toString();
		String arg1Str = String.valueOf(firstArg);
		String arg2Str = secondArg.toString();
		StringBuilder buf = new StringBuilder(
				chStr.length() + 1 + eventName.length() + 2 + arg1Str.length() + 2 + arg2Str.length());
		buf.append(chStr).append(' ').append(eventName).append(": ").append(arg1Str).append(", ").append(arg2Str);
		return buf.toString();
	}

	/**
	 * Generates the default log message of the specified event whose argument is a
	 * {@link ByteBuf}.
	 */
	private static String formatByteBuf(ChannelHandlerContext ctx, String eventName, ByteBuf msg) {
		String chStr = ctx.channel().toString();
		int length = msg.readableBytes();
		if (length == 0) {
			StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 4);
			buf.append(chStr).append(' ').append(eventName).append(": 0B");
			return buf.toString();
		} else {
			int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
			StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 2 + 10 + 1 + 2 + rows * 80);

			buf.append(chStr).append(' ').append(eventName).append(": ").append(length).append('B').append(NEWLINE);
			appendPrettyHexDump(buf, msg);

			return buf.toString();
		}
	}

	/**
	 * Generates the default log message of the specified event whose argument is a
	 * {@link ByteBufHolder}.
	 */
	private static String formatByteBufHolder(ChannelHandlerContext ctx, String eventName, ByteBufHolder msg) {
		String chStr = ctx.channel().toString();
		String msgStr = msg.toString();
		ByteBuf content = msg.content();
		int length = content.readableBytes();
		if (length == 0) {
			StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 2 + msgStr.length() + 4);
			buf.append(chStr).append(' ').append(eventName).append(", ").append(msgStr).append(", 0B");
			return buf.toString();
		} else {
			int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
			StringBuilder buf = new StringBuilder(
					chStr.length() + 1 + eventName.length() + 2 + msgStr.length() + 2 + 10 + 1 + 2 + rows * 80);

			buf.append(chStr).append(' ').append(eventName).append(": ").append(msgStr).append(", ").append(length)
					.append('B').append(NEWLINE);
			appendPrettyHexDump(buf, content);

			return buf.toString();
		}
	}

	/**
	 * Generates the default log message of the specified event whose argument is an
	 * arbitrary object.
	 */
	private static String formatSimple(ChannelHandlerContext ctx, String eventName, Object msg) {
		String chStr = ctx.channel().toString();
		String msgStr = String.valueOf(msg);
		StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 2 + msgStr.length());
		return buf.append(chStr).append(' ').append(eventName).append(": ").append(msgStr).toString();
	}

	private void SaveLog(String logId, String method, String arguments) {
		AddLogInfo info = new AddLogInfo();
		info.set_dateTime(LocalDateTime.now().toString());
		info.set_sessionId(logId);
		info.set_methodName(method);
		info.set_className("");
		info.set_arguments(arguments);
		long lStart = System.currentTimeMillis();
		info.set_timeStart(lStart);
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String collectionName = configUtil.getProperty("spring.data.mongodb.collectionrefix") + timeStamp;
		LogMongoTemplate.logInfo(info, collectionName);
	}
}

package cob.com.communication.handler;

import java.net.URLDecoder;
import java.util.Date;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class RemoveConnectHandler extends ChannelDuplexHandler {
	private final String websocketPath;

	public RemoveConnectHandler(String websocketPath) {
		this.websocketPath = websocketPath;
	}

	@SuppressWarnings("all")
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			FullHttpRequest httpReq = (FullHttpRequest) msg;
			String uri = URLDecoder.decode(httpReq.uri());
			String[] uriComponents = uri.split("[?]");
//			if (uriComponents.length <=1 || !this.websocketPath.equals(uriComponents[0])) {
//				Channel channel = ctx.channel();
//				HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
//				ChannelFuture f = channel.writeAndFlush(res);
//				f.addListener(ChannelFutureListener.CLOSE);
//				httpReq.release();
//				return;
//			}

		}
		super.channelRead(ctx, msg);
	}
}
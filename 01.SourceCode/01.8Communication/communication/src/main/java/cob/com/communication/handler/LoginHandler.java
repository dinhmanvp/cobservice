package cob.com.communication.handler;

import java.util.Date;

import javax.transaction.Synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.communication.SocketIOServer;
import cob.com.communication.utils.ConfigUtility;
import cob.com.communication.utils.StringUtility;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

//@SuppressWarnings("all")
public abstract class LoginHandler extends ChannelInboundHandlerAdapter {

	private final String websocketPath;

	private final ConfigUtility configUtil;

	private final ClientsBox clientsBox;
	
	private static final Logger log = LoggerFactory.getLogger(LoginHandler.class);

//	private final GatewayClient gateClient;

//	private final Synchronization synchronization;

	public LoginHandler(String websocketPath, ConfigUtility configUtil,
			ClientsBox clientsBox/*
									 * , GatewayClient gateClient, Synchronization synchronization
									 */) {
		this.websocketPath = websocketPath;
		this.configUtil = configUtil;
		this.clientsBox = clientsBox;
//		this.gateClient = gateClient;
//		this.synchronization = synchronization;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// check login
		if (msg instanceof FullHttpRequest) {
			FullHttpRequest httpReq = (FullHttpRequest) msg;

//			QueryStringDecoder queryDecoder = new QueryStringDecoder(httpReq.uri());
//			String path = queryDecoder.path();
//			if (!this.websocketPath.equals(path)) {
//				Channel channel = ctx.channel();
//				HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
//				ChannelFuture f = channel.writeAndFlush(res);
//				f.addListener(ChannelFutureListener.CLOSE);
//				httpReq.release();
//				return;
//
//			} else {
//
//				Gson gson = new Gson();
//				JsonElement jElement = gson.toJsonTree(queryDecoder.parameters()).getAsJsonObject();
//
//				if (StringUtility.isEmpty(jElement)) {
//					// return
//					Channel channel = ctx.channel();
//					HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
//					ChannelFuture f = channel.writeAndFlush(res);
//					f.addListener(ChannelFutureListener.CLOSE);
//					httpReq.release();
//					return;
//				}
//				JsonObject jObject = jElement.getAsJsonObject();
//				// excuse
//				excuseLogin(ctx, httpReq, jObject);
//				// push message
//				pushMessage(ctx, httpReq, jObject);
//			}

			String url = "ws://" + httpReq.headers().get(HttpHeaderNames.HOST) + httpReq.uri();
			
			WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(url, null,
					true);
			WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(httpReq);
			if (handshaker == null) {
				WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
			} else {
				ChannelFuture f = handshaker.handshake(ctx.channel(), httpReq);
				f.addListener(new ChannelFutureListener() {

					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						// TODO Auto-generated method stub
						if (!future.isSuccess()) {
							return;
						}
						//ctx.channel().writeAndFlush(new TextWebSocketFrame("da ket noi"));
						log.info("connect to:" + url);						
					}					
//					@Override
//					public void operationComplete(ChannelFuture future) throws Exception {
//						if (!future.isSuccess()) {
//							// Can't handshake + future.cause()
//							logger.info("ket noi khong thanh cong");
//							return;
//						}
//						//connectClient(channel, uuid);
//						ctx.channel().writeAndFlush(new TextWebSocketFrame("da ket noi"));
//					}
				});
			}
			
			
		}
		super.channelRead(ctx, msg);
	}

	protected ClientsBox getClientsBox() {
		return clientsBox;
	}

	protected ConfigUtility getConfigUtil() {
		return configUtil;
	}

//	protected GatewayClient getGateClient() {
//		return gateClient;
//	}
//
//	protected Synchronization getSynchronization() {
//		return synchronization;
//	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	protected abstract void excuseLogin(ChannelHandlerContext ctx, FullHttpRequest httpReq, JsonObject jObject)
			throws Exception;

	protected abstract void pushMessage(ChannelHandlerContext ctx, FullHttpRequest httpReq, JsonObject jObject)
			throws Exception;

}
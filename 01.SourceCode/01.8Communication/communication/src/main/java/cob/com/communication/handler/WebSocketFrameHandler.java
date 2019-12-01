/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package cob.com.communication.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import cob.com.communication.utils.ConfigUtility;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public abstract class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

	private static final Logger log = LoggerFactory.getLogger(LoginHandler.class);
	
	private final ConfigUtility configUtil;

	private final ClientsBox clientsBox;

//	private final Synchronization synchronization;

	public WebSocketFrameHandler(ConfigUtility configUtil,
			ClientsBox clientsBox/* , Synchronization synchronization */) {
		this.configUtil = configUtil;
		this.clientsBox = clientsBox;
//		this.synchronization = synchronization;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {

		if (frame instanceof WebSocketFrame) {
			String data = ((TextWebSocketFrame) frame).text();
			ctx.channel().writeAndFlush(new TextWebSocketFrame("bot : " + data));
//			JsonParser parser = new JsonParser();
//			JsonElement jElement = parser.parse(data);
//			if (StringUtility.isEmpty(jElement) || !jElement.isJsonObject()) {
//				String msg = configUtil.getProperty("ecp.data.invalid.code") + ":"
//						+ configUtil.getProperty("ecp.data.invalid.msg");
//				ctx.channel().writeAndFlush(new TextWebSocketFrame(msg));
//				return;
//			}
//			JsonObject jObject = jElement.getAsJsonObject();
//			if (StringUtility.isEmpty(jObject.get(Parameter.TRANSATION_TYPE))
//					|| StringUtils.isEmpty(jObject.get(Parameter.TRANSATION_TYPE).getAsString())) {
//				String msg = configUtil.getProperty("ecp.data.invalid.code") + ":"
//						+ configUtil.getProperty("ecp.data.invalid.msg");
//				ctx.channel().writeAndFlush(new TextWebSocketFrame(msg));
//				return;
//			}
//			// excuse
//			excuse(ctx, jObject);
			log.info("sent to " + data);

		} else {
			// Unsupported WebSocketFrame
			log.info("sent to Unsupported WebSocketFrame");
			String msg = "Unsupported WebSocketFrame";
			ctx.channel().writeAndFlush(new TextWebSocketFrame(msg));
		}
	}

	public ConfigUtility getConfigUtil() {
		return configUtil;
	}

	public ClientsBox getClientsBox() {
		return clientsBox;
	}

//	public Synchronization getSynchronization() {
//		return synchronization;
//	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelUnregistered()} to forward to
	 * the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
	 *
	 * Sub-classes may override this method to change behavior. remove client from
	 * clients list online
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

		ClientHead client = clientsBox.get(ctx.channel());
		clientsBox.removeClient(client.getSessionId());
		clientsBox.remove(ctx.channel());
		// set user ofline to db
//		synchronization.updateUserOffline(new BigDecimal(client.getSessionId()));
		ctx.close();
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
	 * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	 *
	 * Sub-classes may override this method to change behavior. remove client from
	 * clients list online
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	protected abstract void excuse(ChannelHandlerContext ctx, JsonObject jObject) throws Exception;
}
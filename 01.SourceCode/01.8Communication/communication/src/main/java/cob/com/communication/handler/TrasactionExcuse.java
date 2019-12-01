package cob.com.communication.handler;

import com.google.gson.JsonObject;

import cob.com.communication.utils.ConfigUtility;
import io.netty.channel.ChannelHandlerContext;

public class TrasactionExcuse extends WebSocketFrameHandler {

	public TrasactionExcuse(ConfigUtility configUtil, ClientsBox clientsBox/* , Synchronization synchronization */) {
		super(configUtil, clientsBox/* , synchronization */);
	}

	@Override
	protected void excuse(ChannelHandlerContext ctx, JsonObject jObject) throws Exception {
		//
		System.out.println("sf");
		ConfigUtility configUtil = getConfigUtil();
//		getSynchronization().excuse(ctx, jObject, configUtil, getClientsBox(), true);
	}
}
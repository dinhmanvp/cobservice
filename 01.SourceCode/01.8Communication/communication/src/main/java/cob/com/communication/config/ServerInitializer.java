package cob.com.communication.config;

import cob.com.communication.utils.ConfigUtility;
import cob.com.communication.handler.ClientsBox;
import cob.com.communication.handler.LoggingMongoHandler;
import cob.com.communication.handler.LoginExcuse;
import cob.com.communication.handler.RemoveConnectHandler;
import cob.com.communication.handler.TrasactionExcuse;
//import ecpay.vn.synchronization.ws.intercom.GatewayClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<Channel> {
	private final ChannelGroup group;

	private final ConfigUtility configUtil;

//	private final GatewayClient gatewayClient;

//	private final Synchronization synchronization;

	private static final String WEBSOCKET_PATH = "/message";

	private static ClientsBox clientsBox = new ClientsBox();

	public ServerInitializer(ChannelGroup group,
			ConfigUtility configUtil/*
									 * , GatewayClient gatewayClient, Synchronization synchronization
									 */) {
		this.group = group;
		this.configUtil = configUtil;
//		this.gatewayClient = gatewayClient;
//		this.synchronization = synchronization;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new LoggingMongoHandler(configUtil));
		pipeline.addLast(new RemoveConnectHandler(WEBSOCKET_PATH));
		pipeline.addLast(
				new LoginExcuse(WEBSOCKET_PATH, configUtil, clientsBox/* , gatewayClient , synchronization */));
		pipeline.addLast(new TrasactionExcuse(configUtil, clientsBox/* , synchronization */));
	}

	public ConfigUtility getConfigUtil() {
		return configUtil;
	}

	public ChannelGroup getGroup() {
		return group;
	}

	public static ClientsBox getClientsBox() {
		return clientsBox;
	}
}
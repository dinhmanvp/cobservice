package cob.com.communication;

import java.net.InetSocketAddress;

import javax.transaction.Synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cob.com.communication.config.Configuration;
import cob.com.communication.config.ServerInitializer;
import cob.com.communication.utils.ConfigUtility;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class SocketIOServer {

	private static final Logger log = LoggerFactory.getLogger(SocketIOServer.class);

	private final Configuration configuration;

	private final ConfigUtility configUtil;

//	private final GatewayClient gatewayClient;

//	private final Synchronization synchronization;

	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private EventLoopGroup bossGroup;
	private EventLoopGroup workGroup;

	public SocketIOServer(Configuration configuration,
			ConfigUtility configUtil/*
									 * , GatewayClient gatewayClient, Synchronization synchronization
									 */) {
		this.configuration = configuration;
		this.configUtil = configUtil;
//		this.gatewayClient = gatewayClient;
//		this.synchronization = synchronization;
	}

	/**
	 * Allows to get configuration provided during server creation. Further changes
	 * on this object not affect server.
	 *
	 * @return Configuration object
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	public ConfigUtility getConfigUtil() {
		return configUtil;
	}

	/**
	 * Start server
	 */
	public void start() {
		startAsync().syncUninterruptibly();
	}

	/**
	 * Stop server
	 */
	public void stop() {
		bossGroup.shutdownGracefully().syncUninterruptibly();
		workGroup.shutdownGracefully().syncUninterruptibly();
		log.info("SocketIO server stopped");
	}

	/**
	 * Start server asynchronously
	 * 
	 * @return void
	 */
	public Future<Void> startAsync() {

		initGroups();

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ServerInitializer(channelGroup, configUtil/* , gatewayClient, synchronization */))
				.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

		InetSocketAddress addr = new InetSocketAddress(configuration.getPort());
		if (configuration.getHostname() != null) {
			addr = new InetSocketAddress(configuration.getHostname(), configuration.getPort());
		}

		return bootstrap.bind(addr).addListener(new FutureListener<Void>() {
			@Override
			public void operationComplete(Future<Void> future) throws Exception {
				if (future.isSuccess()) {
					log.info("SocketIO server started at port: {}", configuration.getPort());
				} else {
					log.error("SocketIO server start failed at port: {}!", configuration.getPort());
				}
			}
		});
	}

	protected void initGroups() {
		bossGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup();
	}

}
package cob.com.communication.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class ClientHead {
	private final HandshakeData handshakeData;
	private final String sessionId;
	private final String channelId;
	private Channel channel;

	public ClientHead(String sessionId, String channelId, HandshakeData handshakeData) {
		this.sessionId = sessionId;
		this.handshakeData = handshakeData;
		this.channelId = channelId;
	}

	public HandshakeData getHandshakeData() {
		return handshakeData;
	}

	public String getSessionId() {
		return sessionId;
	}
	
	public String getChannelId() {
		return channelId;
	}
	
	public Channel getChannel() {
        return channel;
    }
	
	public Channel update(Channel channel) {
        Channel prevChannel = this.channel;
        this.channel = channel;
        return prevChannel;
    }
	
	public ChannelFuture send(TextWebSocketFrame frame) {
        if (this.channel == null) {
            return null;
        }
        return this.channel.writeAndFlush(frame);
    }
	
}
package cob.com.communication.handler;

import java.util.Map;

import io.netty.channel.Channel;
import io.netty.util.internal.PlatformDependent;

public class ClientsBox {

	private final Map<String, ClientHead> uuid2clients = PlatformDependent.newConcurrentHashMap();
	private final Map<Channel, ClientHead> channel2clients = PlatformDependent.newConcurrentHashMap();

	public HandshakeData getHandshakeData(String sessionId) {
		ClientHead client = uuid2clients.get(sessionId);
		if (client == null) {
			return null;
		}

		return client.getHandshakeData();
	}

	public void addClient(ClientHead clientHead) {
		//uuid2clients.put(clientHead.getSessionId() + clientHead.getTerminalId(), clientHead);
		uuid2clients.put(clientHead.getSessionId(), clientHead);
	}

	public void removeClient(String sessionId) {
		uuid2clients.remove(sessionId);
	}

	public ClientHead get(String sessionId) {
		return uuid2clients.get(sessionId);
	}

	public void add(Channel channel, ClientHead clientHead) {
		channel2clients.put(channel, clientHead);
	}

	public void remove(Channel channel) {
		channel2clients.remove(channel);
	}

	public ClientHead get(Channel channel) {
		return channel2clients.get(channel);
	}

}
package cob.com.communication.handler;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

public class ClientHeadSerializer implements StreamSerializer<ClientHead> {

	@Override
	public int getTypeId() {
		return 10;
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void write(ObjectDataOutput out, ClientHead object) throws IOException {	
		out.writeObject(object);
	}

	@Override
	public ClientHead read(ObjectDataInput in) throws IOException {
        return (ClientHead) in.readObject();
	}

}

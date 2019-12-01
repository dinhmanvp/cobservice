package cob.com.communication.models;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

public class UserOnlineInfoSerializer implements StreamSerializer<UserOnlineInfo> {

	@Override
	public int getTypeId() {
		return 100;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void write(ObjectDataOutput out, UserOnlineInfo object) throws IOException {
		out.writeObject(object);
	}

	@Override
	public UserOnlineInfo read(ObjectDataInput in) throws IOException {
		return (UserOnlineInfo) in.readObject();
	}
}
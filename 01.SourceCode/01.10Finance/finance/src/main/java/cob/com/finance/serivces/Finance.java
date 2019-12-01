package cob.com.finance.serivces;

import java.math.BigDecimal;
import java.util.List;

import com.google.gson.JsonObject;

import cob.com.finance.entities.TbTransaction;
import cob.com.finance.ws.models.PocketInfoForOther;
import cob.com.finance.ws.models.TbMypocketModel;

public interface Finance {
	List<TbMypocketModel> getByUserId(String userId);
	Integer excuseTransfer(TbTransaction entity, Integer method);
	Integer updateTransfer(TbTransaction entity);
	void sendOtp(Integer method, String username, String transactionId, String opt);
	List<PocketInfoForOther> getPoketInfoForOther(JsonObject input);
	Integer transferCOBCoin(String transactionTypeId, String toUserId, BigDecimal amount,
			String channelId, String fromUserId, String transMessage);
}

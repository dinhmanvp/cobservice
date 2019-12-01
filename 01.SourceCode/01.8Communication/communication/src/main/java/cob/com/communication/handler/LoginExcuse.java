package cob.com.communication.handler;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Synchronization;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hazelcast.core.IMap;

import cob.com.communication.log.LogMongoTemplate;
import cob.com.communication.log.models.AddLogDetailInfo;
import cob.com.communication.param.Parameter;
import cob.com.communication.utils.ConfigUtility;
import cob.com.communication.utils.StringUtility;
//import ecpay.vn.synchronization.entities.TbUserOnline;
//import ecpay.vn.synchronization.ws.intercom.GatewayClient;
//import ecpay.vn.synchronization.ws.models.SyncEventInfo;
//import ecpay.vn.synchronization.ws.validate.ValidateInput;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

public class LoginExcuse extends LoginHandler {

	public LoginExcuse(String websocketPath, ConfigUtility configUtil,
			ClientsBox clientsBox/*
									 * , GatewayClient gatewayClient, Synchronization synchronization
									 */) {
		super(websocketPath, configUtil, clientsBox/* , gatewayClient, synchronization */);
	}

	@SuppressWarnings("all")
	@Override
	protected void excuseLogin(ChannelHandlerContext ctx, FullHttpRequest httpReq, JsonObject jObject)
			throws Exception {
		// validate
		//ValidateInput validate = new ValidateInput(getConfigUtil());
//		boolean isValidate = true;// validate.LoginHandler(jObject);
//		if (isValidate) {
//			// return
//			SaveLogDetail("ValidateInput", "ValidateInput : " + isValidate);
//			//sendError(ctx, httpReq, 400);
//			ctx.close();
//			return;
//		}
//		// check channelSignature and walletSignature
//		GatewayClient client = getGateClient();
//		String json = jObject.toString().replaceAll("[\\[]|[\\]]", "");
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Object> inputReq = mapper.readValue(json, Map.class);
//		// ECGW close START
//		Object outpObject = client.execute(inputReq);
//		Gson gson = new Gson();
//		JsonElement jElement = gson.toJsonTree(outpObject).getAsJsonObject();
//		JsonObject clientData = jElement.getAsJsonObject();
//		if (StringUtility.isEmpty(clientData.get(Parameter.RESPONSE_CODE))
//				|| !"0000".equals(clientData.get(Parameter.RESPONSE_CODE).getAsString())) {
//			// return
//			String msgError = StringUtility.isEmpty(clientData.get(Parameter.RESPONSE_CODE)) ? "No Code"
//					: clientData.get(Parameter.RESPONSE_CODE).getAsString();
//			msgError += StringUtility.isEmpty(clientData.get(Parameter.RESPONSE_MESSAGE)) ? "No Message"
//					: clientData.get(Parameter.RESPONSE_MESSAGE).getAsString();
//			SaveLogDetail("CheckSignature", msgError);
//			ctx.close();
//			return;
//		}
//		// LOGIN SUCCESS
//		String channelId = clientData.get(Parameter.RESPONSE_DATA).getAsJsonObject().get(Parameter.CHANNEL_ID).getAsString();
//		//String channelId = "252";
//		// ECGW close END
//		// get value uuid
//		String uuid = inputReq.get(Parameter.WALLET_ID).toString();
//		String terminalId = inputReq.get(Parameter.TERMINAL_ID).toString();
//		Synchronization synchronization = getSynchronization();		
//		try {
//			// save user online to db
//			TbUserOnline addUserOnline = synchronization.UserOnlineCreate(inputReq, channelId);
//			if (Objects.isNull(addUserOnline) || StringUtils.isEmpty(addUserOnline.getSUserId())) {
//				SaveLogDetail("SaveTbUserOnline", "Null pointer");
//				ctx.close();
//				return;
//			}
//			// Do the Handshake to upgrade connection from HTTP to WebSocket protocol
//			handleHandshake(ctx, httpReq, uuid, channelId);
//
//		} catch (Exception e) {
//			// write log
//			SaveLogDetail("Exception", "errorMesage: " + e.getMessage());
//			// set user ofline to db
//			synchronization.updateUserOffline(new BigDecimal(uuid));
//			// close channel
//			ctx.close();
//			return;
//		}

	}

	/* Do the handshaking for WebSocket request */
	protected void handleHandshake(ChannelHandlerContext ctx, FullHttpRequest req, String uuid, String channelId) {
		Channel channel = ctx.channel();

		ClientsBox clientBox = getClientsBox();

		QueryStringDecoder queryDecoder = new QueryStringDecoder(req.uri());

		Map<String, List<String>> headers = new HashMap<String, List<String>>(req.headers().names().size());
		for (String name : req.headers().names()) {
			List<String> values = req.headers().getAll(name);
			headers.put(name, values);
		}

		HandshakeData data = new HandshakeData(req.headers(), queryDecoder.parameters(),
				(InetSocketAddress) channel.remoteAddress(), (InetSocketAddress) channel.localAddress(), req.uri());

		ClientHead client = new ClientHead(uuid, channelId, data);

		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketURL(req), null,
				true);
		WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			clientBox.addClient(client);
			ChannelFuture f = handshaker.handshake(channel, req);
			f.addListener(new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (!future.isSuccess()) {
						// Can't handshake + future.cause()
						return;
					}
					connectClient(channel, uuid);
				}
			});
		}
	}

	private void connectClient(final Channel channel, final String uuid) {
		ClientHead client = getClientsBox().get(uuid);
		client.update(channel);
		getClientsBox().add(channel, client);
	}

	@SuppressWarnings("all")
	protected String getWebSocketURL(FullHttpRequest req) {
		String url = "wss://" + req.headers().get(HttpHeaderNames.HOST) + req.uri();
		return url;
	}

	private void SaveLogDetail(String detailname, String detail) {
		ConfigUtility configUtil = getConfigUtil();
		AddLogDetailInfo testApiDetail = new AddLogDetailInfo();
		testApiDetail.setLogDetailId(LoggingMongoHandler.getLogId());
		testApiDetail.set_detailname(detailname);
		testApiDetail.set_detail(detail);
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String collectionName = configUtil.getProperty("spring.data.mongodb.collectiondetailrefix") + timeStamp;
		LogMongoTemplate.logInfo(testApiDetail, collectionName);
	}

	public void sendError(ChannelHandlerContext ctx, FullHttpRequest httpReq, int errorCode) {
		Channel channel = ctx.channel();
		HttpResponse res = null;
		switch (errorCode) {
		case 500:
			res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
			break;
		case 401:
			res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
		case 400:
			res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
		default:
			res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
			break;
		}
		ChannelFuture f = channel.writeAndFlush(res);
		f.addListener(ChannelFutureListener.CLOSE);
		httpReq.release();
	}

	@SuppressWarnings("all")
	@Override
	protected void pushMessage(ChannelHandlerContext ctx, FullHttpRequest httpReq, JsonObject jObject)
			throws Exception {
		
//		String json = jObject.toString().replaceAll("[\\[]|[\\]]", "");
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Object> inputReq = mapper.readValue(json, Map.class);
//		
//		Synchronization synchronization = getSynchronization();
//		// transaction for ecash message
//		String uuid = inputReq.get(Parameter.WALLET_ID).toString();
//		IMap<String, SyncEventInfo> iMapEcash = synchronization.GetEcashMessageFromHazelcast();
//		boolean isExist = iMapEcash.values().stream().anyMatch(p -> p.getToWallet().equals(uuid));
//		if (isExist) {
//			Iterator<SyncEventInfo> it = iMapEcash.values().stream().filter(p -> p.getToWallet().equals(uuid)).iterator();
//			while (it.hasNext()) {
//				SyncEventInfo item = it.next();
//				//sent
//				Gson gson = new Gson();				
//				synchronization.excuse(null, gson.toJsonTree(item).getAsJsonObject(), getConfigUtil(), getClientsBox(), false);
//			}
//		}		
		// TODO transaction for edong message
		//IMap<String, SyncEventInfo> iMapEdong = synchronization.GetEdongMessageFromHazelcast();
	}

}
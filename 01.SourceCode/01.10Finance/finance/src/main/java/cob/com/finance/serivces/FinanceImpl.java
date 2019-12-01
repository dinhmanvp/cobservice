package cob.com.finance.serivces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.gson.JsonObject;

import cob.com.finance.dao.TbMypocketRepository;
import cob.com.finance.dao.TbTransactionRepository;
import cob.com.finance.dao.TbUserRepository;
import cob.com.finance.entities.TbTransaction;
import cob.com.finance.entities.TbUser;
import cob.com.finance.intercom.NotfifyClient;
import cob.com.finance.param.Parameter;
import cob.com.finance.utils.StringUtility;
import cob.com.finance.ws.models.PocketInfoForOther;
import cob.com.finance.ws.models.PocketInfoForOtherEntities;
import cob.com.finance.ws.models.TbMypocketModel;
import cob.com.finance.ws.models.TransactionDataInfo;

@Component
public class FinanceImpl implements Finance {
	@Autowired
	TbMypocketRepository tbMypocketRepository;
	
	@Autowired
	TbTransactionRepository tbTransactionRepository;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	TbUserRepository tbUserRepository;

	@Autowired
	NotfifyClient notifyClient;
	
	private static final Logger log = LoggerFactory.getLogger(FinanceImpl.class);

	
	@SuppressWarnings("all")
	@Override
	public List<TbMypocketModel> getByUserId(String userId) {
		TbUser user = tbUserRepository.getUserByUserId(userId);
		if (user != null) {

			StoredProcedureQuery q = em.createStoredProcedureQuery("mdl_finance.getPoketInfo", TbMypocketModel.class)
					.registerStoredProcedureParameter("userId", String.class, ParameterMode.IN)
					.setParameter("userId", userId);
			List<TbMypocketModel> pockets = q.getResultList();

			for (TbMypocketModel tbMypocket : pockets) {
				try {
					String balance = StringUtility.decryptData(user.getsKey(), tbMypocket.getNbalance());
					String navailableBalance = StringUtility.decryptData(user.getsKey(),
							tbMypocket.getNavailablebalance());
					String nblockedBalance = StringUtility.decryptData(user.getsKey(), tbMypocket.getNblockedbalance());
					tbMypocket.setNavailablebalance(
							(new BigDecimal(navailableBalance).setScale(Parameter.SCALE)).toString());
					tbMypocket.setNbalance((new BigDecimal(balance).setScale(Parameter.SCALE)).toString());
					tbMypocket
							.setNblockedbalance((new BigDecimal(nblockedBalance).setScale(Parameter.SCALE)).toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new ArrayList<TbMypocketModel>();
				}
			}
			return pockets;
		}
		return new ArrayList<TbMypocketModel>();
	}

	private TransactionDataInfo checkTransaction(String currency, String fromUserId, String toUserId, BigDecimal amount) {
		try {
			StoredProcedureQuery query = em
					.createStoredProcedureQuery("mdl_finance.gettransactioninfo", TransactionDataInfo.class)
					.registerStoredProcedureParameter("transferCoin", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("fromUserId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("toUserId", String.class, ParameterMode.IN)
					.setParameter("transferCoin", currency)
					.setParameter("fromUserId", fromUserId)
					.setParameter("toUserId", toUserId);
			TransactionDataInfo info = (TransactionDataInfo) query.getSingleResult();
			em.clear();
			String sAvailableBalance = StringUtility.decryptData(info.getSkeyfrom(), info.getNavailablebalancefrom());
			BigDecimal nAvailableBalance = new BigDecimal(sAvailableBalance);
			BigDecimal limitBalance = new BigDecimal(info.getLimitbalance());
			if (nAvailableBalance.subtract(amount).compareTo(limitBalance) != -1) {
				info.setNavailablebalancefrom(nAvailableBalance.toString());
				info.setNbalancefrom(StringUtility.decryptData(info.getSkeyfrom(), info.getNbalancefrom()));
				info.setNblockedbalancefrom(StringUtility.decryptData(info.getSkeyfrom(), info.getNblockedbalancefrom()));
				info.setNavailablebalanceto(StringUtility.decryptData(info.getSkeyto(), info.getNavailablebalanceto()));
				info.setNbalanceto(StringUtility.decryptData(info.getSkeyto(), info.getNbalanceto()));
				info.setNblockedbalanceto(StringUtility.decryptData(info.getSkeyto(), info.getNblockedbalanceto()));
				return info;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public Integer excuseTransfer(TbTransaction entity, Integer method) {
		if (entity.getSTransactionTypeId().equals(Parameter.PAY_BUYING_SERVICE)) {
			TransactionDataInfo isAccept = checkTransaction(entity.getSCurrencyId(), entity.getSFromUserId(), entity.getSToUserId(), entity.getNAmount());
			if (isAccept != null) {
				try {
					String otp = StringUtility.OTP(6);
					entity.setNOtpAuth(otp);
					tbTransactionRepository.save(entity);
					sendOtp(method, isAccept.getSusernamefrom(), entity.getSTransactionId(), otp);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					return 2;
				}
			} else {
				return 0;
			}
		}
		return -1;
	}

	void sendEmail(Object object) {
		notifyClient.sendEmail(object);
	}

	void sendNotify(Object object) {
		notifyClient.sendNotify(object);
	}

	@Override
	public void sendOtp(Integer method, String username, String transactionId, String opt) {
		Map<Object, Object> object = new HashMap<Object, Object>();
		object.put(Parameter.USERNAME, username);
		object.put(Parameter.TEMPLATE_NAME, Parameter.OTP_TEMPLATE);
		object.put(Parameter.OTP_PARAMETER, opt);
		Map<Object, Object> data = new HashMap<Object, Object>();
		data.put(Parameter.TRANSACTION_ID, transactionId);
		object.put(Parameter.PARAMS, data);
		if (method.equals(Parameter.EMAIL_METHOD)) {
			sendEmail(object);
		} else {
			sendNotify(object);
		}
	}

	@Override
	public Integer updateTransfer(TbTransaction entity) {
		try {
			TbTransaction inDatabase = tbTransactionRepository.getByTransactionId(entity.getSTransactionId());
			if (inDatabase != null) {
				if (entity.getSTransactionId().equals(inDatabase.getSTransactionId())
						&& entity.getNId().equals(inDatabase.getNId())) {
					tbTransactionRepository.save(entity);
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@SuppressWarnings("all")
	@Override
	public List<PocketInfoForOther> getPoketInfoForOther(JsonObject input) {

		List<PocketInfoForOther> result = new ArrayList<PocketInfoForOther>();

		String userId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.USER_ID)))
			userId = input.get(Parameter.USER_ID).getAsString();

		String currencyId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.S_CURRENCY_ID)))
			currencyId = input.get(Parameter.S_CURRENCY_ID).getAsString();

		String phone = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.PHONE)))
			phone = input.get(Parameter.PHONE).getAsString();

		String email = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.EMAIL)))
			email = input.get(Parameter.EMAIL).getAsString();

		StoredProcedureQuery query = em
				.createStoredProcedureQuery("mdl_finance.getPoketInfoForOther", PocketInfoForOtherEntities.class)
				.registerStoredProcedureParameter("userId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("phone", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("currencyId", String.class, ParameterMode.IN)
				.setParameter("userId", userId)
				.setParameter("phone", phone)
				.setParameter("email", email)
				.setParameter("currencyId", currencyId);

		List<PocketInfoForOtherEntities> outdata = query.getResultList();

		for (PocketInfoForOtherEntities item : outdata) {

			PocketInfoForOther element = new PocketInfoForOther();
			element.setnId(item.getnId());
			element.setsCurrencyId(item.getsCurrencyId());
			element.setsCurrencyImage(item.getsCurrencyImage());
			element.setsCurrencySymbol(item.getsCurrencySymbol());
			element.setsPocketId(item.getsPocketId());
			element.setsUserId(item.getsUserId());
			// nBalance
			String balance;
			try {
				balance = StringUtility.decryptData(item.getsKey(), item.getnBalance());
				element.setnBalance((new BigDecimal(balance).setScale(Parameter.SCALE)).toString());
				// nAvailableBalance
				String navailableBalance = StringUtility.decryptData(item.getsKey(), item.getnAvailableBalance());
				element.setnAvailableBalance((new BigDecimal(navailableBalance).setScale(Parameter.SCALE)).toString());
				// nBlockedBalance
				String nblockedBalance = StringUtility.decryptData(item.getsKey(), item.getnBlockedBalance());
				element.setnBlockedBalance((new BigDecimal(nblockedBalance).setScale(Parameter.SCALE)).toString());
			} catch (Exception e) {
				log.info("ERROR:" + e.getMessage());
				e.printStackTrace();
			}			
			//
			result.add(element);
		}

		return result;
	}

	
//	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
//			Exception.class, ParseException.class })
	@Override
	public Integer transferCOBCoin(String transactionTypeId, String toUserId, BigDecimal amount,
			String channelId, String fromUserId, String transMessage) {		
		if (transactionTypeId.equals(Parameter.TRANFER_TO_USER)) {
			
			TransactionDataInfo info = checkTransaction(Parameter.COB_CURRENCY, fromUserId, toUserId, amount);
			if (info != null) {
				try {
						String skeyFrom = info.getSkeyfrom();
						BigDecimal 	availableFrom = new BigDecimal(info.getNavailablebalancefrom());
						BigDecimal balanceFrom = new BigDecimal(info.getNbalancefrom());
						
						String skeyTo = info.getSkeyto();
						BigDecimal 	availableTo = new BigDecimal(info.getNavailablebalanceto());
						BigDecimal balanceTo = new BigDecimal(info.getNbalanceto());
						
						availableFrom = availableFrom.subtract(amount);
						balanceFrom = balanceFrom.subtract(amount);
						availableTo = availableTo.add(amount);
						balanceTo = balanceTo.add(amount);

						String fromPocketId = info.getSpocketidfrom();
						String avaiFrom = StringUtility.encryptData(skeyFrom, availableFrom.toString());
						String balFrom = StringUtility.encryptData(skeyFrom, balanceFrom.toString());
						
						String toPocketId = info.getNpocketidto();
						String avaiTo = StringUtility.encryptData(skeyTo, availableTo.toString());
						String balTo = StringUtility.encryptData(skeyTo, balanceTo.toString());
						
						String sTransactionId = UUID.randomUUID().toString();
						
						TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
						transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
						transactionTemplate.execute(new TransactionCallbackWithoutResult() {					        
							@Override
							protected void doInTransactionWithoutResult(TransactionStatus status) {
								 em.createNativeQuery("CALL mdl_finance.saveTransaction(:fromUserId, :fromPocketId, :avaiFrom, :balFrom, :toUserId, :toPocketId, :avaiTo, :balTo, :sTransactionId, :amount, :currencyId, :transactionType, :massage, :channelId)")
						            .setParameter("fromUserId", fromUserId)
									.setParameter("fromPocketId", fromPocketId)
									.setParameter("avaiFrom", avaiFrom)
									.setParameter("balFrom", balFrom)
									.setParameter("toUserId", toUserId)
									.setParameter("toPocketId", toPocketId)
									.setParameter("avaiTo", avaiTo)
									.setParameter("balTo", balTo)
									.setParameter("sTransactionId", sTransactionId)
									.setParameter("amount", amount)
									.setParameter("currencyId", Parameter.COB_CURRENCY)
									.setParameter("transactionType", transactionTypeId)
									.setParameter("massage", transMessage)
									.setParameter("channelId", channelId)
						            .executeUpdate();
							}
					    });

					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return 2;
				}
				finally {
					
				}
			} else {
				return 0;
			}
		}
		return -1;
	}
	
}

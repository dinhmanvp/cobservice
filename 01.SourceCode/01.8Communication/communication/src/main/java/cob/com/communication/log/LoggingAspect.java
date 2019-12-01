package cob.com.communication.log;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cob.com.communication.log.models.AddLogDetailInfo;
import cob.com.communication.log.models.AddLogInfo;
import cob.com.communication.utils.ConfigUtility;

/**
 * @author ldman 2019/07/06
 * Aspect write log for module service, catch all REST API
 */
@Aspect
@Component
public class LoggingAspect {
    
	@Autowired
	private ConfigUtility configUtil;
	
	public static String logId;
	
	@Pointcut("execution(* cob.com.communication.rest.*.*(..))")
	protected void restApi() {
	}
	
    @Around("restApi()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		AddLogInfo info = new AddLogInfo();
		info.set_dateTime( LocalDateTime.now().toString());
		logId = UUID.randomUUID().toString();
		info.set_sessionId(logId);
		info.set_methodName(joinPoint.getSignature().getName());
		info.set_className(joinPoint.getSignature().getDeclaringTypeName());
		info.set_arguments(Arrays.toString(joinPoint.getArgs()));
		long lStart = System.currentTimeMillis();
		info.set_timeStart(lStart);
		
        Object proceed = joinPoint.proceed();
        
        long lEnd = System.currentTimeMillis();
        info.set_timeEnd(lEnd);
        info.set_timeTotal(lEnd - info.get_timeStart());
        info.set_argumentsReturn(proceed);
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String collectionName = configUtil.getProperty("spring.data.mongodb.collectionrefix") + timeStamp;
		
		LogMongoTemplate.logInfo(info, collectionName);
		
		return proceed;
	}
    
    @AfterThrowing(throwing = "ex", pointcut = "restApi()")
    public void logAfterThrowingAllMethods(Throwable ex) throws Throwable  {
    	AddLogDetailInfo testApiDetail = new AddLogDetailInfo();
		testApiDetail.setLogDetailId(LoggingAspect.logId);
		testApiDetail.set_detailname("Error:");
		testApiDetail.set_detail(ex.getMessage());
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String collectionName = configUtil.getProperty("spring.data.mongodb.collectiondetailrefix") + timeStamp;
		LogMongoTemplate.logInfo(testApiDetail, collectionName);
    }
}

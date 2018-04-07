package pers.wsf.study.httpclient.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class QueryAspect {

	private Logger logger = LoggerFactory.getLogger(QueryAspect.class);

	@Around("execution(* pers.wsf.study.httpclient.service.QueryService.*(..))")
	public void arroundQuery(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("before query");
		joinPoint.proceed();
		logger.info("after query");
	}
}

package util;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {
	
	@Inject
	Logger logger;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		
		logger.info("LoggingInterceptor - before EJB method invoke: "
		        + ctx.getMethod().getName());
		
		     Object result = ctx.proceed();

		     logger.info("LoggingInterceptor - after EJB method invoke: " 
		        + ctx.getMethod().getName());

		     return result;
	}
}

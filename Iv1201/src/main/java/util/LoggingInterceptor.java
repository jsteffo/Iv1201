package util;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * The Interceptor used to log information about methods being invoced
 * @author stefan
 *
 */
public class LoggingInterceptor {
	
	@Inject
	Logger logger;
	/**
	 * The method to be invoked on before and after any method being invoced with LoggingInterface
	 * defined
	 * @param ctx - The invocationContext with information in regards to the method being invoced
	 * @return
	 * @throws Exception
	 */
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

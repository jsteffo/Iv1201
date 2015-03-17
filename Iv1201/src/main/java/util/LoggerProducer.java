package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
/**
 * Allows us to use @Inject annotation to initialize Logger using CDI
 * @author stefan
 *
 */
public class LoggerProducer {

	/**
	 * Called upon CDI trying to create a Logger.
	 * @param p - InjectionPoint
	 * @return The logger
	 */
	@Produces
	public Logger getLogger(InjectionPoint p) {

		Logger logger = Logger.getLogger(p.getClass().getCanonicalName());
		//logger.setUseParentHandlers(false);
		try {
			//logger.addHandler(new FileHandler("/home/stefan/testar.log", true));
			
			logger.setLevel(Level.INFO);
		} catch(SecurityException e){
			e.printStackTrace();
		} 
		return logger;
	}
}

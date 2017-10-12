package com.flowedu.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author <a href="mailto:pyeonzi20c@challabros.com">안영수</a>
 * @since 2016. 03. 09.
 */
public class ShutdownListener implements ServletContextListener {

	final static Logger logger = LoggerFactory.getLogger(ShutdownListener.class);

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

		try {
			com.amazonaws.http.IdleConnectionReaper.shutdown();
			logger.debug("amazon idle connections shutdown...");
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
	}
}

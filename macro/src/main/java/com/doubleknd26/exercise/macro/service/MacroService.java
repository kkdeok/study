package com.doubleknd26.exercise.macro.service;

import com.doubleknd26.exercise.macro.MacroConfig;
import com.doubleknd26.exercise.macro.util.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class MacroService {
	protected static final Logger logger = LogManager.getLogger();
	
	protected MacroConfig.ServiceConfig config;
	protected WebDriverWrapper driver;
	protected long count; // # of try.
	
	public MacroService(MacroConfig.ServiceConfig config) {
		this.config = config;
		this.driver = new WebDriverWrapper(config.getUserAgent(), config.isHeadless());
		this.count = 0L;
	}

	protected abstract void login();

	protected abstract void runMacro();
	
	public void start() {
		try {
			login();
			runMacro();
		} catch (Exception e) {
			logger.info("exception occurred: " + e);
			stop();
		}
	}

	private void stop() {
		driver.quit();
	}
}

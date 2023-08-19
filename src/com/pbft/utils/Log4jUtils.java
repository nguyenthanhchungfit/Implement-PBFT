package com.pbft.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author chungnt
 * @version 1.0
 * @date 14/08/2023
 */
public class Log4jUtils {
	public static void buildLog4jRollingFile(String name) {
		ConfigurationBuilder<BuiltConfiguration> builder
				= ConfigurationBuilderFactory.newConfigurationBuilder();

		LayoutComponentBuilder layoutLog
				= builder.newLayout("PatternLayout");
		layoutLog.addAttribute("pattern", "%d %p %c{1.} [%t]: %m %ex%n");

		ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
				.addComponent(builder.newComponent("CronTriggeringPolicy")
						.addAttribute("schedule", "0 0 0 * * ?"))
				.addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
						.addAttribute("size", "100M"));
		AppenderComponentBuilder rollingFile
				= builder.newAppender(name, "RollingFile");
		String fileName = String.format("log/%s.log", name);
		String filePattern = "log/" + name + "-%d{MM-dd-yy-HH-mm-ss}.log.gz";
		rollingFile.addAttribute("fileName", fileName)
				.addAttribute("filePattern", filePattern)
				.addComponent(triggeringPolicies)
				.add(layoutLog);

		builder.add(rollingFile);

		LoggerComponentBuilder logger = builder.newLogger(name, Level.DEBUG);
		logger.add(builder.newAppenderRef(name));
		logger.addAttribute("additivity", false);

		builder.add(logger);
		Configurator.initialize(builder.build());
	}

	public static void buildLog4jRollingFileV2(String name) {
		LoggerContext ctx = (LoggerContext) LogManager.getContext();
		Configuration configuration = ctx.getConfiguration();

		AppenderRef ref = AppenderRef.createAppenderRef("RollingFile", null, null);
		AppenderRef[] refs = new AppenderRef[]{ref};
		LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.INFO, "org.apache.logging.log4j",
				"true", refs, null, configuration, null);

		boolean ignoreException = true;
		String fileName = String.format("log/%s.log", name);
		String filePattern = "log/" + name + "-%d{MM-dd-yy-HH-mm-ss}.log.gz";
		String layoutPattern = "%d %p %c{1.} [%t]: %m %ex%n";
		SizeBasedTriggeringPolicy policy = SizeBasedTriggeringPolicy.createPolicy("100M");
		DefaultRolloverStrategy strategy = new DefaultRolloverStrategy.Builder().withMax("5").build();
		PatternLayout layout = PatternLayout.newBuilder().withPattern(layoutPattern).build();
		RollingFileAppender appender = RollingFileAppender.createAppender(fileName, filePattern,
				"", "RollingFile", "false", "",
				"true", policy, strategy, layout, null,
				String.valueOf(ignoreException), "", "", configuration);

		appender.start();
		configuration.addAppender(appender);
		loggerConfig.addAppender(appender, null, null);
		configuration.addLogger(name, loggerConfig);
		ctx.updateLoggers(configuration);
	}

	private static void _preInitLog4j() {
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		File file = new File("conf/log4j2.xml");
		// this will force a reconfiguration
		context.setConfigLocation(file.toURI());
	}

	public static void buildListLog4jRollingFile(List<String> listNames) {
		ConfigurationBuilder<BuiltConfiguration> builder
				= ConfigurationBuilderFactory.newConfigurationBuilder();

		for (String name : listNames) {
			LayoutComponentBuilder layoutLog
					= builder.newLayout("PatternLayout");
			layoutLog.addAttribute("pattern", "%d %p %c{1.} [%t]: %m %ex%n");

			ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
					.addComponent(builder.newComponent("CronTriggeringPolicy")
							.addAttribute("schedule", "0 0 0 * * ?"))
					.addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
							.addAttribute("size", "100M"));
			AppenderComponentBuilder rollingFile
					= builder.newAppender(name, "RollingFile");
			String fileName = String.format("log/%s.log", name);
			String filePattern = "log/" + name + "-%d{MM-dd-yy-HH-mm-ss}.log.gz";
			rollingFile.addAttribute("fileName", fileName)
					.addAttribute("filePattern", filePattern)
					.addComponent(triggeringPolicies)
					.add(layoutLog);

			builder.add(rollingFile);

			LoggerComponentBuilder loggerComponentBuilder = builder.newLogger(name, Level.DEBUG);
			loggerComponentBuilder.add(builder.newAppenderRef(name));
			loggerComponentBuilder.addAttribute("additivity", false);

			builder.add(loggerComponentBuilder);
		}

		LayoutComponentBuilder layoutLog
				= builder.newLayout("PatternLayout");
		layoutLog.addAttribute("pattern", "%d %p %c{1.} [%t]: %m %ex%n");
		AppenderComponentBuilder console
				= builder.newAppender("stdout", "Console")
				.add(layoutLog);
		builder.add(console);

		RootLoggerComponentBuilder rootLogger
				= builder.newRootLogger(Level.ERROR);
		rootLogger.add(builder.newAppenderRef("stdout"));
		rootLogger.addAttribute("additivity", true);
		builder.add(rootLogger);

		Configurator.initialize(builder.build());
	}

	public static void buildLog4jRollingFileTest(String name) {
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration config = ctx.getConfiguration();
		SizeBasedTriggeringPolicy policy = SizeBasedTriggeringPolicy.createPolicy("100M");
		DefaultRolloverStrategy strategy = new DefaultRolloverStrategy.Builder().withMax("5").build();
		String layoutPattern = "%d %p %c{1.} [%t]: %m %ex%n";
		PatternLayout layout = PatternLayout.newBuilder().withPattern(layoutPattern).build();
		String fileName = String.format("log/%s.log", name);
		String filePattern = "log/" + name + "-%d{MM-dd-yy-HH-mm-ss}.log.gz";
		Appender appender = RollingFileAppender.createAppender(fileName, filePattern, "true", name, "true", "8192",
				"true", policy, strategy, layout, null, "true", null, null, config);
		appender.start();
		config.addAppender(appender);
		AppenderRef ref = AppenderRef.createAppenderRef(name, Level.INFO, null);
		AppenderRef[] refs = new AppenderRef[]{ref};
		LoggerConfig loggerConfig = LoggerConfig.createLogger(true, Level.INFO, name,
				"true", refs, null, config, null);
		loggerConfig.addAppender(appender, Level.INFO, null);
		config.addLogger(name, loggerConfig);
		ctx.updateLoggers();
	}

	public static void main(String[] args) {
//		_preInitLog4j();


//		List<String> log4jName = Arrays.asList("node-1", "node-2");
//		buildListLog4jRollingFile(log4jName);

		String name1 = "node-1";
//		buildLog4jRollingFileTest(name1);
//		buildLog4jRollingFileV2(name1);
//		buildLog4jRollingFile(name1);
		Logger customLogger1 = LogManager.getLogger(name1);

//		String name2 = "node-2";
//		buildLog4jRollingFileV2(name2);
//		buildLog4jRollingFile(name2);
//		Logger customLogger2 = LogManager.getLogger(name2);
//		_preInitLog4j();

		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 100; i++) {
					customLogger1.info("Hello World xxx" + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}).start();

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 0; i < 100; i++) {
//					customLogger2.info("Hello World " + i);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						throw new RuntimeException(e);
//					}
//				}
//			}
//		}).start();

		Logger logger = LogManager.getLogger(Log4jUtils.class);
		for (int i = 0; i <= 10000; i++) {
			System.out.println("main: " + i);
			logger.info("main: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

//		try {
//			Thread.sleep(1000000);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
	}
}

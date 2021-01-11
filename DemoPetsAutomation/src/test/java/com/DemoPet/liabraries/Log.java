package com.DemoPet.liabraries;

import org.apache.log4j.Logger;

final public class Log {

	private static final Logger log = Logger.getLogger(Log.class.getName());

	enum Level {

		Error, Warn, Fatal, Info, Debug

	}

	public static void logError(Class clazz, String msg) {

		log(Level.Error, clazz, msg, null);

	}

	public static void logWarn(Class clazz, String msg) {

		log(Level.Warn, clazz, msg, null);

	}

	public static void logFatal(Class clazz, String msg) {

		log(Level.Fatal, clazz, msg, null);

	}

	public static void logInfo(Class clazz, String msg) {

		log(Level.Info, clazz, msg, null);

	}

	public static void logDebug(Class clazz, String msg) {

		log(Level.Debug, clazz, msg, null);

	}

	// Need to create these methods, so that they can be called

	private static void log(Level level, Class clazz, String msg, Throwable throwable) {

		System.setProperty("org.freemarker.loggerLibrary", "none");

		String message = String.format("[%s] : %s", clazz, msg);

		switch (level) {

		case Info:

			log.info(message, throwable);

			break;

		case Warn:

			log.warn(message, throwable);

			break;

		case Error:

			log.error(message, throwable);

			break;

		case Fatal:

			log.fatal(message, throwable);

			break;

		default:

		case Debug:

			log.debug(message, throwable);

		}

	}

	// This is to print log for the beginning of the test case, as we usually run so

	// many test cases as a test suite

	public static void startTestCase(String sTestCaseName) {

		log.info("****************************************************************************************");

		log.info("****************************************************************************************");

		log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		log.info("****************************************************************************************");

		log.info("****************************************************************************************");

	}

	// This is to print log for the ending of the test case

	public static void endTestCase(String sTestCaseName) {

		log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");

		log.info("X");

		log.info("X");

		log.info("X");

		log.info("X");

	}

}

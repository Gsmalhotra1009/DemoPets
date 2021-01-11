package com.DemoPet.liabraries;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.log4j.PropertyConfigurator;

import com.DemoPet.Scenarios.DemoPetsScenarioTesting;
import com.DemoPet.appconfig.Config;

import org.apache.commons.configuration.PropertiesConfiguration;


public class Genericfunctions {
	static String jsonToString;
	

	public static String parsingjsonToString() throws Exception {
		String file = "src/main/resources/JsonFiles/pets.json";
		jsonToString = readFileAsString(file);
		Log.logInfo(Genericfunctions.class, "JSON is converted to String successfull!!" + jsonToString);
		return jsonToString;
	}

	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	public static void setup() {

		try {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_yyyy HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String currenTimeFormat = dtf.format(now);
			
			String loggerFileName = "DemoPetAutomation_" + currenTimeFormat;
			String loggerPropertyAbsolutePath = Config.vProjPath

					+ "\\src\\test\\java\\com\\DemoPet\\log4j\\Logger.properties";

			PropertiesConfiguration propertiesConfig = new PropertiesConfiguration(loggerPropertyAbsolutePath);
			propertiesConfig.setProperty("log4j.appender.fileAppender.File",
					(Config.vProjPath + "\\target\\TestLogs\\" + loggerFileName + ".log").replace("\\", File.separator));
			propertiesConfig.save();
			PropertyConfigurator.configure(loggerPropertyAbsolutePath);
			Log.logInfo(Genericfunctions.class, "Initiating Loggers Instance with file name: " + loggerFileName);
			

		} catch (Exception e) {

			Log.logError(Genericfunctions.class, e.getMessage());

		}

	}
}
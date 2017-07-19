package core;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.LogLevel;

/**
 * Created by mukeshpal on 08/06/17.
 */
public class Log
{

		static Logger logInstance = Logger.getLogger(Log.class.getName());
		public static String appendCharacter = "";

		public static void Message(String message, LogLevel logLevel)
		{
			if (!appendCharacter.equals("")) {
				message = appendCharacter + message;
			}
			switch (logLevel.toString()) {
				case "ERROR":
					logInstance.error(message);
					break;
				case "INFO":
					logInstance.info(message);
					break;
//				case "DEBUG":
//					logInstance.debug(message);
//					break;
			}
		}
}

package com.github.The414s.util;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class T4Formatter extends Formatter {

	@Override
	public String format(LogRecord log) {
		Level level = log.getLevel();
		int temp = log.getSourceClassName().lastIndexOf(".");
		String temp1 = log.getSourceClassName();
		String temp2 = temp1.substring(temp + 1, temp1.length());
		String f = "[" + temp2 + "]" + "[" + log.getSourceMethodName() + "]";
		if (level == Level.INFO) {
			return "[INFO]" + f + log.getMessage() + "\n";
		}
		if (level == Level.WARNING) {
			return "[WARNING]" + f + log.getMessage() + "\n";
		}
		if (level == Level.SEVERE) {
			return "[SEVERE]" + f + log.getMessage() + "\n";
		}
		return null;
	}

}

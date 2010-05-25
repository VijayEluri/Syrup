/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.syrup.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static final String SUCCESS = "successMessages";
	public static final String ERROR = "errorMessages";

	/**
	 * 
	 * @param message
	 * @param req
	 */
	@SuppressWarnings("unchecked")
	private static void save(String message, String messageKey,
			HttpServletRequest req) {

		List<String> msgs = (List<String>) req.getSession().getAttribute(
				messageKey);
		if (msgs == null) {
			msgs = new ArrayList<String>();
		}
		msgs.add(message);
		req.getSession().setAttribute(messageKey, msgs);
	}

	/**
	 * 
	 * @param message
	 * @param req
	 */
	public static void saveErrorMessage(String message, HttpServletRequest req) {
		save(message, ERROR, req);
	}

	public static void saveSuccessMessage(String message, HttpServletRequest req) {
		save(message, SUCCESS, req);

	}

	@SuppressWarnings("unchecked")
	public static void saveErrorMap(Map errorMap, HttpServletRequest req) {
		if (errorMap != null) {
			Iterator<String> iter = errorMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = (String) errorMap.get(key);
				save((key + " : " + value), ERROR, req);
			}
		}

	}

	/**
	 * 
	 * @param objectMap
	 * 
	 * @return
	 */
	public static String getJSON(Map<String, String> objectMap) {
		StringBuffer returnErrorMap = new StringBuffer();

		Iterator<String> errorIter = objectMap.keySet().iterator();
		while (errorIter.hasNext()) {
			String key = errorIter.next();
			String value = (String) objectMap.get(key);

			returnErrorMap.append("\"" + key + "\": \"" + value + "\"");
			if (errorIter.hasNext()) {

				returnErrorMap.append(",\n");
			}
		}

		String resultingJSON = "{ \"result\": { " + returnErrorMap.toString()
				+ "}}";

		return resultingJSON;
	}
	
	
}

package com.barunsw.day02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.Person;

public class StringTest {
	private static Logger LOGGER = LogManager.getLogger(StringTest.class);
	
	public static void main(String[] args) {
		String str = "abcd";
		String str2 = "abce";
		String str3 = new String("abcd");
		String str4 = new String("ABCD");
		String str5 = " \n\t    abcd   \n\t ";
		String str6 = "abcd abcd abcd abcd";
		String str7 = "abcd,abcd,abcd,abcd";
		
		String filePath = "/aaa/bbb/test.xls";
		
		// charAt
		LOGGER.debug("str.charAt(1):" + str.charAt(1) + ", str.charAt(2):" + str.charAt(2));
		LOGGER.debug(String.format("str.charAt(1):%s, str.charAt(2):%s", str.charAt(1), str.charAt(2)));
		
		// compareTo
		LOGGER.debug("str.compareTo(str2):" + str.compareTo(str2));
		LOGGER.debug("str2.compareTo(str):" + str2.compareTo(str));
		LOGGER.debug("str.compareTo(str):" + str.compareTo(str));
		
		if (str.equals(str3)) {
			LOGGER.debug("str.equals(str3) == True");
		}
		
		if (str.compareTo(str3) == 0) {
			LOGGER.debug("str.compareTo(str3) == 0");
		}
		
		if (str == str3) {
			LOGGER.debug("str == str3");
		}
		else {
			LOGGER.debug("str != str3");
		}
		
		if (str.equalsIgnoreCase(str4)) {
			LOGGER.debug("str.equals(str4) == True");
		}
		
		// concat(+)
		LOGGER.debug("str.concat(str4):" + str.concat(str4));
		
		// endsWith
		if (filePath.endsWith(".xls")) {
			LOGGER.debug("filePath.endsWith(\".xls\"):" + filePath.endsWith(".xls"));
		}
		
		// startWith
		if (str.startsWith("abc")) {
			LOGGER.debug("str.startsWith(\"abc\"):" + str.startsWith("abc"));
		}
		
		LOGGER.debug("str.getBytes():" + str.getBytes());
		
		
		Person p1 = new Person("12345", 20, "홍길동");
		LOGGER.debug("p1:" + p1);
		
		Person p2 = new Person("12346", 20, "홍길동");

		LOGGER.debug("p1.equals(p2):" + p1.equals(p2));
		
		LOGGER.debug("str.hashCode():" + str.hashCode());
		LOGGER.debug("str2.hashCode():" + str2.hashCode());
		LOGGER.debug("str3.hashCode():" + str3.hashCode());
		
		Set<Person> personSet = new HashSet<>();
		personSet.add(p1);
		personSet.add(p2);
		
		LOGGER.debug(personSet);
		
		Map<Person, Object> personMap = new HashMap<>();
		personMap.put(p1, p1);
		personMap.put(p2, p2);
		
		LOGGER.debug(personMap);
		
		LOGGER.debug("p1 == p2 : " + (p1 == p2));
		LOGGER.debug("p1.hashCode() : " + p1.hashCode());
		LOGGER.debug("p2.hashCode() : " + p2.hashCode());
		
		LOGGER.debug("str.indexOf(\"bc\"):" + str.indexOf("bc"));
		
		LOGGER.debug(String.format("str5:[%s]", str5));
		LOGGER.debug(String.format("str5.trim():[%s]", str5.trim()));
		LOGGER.debug(String.format("str.equals(str5):%s", str.equals(str5)));
		LOGGER.debug(String.format("str.equals(str5.trim()):%s", str.equals(str5.trim())));
		
		LOGGER.debug("str6.replace(\"bcd\", \"BCD\"):" + str6.replace("bcd", "BCD"));
		LOGGER.debug("str6.replaceAll(\"bcd\", \"BCD\"):" + str6.replaceAll("bcd", "BCD"));
		
		String[] splitData = str7.split(",");
		for (String oneData : splitData) {
			LOGGER.debug(oneData);
		}
		
		LOGGER.debug("str.substring(1, 2):" + str.substring(1, 2));
		
		int a = 6;
		String aa = "6";
		LOGGER.debug(Integer.toString(a));
		LOGGER.debug(Integer.parseInt(aa));
		
		int LOOP = 10000;
		String b = "";
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			b += "1";
			b += "2";
			b += "3";
			b += "4";
		}
		LOGGER.debug("걸린시간1:" + (System.currentTimeMillis() - startTime) + "," + b.length());
		
		startTime = System.currentTimeMillis();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < LOOP; i++) {
			buf.append("1");
			buf.append("2");
			buf.append("3");
			buf.append("4");
		}		
		LOGGER.debug("걸린시간2:" + (System.currentTimeMillis() - startTime) + "," + buf.length());
		
	}
}

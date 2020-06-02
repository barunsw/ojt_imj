package com.barunsw.imj.day02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class StringTest {
	private static Logger LOGGER = LogManager.getLogger(StringTest.class);
	
	public static void main(String[] argus) {
		String str 	= "abcd";
		String str2 = "abce";
		// String str3 = "abcd";
		String str3 = new String("abcd");
		String str4 = new String("ABCD");
		String str5 = " \n\t    abcd   \n\t ";
		String str6 = "abcd abcd abcd abcd";
		String str7 = "abcd,abcd,abcd,abcd";
		String str8 = "";
		
		long fileSize 		= 999L;
		String filePath 	= "/aaa/bbb/test.xls";
		
		// 1. 문자열 출력
		// 		1)charAt
		LOGGER.debug("str.charAt(1):" + str.charAt(1) + ", str.charAt(2):" + str.charAt(2));
		
		//		2)format
		LOGGER.debug(String.format("str.char(1):%s, str.char(2):%s", str.charAt(1), str.charAt(2)));
		//		3) {}
		LOGGER.debug("filePath.endsWith(\".xls\"): {}", filePath.endsWith(".xls"));
		
		// 2. compareTo, equals, ==
		LOGGER.debug("str.compareTo(str2):" + str.compareTo(str2));
		LOGGER.debug("str2.compareTo(str):" + str2.compareTo(str));
		LOGGER.debug("str.compareTo(str):" + str.compareTo(str));
		
		if ( str.equals(str3) ) {
			LOGGER.debug("str.equals(str3) == True");
		}
		
		if ( str.compareTo(str3) == 0 ) {
			LOGGER.debug("str.compareTo(str3) == 0");
		}
		
		if ( str == str3 ) {
			LOGGER.debug("str == str3");
		}
		else {
			LOGGER.debug("str != str3");
		}
		
		// 3. 대소문자 없이 문자열 비교
		if ( str.equalsIgnoreCase(str4) ) {
			LOGGER.debug("str.equalsIgnoreCase(str4) == True");
		}
		
		// 4.concat(+)
		LOGGER.debug("str.concat(str4): " + str.concat(str4));
		
		// 5. 지정된 문자열 테스트
		if ( filePath.endsWith(".xls") ) {
			LOGGER.debug("filePath.endsWith(\".xls\"): " + filePath.endsWith(".xls"));
		}
		
		if ( str.startsWith("abc") ) {
			LOGGER.debug("str.startsWith(\"abc\")" + str.startsWith("abc"));
		}

		// 6. getBytes
		LOGGER.debug("str.getBytes(): " + str.getBytes());
		
		// 7. hashCode
		Person p1 = new Person("12345", 20, "홍길동");
		LOGGER.debug("p1:" + p1);
		
		Person p2 = new Person("12346", 20, "홍길동");
		LOGGER.debug("p2: " + p2);
		
		LOGGER.debug("p1.equals(p2): " + p1.equals(p2));
		
		// Person.java에서 override로 hashCode를 같게 만들어서 실행
		// LOGGER.debug("p1.hashCode(): " + p1.hashCode());
		// LOGGER.debug("p2.hashCode(): " + p2.hashCode());
		
		LOGGER.debug("str.hashCode() : " + str.hashCode());
		LOGGER.debug("str2.hashCode(): " + str2.hashCode());
		LOGGER.debug("str3.hashCode(): " + str3.hashCode());
		
		// 8. Set
		Set<Person> personSet = new HashSet<>();
		personSet.add(p1);
		personSet.add(p2);
		
		LOGGER.debug(personSet);
		
		// 9. Map
		Map<Person, Object> personMap = new HashMap<>();
		personMap.put(p1, p1);
		personMap.put(p2, p2);
		
		LOGGER.debug(personMap);
		
		LOGGER.debug("p1 == p2: " + (p1 == p2));
		LOGGER.debug("p1.hashCode(): " + p1.hashCode());
		LOGGER.debug("p2.hashCode(): " + p2.hashCode());
		
		
		// 10. indexOf
		LOGGER.debug("str.indexOf(\"bc\"): " + str.indexOf("bc"));
		LOGGER.debug("str.indexOf(\"fg\"): " + str.indexOf("fg"));
		
		// 11. isEmpty()
		LOGGER.debug("str8.isEmpty(): " + str8.isEmpty());
		LOGGER.debug("str8.length() : " + str8.length());
		
		// 12. trim
		LOGGER.debug("str5: {}", str5);
		LOGGER.debug("str5.trim(): {}", str5.trim());
		LOGGER.debug("str.equals(str5): {}", str.equals(str5));
		LOGGER.debug("str.equals(str5): {}", str.equals(str5.trim()));
		
		// 13. replace
		LOGGER.debug("str6.replace(\"bcd\", \"BCD\"):" + str6.replace("bcd", "BCD"));
		
		// 14. split()
		String[] splitDate = str7.split(",");
		for (String oneDate : splitDate) {
			LOGGER.debug(oneDate);
		}
		
		// 15. subString
		LOGGER.debug("str.substring(1): " + str.substring(1));
		LOGGER.debug("str.substring(1, 2): " + str.substring(1, 2));
		
		// 16. 형변환
		int a = 6;
		String aa = "6";
		
		LOGGER.debug(Integer.toString(a));
		// LOGGER.debug(Integer.toString(a) + 5);
		LOGGER.debug(Integer.parseInt(aa));
		// LOGGER.debug(Integer.parseInt(aa) + 5);
		
		// 17. StringBuffer
		int LOOP = 10000;
		String b = "";
		
		//		1) String으로 값을 연결 할 때
		long startTime = System.currentTimeMillis();
		for ( int i = 0; i < LOOP; i++ ) {
			b += "1";
			b += "2";
			b += "3";
			b += "4";
		}
		LOGGER.debug("String 사용 시 걸린시간		: {}, {}", (System.currentTimeMillis() - startTime), b.length());
		
		//		2) StringBuffer로 값을 연결 할 때
		startTime = System.currentTimeMillis();
		StringBuffer buf = new StringBuffer();
		for ( int i = 0; i < LOOP; i++ ) {
			buf.append("1");
			buf.append("2");
			buf.append("3");
			buf.append("4");
		}
		
		LOGGER.debug("StringBuffer 사용 시 걸린시간	: {}, {}", (System.currentTimeMillis() - startTime), buf.length());
	}
}



























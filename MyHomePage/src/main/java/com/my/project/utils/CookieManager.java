package com.my.project.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	// 쿠키 ?��?�� 메서?��
				// 명시?�� ?���?, �?, ?���?기간?�� 조건?���? ?��로운 쿠키�? ?��?��
		public static void makeCookie (HttpServletResponse response, String cName, String cValue, int cTime) {
			Cookie cookie = new Cookie (cName, cValue);
			cookie.setPath("/"); //경로?��?��
			cookie.setMaxAge(cTime); // 쿠키 ?���? 기간
			response.addCookie(cookie);	// 쿠키 ???��
		}
		
		/// 쿠키 ?��보�?? ?��?�� 메서?��
		// 명시?�� 쿠키 ?��름을 찾아 �? 값을 반환?��?�� 메서?��
		public static String readCookie (HttpServletRequest request, String cName) {
			String cookieValue = "";	// ��ȯ ��
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie c : cookies) {
					String cookieName = c.getName();
					
					// 매개�??���? ?��?��?��?�� cName�??��?�� ?��?��?��?�� ?��름의 Value�? 리턴?��켜�??��.  
					if(cookieName.equals(cName)) {
						cookieValue = c.getValue();
					}
				}
			}
			
			return cookieValue;
		}
		// 쿠키 ?��?�� 메서?��
					// 명시?�� ?��름의 쿠키�? ?��?��?��?�� 메서?��
		public static void deleteCookie (HttpServletResponse response, String cName) {
			makeCookie(response, cName, "", 0);
		}
}


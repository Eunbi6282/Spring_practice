package com.my.project.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	// ì¿ í‚¤ ?ƒ?„± ë©”ì„œ?“œ
				// ëª…ì‹œ?•œ ?´ë¦?, ê°?, ?œ ì§?ê¸°ê°„?„ ì¡°ê±´?œ¼ë¡? ?„¸ë¡œìš´ ì¿ í‚¤ë¥? ?ƒ?„±
		public static void makeCookie (HttpServletResponse response, String cName, String cValue, int cTime) {
			Cookie cookie = new Cookie (cName, cValue);
			cookie.setPath("/"); //ê²½ë¡œ?„¤? •
			cookie.setMaxAge(cTime); // ì¿ í‚¤ ?œ ì§? ê¸°ê°„
			response.addCookie(cookie);	// ì¿ í‚¤ ???¥
		}
		
		/// ì¿ í‚¤ ? •ë³´ë?? ?½?Š” ë©”ì„œ?“œ
		// ëª…ì‹œ?•œ ì¿ í‚¤ ?´ë¦„ì„ ì°¾ì•„ ê·? ê°’ì„ ë°˜í™˜?•˜?Š” ë©”ì„œ?“œ
		public static String readCookie (HttpServletRequest request, String cName) {
			String cookieValue = "";	// ï¿½ï¿½È¯ ï¿½ï¿½
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie c : cookies) {
					String cookieName = c.getName();
					
					// ë§¤ê°œë³??ˆ˜ë¡? ?…?’‹?˜?Š” cNameë³??ˆ˜?— ?•´?‹¹?•˜?Š” ?´ë¦„ì˜ Valueë¥? ë¦¬í„´?‹œì¼œì??‹¤.  
					if(cookieName.equals(cName)) {
						cookieValue = c.getValue();
					}
				}
			}
			
			return cookieValue;
		}
		// ì¿ í‚¤ ?‚­? œ ë©”ì„œ?“œ
					// ëª…ì‹œ?•œ ?´ë¦„ì˜ ì¿ í‚¤ë¥? ?‚­? œ?•˜?Š” ë©”ì„œ?“œ
		public static void deleteCookie (HttpServletResponse response, String cName) {
			makeCookie(response, cName, "", 0);
		}
}


package com.my.project.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	// μΏ ν€ ??± λ©μ?
				// λͺμ? ?΄λ¦?, κ°?, ? μ§?κΈ°κ°? μ‘°κ±΄?Όλ‘? ?Έλ‘μ΄ μΏ ν€λ₯? ??±
		public static void makeCookie (HttpServletResponse response, String cName, String cValue, int cTime) {
			Cookie cookie = new Cookie (cName, cValue);
			cookie.setPath("/"); //κ²½λ‘?€? 
			cookie.setMaxAge(cTime); // μΏ ν€ ? μ§? κΈ°κ°
			response.addCookie(cookie);	// μΏ ν€ ???₯
		}
		
		/// μΏ ν€ ? λ³΄λ?? ?½? λ©μ?
		// λͺμ? μΏ ν€ ?΄λ¦μ μ°Ύμ κ·? κ°μ λ°ν?? λ©μ?
		public static String readCookie (HttpServletRequest request, String cName) {
			String cookieValue = "";	// οΏ½οΏ½Θ― οΏ½οΏ½
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie c : cookies) {
					String cookieName = c.getName();
					
					// λ§€κ°λ³??λ‘? ???? cNameλ³??? ?΄?Ή?? ?΄λ¦μ Valueλ₯? λ¦¬ν΄?μΌμ??€.  
					if(cookieName.equals(cName)) {
						cookieValue = c.getValue();
					}
				}
			}
			
			return cookieValue;
		}
		// μΏ ν€ ?­?  λ©μ?
					// λͺμ? ?΄λ¦μ μΏ ν€λ₯? ?­? ?? λ©μ?
		public static void deleteCookie (HttpServletResponse response, String cName) {
			makeCookie(response, cName, "", 0);
		}
}


package com.my.project.utils;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
            int pageNum, String reqUrl) {
        String pagingStr = "";

        // ?¨κ³? 3 : ? μ²? ??΄μ§? ? κ³μ°
        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

        // ?¨κ³? 4 : '?΄?  ??΄μ§? λΈλ‘ λ°λ‘κ°?κΈ?' μΆλ ₯
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
        if (pageTemp != 1) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[μ²? ??΄μ§?]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
                         + "'>[?΄?  λΈλ‘]</a>";
        }

        // ?¨κ³? 5 : κ°? ??΄μ§? λ²νΈ μΆλ ₯
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // ??¬ ??΄μ§?? λ§ν¬λ₯? κ±Έμ? ??
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
                             + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }

        // ?¨κ³? 6 : '?€? ??΄μ§? λΈλ‘ λ°λ‘κ°?κΈ?' μΆλ ₯
        if (pageTemp <= totalPages) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
                         + "'>[?€? λΈλ‘]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
                         + "'>[λ§μ?λ§? ??΄μ§?]</a>";
        }
        
        //System.out.println(pagingStr);

        return pagingStr;
    }
}

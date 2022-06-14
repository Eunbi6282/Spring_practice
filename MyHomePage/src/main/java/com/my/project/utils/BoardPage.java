package com.my.project.utils;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
            int pageNum, String reqUrl) {
        String pagingStr = "";

        // ?���? 3 : ?���? ?��?���? ?�� 계산
        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

        // ?���? 4 : '?��?�� ?��?���? 블록 바로�?�?' 출력
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
        if (pageTemp != 1) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[�? ?��?���?]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
                         + "'>[?��?�� 블록]</a>";
        }

        // ?���? 5 : �? ?��?���? 번호 출력
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // ?��?�� ?��?���??�� 링크�? 걸�? ?��?��
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
                             + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }

        // ?���? 6 : '?��?�� ?��?���? 블록 바로�?�?' 출력
        if (pageTemp <= totalPages) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
                         + "'>[?��?�� 블록]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
                         + "'>[마�?�? ?��?���?]</a>";
        }
        
        //System.out.println(pagingStr);

        return pagingStr;
    }
}

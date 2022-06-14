package com.my.project.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartRequest;


//?��?�� ?��로드 처리?��?�� ?��?��?��

public class FileUtil {
	
	//?��?�� ?��로드 (multipart/form-data ?���?) 처리
	//MultipartRequest  : cos.jar ?��?��브러리에 존재, ?��?��?��로드 처리 
	
	
	public static MultipartRequest uploadFile (HttpServletRequest req, 
			String saveDirectory, int maxPostSize) {
		
		try {
			//?��로드 ?���? 
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8"); 
			
		}catch (Exception e) {
			//?��로드 ?��?��
			e.printStackTrace();
			System.out.println("?��?�� ?��로드 ?��?��");
			return null ; 
		}		
	}
	
	//�? ?��?��?��?�� ?��?�� ?��로드?�� ?��?��?�� ?��?��?�� ?���? ?��?��  
	public static void deleteFile (HttpServletRequest req, 
			String directory, String filename) {
		//매개�??�� directory ?�� ?��?��?�� ?��버의 ?��로드 ?��?��?�� ?��??경로 
		String sDirectory = req.getServletContext().getRealPath(directory); 
		File file = new File (sDirectory + File.separator + filename); 
		if (file.exists()) {
			file.delete(); 
		}
	}
	
	// 명시?�� ?��?��?�� 찾아 ?��?��로드?��?��?��.
    public static void download(HttpServletRequest req, HttpServletResponse resp,
            String directory, String sfileName, String ofileName) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        try {
        	// ?��?��?�� 찾아 ?��?�� ?��?���? ?��?��
            File file = new File(sDirectory, sfileName);
            InputStream iStream = new FileInputStream(file);

         // ?���? ?��?���? 깨짐 방�?
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) {
                ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            else {
                ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
            }

            // ?��?�� ?��?��로드?�� ?��?�� ?��?�� ?��?��
            resp.reset();
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition",
                           "attachment; filename=\"" + ofileName + "\"");
            resp.setHeader("Content-Length", "" + file.length() );

            //out.clear();  // 출력 ?��?���? 초기?��

            // response ?��?�� 객체로�??�� ?��로운 출력 ?��?���? ?��?��
            OutputStream oStream = resp.getOutputStream();

            // 출력 ?��?��림에 ?��?�� ?��?�� 출력
            byte b[] = new byte[(int)file.length()];
            int readBuffer = 0;
            while ( (readBuffer = iStream.read(b)) > 0 ) {
                oStream.write(b, 0, readBuffer);
            }

            // ?��/출력 ?��?���? ?��?��
            iStream.close();
            oStream.close();
        }
        catch (Exception e) {
            System.out.println("?��?��?�� 찾을 ?�� ?��?��?��?��.");
            e.printStackTrace();
        }
     
    }
}

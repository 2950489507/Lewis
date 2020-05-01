package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtil {

	public static Workbook getWorkbook(String path) {
		
		String fileType=path.substring(path.lastIndexOf(".")+1);
		
		InputStream is=null;
		
		try {
			is=new FileInputStream(path);
			Workbook wb=null;
			if(fileType.equals("xls")) {
				wb=new HSSFWorkbook(is);
			}else if(fileType.equals("xlsx")) {
				wb=new XSSFWorkbook(is);
			}else {
				return null;
			}
			return wb;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

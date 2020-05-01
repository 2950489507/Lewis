package webPageModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Util.POIUtil;

public class WebPageModel {
	
	String pageName;
	List<ElementModel> elements;
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public List<ElementModel> getElements() {
		return elements;
	}
	public void setElements(List<ElementModel> elements) {
		this.elements = elements;
	}

	public WebPageModel() {
		this.elements=new ArrayList<ElementModel>();
	}
	
	public static List<WebPageModel> getWebPageList(String path){
		Workbook workbook=POIUtil.getWorkbook(path);
		Sheet sheet=workbook.getSheetAt(0);
		
		int lastcell=sheet.getRow(0).getLastCellNum();
		List<WebPageModel> webPageList=new ArrayList<WebPageModel>();
		WebPageModel webPage= new WebPageModel();
		
		Boolean isFirstPage=true;
		int lastRow=sheet.getLastRowNum();
		
		for(int j=1;j<lastRow+1;j++) {
			Row row=sheet.getRow(j);
			ElementModel element = new ElementModel();
			
			for(int i=0;i<lastcell;i++) {
				String valueStr="";
				Cell cell=row.getCell(i);
				if(cell==null) {
					valueStr = null;
				}else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					valueStr = cell.getStringCellValue();
				}
				
				switch(i) {
				
				case 0:
					if (null != valueStr) {
						if (isFirstPage) {
							isFirstPage = false;
						}else {
							webPageList.add(webPage);
						}
						webPage = new WebPageModel();
						webPage.pageName = valueStr;
					}
					break;
				case 1:
					element.elementName = valueStr;
					break;
				case 2:
					element.elementByType = valueStr;
					break;
				case 3:
					element.elementValue = valueStr;
					webPage.elements.add(element);
					break;
				default:
					break;
				}
			}
			
		}
		webPageList.add(webPage);
		return webPageList;
	}
	
/*	public static void main(String[] args) {
		List<WebPageModel> webPageModels = getWebPageList("C:\\Users\\pc\\Desktop\\UIExample.xlsx");
		 System.out.println(webPageModels);
	}
*/
	@Override
	public String toString() {
		return "WebPageModel\n [pageName=" + pageName + ", elements=" + elements + "]\n";
	}
}

package apiModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Util.POIUtil;

public class APICaseModel {

	String caseName;
	List<APIStepModel> stepModels;
	
	public APICaseModel() {
		this.stepModels=new ArrayList<APIStepModel>();
	}
	
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public List<APIStepModel> getStepModels() {
		return stepModels;
	}
	public void setStepModels(List<APIStepModel> stepModels) {
		this.stepModels = stepModels;
	}
	
	@Override
	public String toString() {
		return "CaseModel [caseName=" + caseName + ",stepModels=" + stepModels + "]\n";
	}

	public static List<APICaseModel> getCaseList(String path){
		Workbook wb=POIUtil.getWorkbook(path);
		System.out.println(wb);
		List<APICaseModel> caseModels = new ArrayList<APICaseModel>();
		APICaseModel caseModel = new APICaseModel();
		//System.out.println(path);
		int sheetIndex=0;
		Sheet sheet = wb.getSheetAt(0);
		
		//获取行数以空行作为一个case结束的标志所以+1
		int lastRow = sheet.getLastRowNum()+1;
		//获取列数
		int lastCell = sheet.getRow(0).getLastCellNum();
		//System.out.println(lastRow+"----"+lastCell);

		for(int i=0;i<=lastRow;i++) {
			Row row=sheet.getRow(i);
			if(i==0) {
				//第一行是标题
				continue;
			}
			if(row==null) {
				//如果是空行，则可以添加case到list,再重新初始化caseModel
				caseModels.add(caseModel);
				caseModel=new APICaseModel();
				continue;
			}
			APIStepModel stepModel= new APIStepModel();
			
			for(int j=0;j<lastCell;j++) {
				String valueStr="";
				Cell cell= row.getCell(j);
				if(cell==null) {
					valueStr=null;
				}else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					valueStr=cell.getStringCellValue();
					//System.out.println(valueStr);
				}
				
				switch(j) {
				case 0:
					if(valueStr!=null&&valueStr.length()>0) {
						caseModel.setCaseName(valueStr);
					}
					break;
				case 1:
					stepModel.setPrecondition(valueStr);
					break;
				case 2:
					stepModel.setStep(valueStr);
					break;
				case 3:
					stepModel.setUrl(valueStr);
					break;
				case 4:
					stepModel.setAction(valueStr);
				case 5:
					stepModel.setValue(valueStr);
					break;
				case 6:
					stepModel.setExpect(valueStr);
					break;
				default:
					break;
				}
				
			}
			caseModel.stepModels.add(stepModel);
		}
		return caseModels;	
	}
	
	//public static void main(String[] args) {
	//	System.out.println(APICaseModel.getCaseList("C:\\Users\\pc\\Desktop\\APIExample.xlsx"));
	//}
}

package itest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Factory;

import apiModel.APICaseModel;
import webCaseModel.CaseModel;

public class TestFactory {

	@Factory
	public Object[] creatInstances() {
		List<String> APIPathList =getAPICasePathList();
		List<String> webPathList=getWebCasePathList();
		int resultSize =APIPathList.size()+webPathList.size();
		Object[] objects =new Object[resultSize];
		//System.out.println(resultSize);
		//实例化APITest
		for(int i=0;i<APIPathList.size();i++) {
			//System.out.println(APICaseModel.getCaseList(APIPathList.get(i)));
			objects[i]=new APITest(APICaseModel.getCaseList(APIPathList.get(i)));
		}
		for(int i=0,j=APIPathList.size();j<objects.length;i++,j++) {
			objects[j]=new WebTest(CaseModel.getCaseList(webPathList.get(i)));
		}
		
		return objects;
	}

	public static List<String> getAPICasePathList() {
		// TODO Auto-generated method stub
		List<String> files=new ArrayList<String>();
		File file=new File("caseexcel/apicase/");
		File[] tempList=file.listFiles();
		if(tempList.length>0) {
			
			for(int i=0;i<tempList.length;i++) {
				if(tempList[i].isFile()) {
					System.out.println("文件："+tempList[i]);
					files.add(tempList[i].toString());
				}
				if(tempList[i].isDirectory()) {
					System.out.println("文件夹：" + tempList[i]);
				}
				
			}
		}else {
			throw new NullPointerException("没有找到用例！");
		}
		return files;
	}
	
	static public ArrayList<String> getWebCasePathList() {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File("caseexcel/webcase/");
		File[] tempList = file.listFiles();
		if (tempList.length > 0) {
			for (int i = 0; i < tempList.length; i++) {
				if (tempList[i].isFile()) {
					System.out.println("文     件：" + tempList[i]);
					files.add(tempList[i].toString());
				}
				if (tempList[i].isDirectory()) {
					// System.out.println("文件夹：" + tempList[i]);
				}
			}
		} else {
			throw new NullPointerException("没有找到web测试用例");
		}

		return files;
	}
}

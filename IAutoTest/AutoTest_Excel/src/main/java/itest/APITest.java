package itest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apiModel.APICaseModel;
import apiModel.APIStepModel;
import okhttp.OKHttpUtil;

public class APITest {

	List<APICaseModel> caseList;
	OKHttpUtil okHttpUtil;
	
	public APITest(List<APICaseModel> caseList) {
		this.okHttpUtil=new OKHttpUtil();
		this.caseList=caseList;
	}
	
	@Test(dataProvider = "dp")
	public void run(APICaseModel caseModel) {
		doCase(caseModel);
	}

	public void doCase(APICaseModel caseModel) {
		// TODO Auto-generated method stub
		for(int i=0;i<caseModel.getStepModels().size();i++) {
			APIStepModel apiStepModel =caseModel.getStepModels().get(i);
			if(apiStepModel.getAction().equals("get")) {
				String actual=okHttpUtil.get(apiStepModel.getUrl(), apiStepModel.getValue());
				Assert.assertEquals(actual,apiStepModel.getExpect());
			}else if(apiStepModel.getAction().equals("post")) {
				String actual = okHttpUtil.post(apiStepModel.getUrl(), apiStepModel.getValue());
				Assert.assertEquals(actual, apiStepModel.getExpect());
			}else {
				System.out.println("还没有写呢！");
			}
			
		}
	}
	
	@DataProvider(name ="dp")
	public Object[] dp() {
		//caseList = APICaseModel.getCaseList("caseexcel/apicase/APIExample.xlsx");
		int size = caseList.size();
		Object[] objects =new Object[size];
		for(int i=0;i<objects.length;i++) {
			objects[i]=caseList.get(i);
		}
		
		return objects;
	}
}

package okhttp;

import java.io.IOException;
import java.util.jar.JarOutputStream;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpUtil {

	OkHttpClient okHttpClient= new OkHttpClient();
	public static final MediaType type=MediaType.parse("application/json;charset=utf-8");
	
	//拼接geturl
	
	public String MergeParam(String url,JSONObject param) {
		StringBuffer stringBuffer = new StringBuffer(url);
		stringBuffer.append("?");
		Boolean isFirst=true;
		for(String key:param.keySet()) {
			if(!isFirst) {
				stringBuffer.append("&");
			}else {
				isFirst=false;
			}
			stringBuffer.append(key);
			stringBuffer.append("=");
			stringBuffer.append(param.get(key));
		}
		url=stringBuffer.toString();
		return url;
	}
	
	public String get(String url,String param) {
		String mergeUrl=null;
		JSONObject paramJO=null;
		JSONObject headerJO=null;
		if(param!=null) {
		JSONObject paramHeaderJO =JSONObject.parseObject(param);
		paramJO =paramHeaderJO.getJSONObject("param");
		headerJO =paramHeaderJO.getJSONObject("headers");
		}
		if(paramJO!=null) {
			mergeUrl=MergeParam(url, paramJO);
			}else {
			mergeUrl=url;
			}
			
		//System.out.println(mergeUrl);
		okhttp3.Request.Builder requestBuilder=new Request.Builder().url(mergeUrl);
		if(headerJO!=null) {
			for(String key:headerJO.keySet()) {
				requestBuilder.addHeader(key, headerJO.getString(key));
			}
		}
				
		Request request =requestBuilder.build();
		Call call = okHttpClient.newCall(request);
		Response response;
		try {
			response=call.execute();
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "请求异常，发生错误！";
		}
		
	}
	
	public String post(String url,String param) {
		JSONObject paramHeaderJO=JSONObject.parseObject(param);
		JSONObject paramJO = paramHeaderJO.getJSONObject("param");
		JSONObject headerJO=paramHeaderJO.getJSONObject("header");
		/*
		Builder formBuilder =new FormBody.Builder(); 
		
		if(null!=paramJO) {
			for(String key:paramJO.keySet()) {
				System.out.println(key+"---"+paramJO.getString(key));
				formBuilder.add(key, paramJO.getString(key));
			}
		}
		
		FormBody formBody = formBuilder.build();
		okhttp3.Request.Builder requestBuilder = new Request.Builder().url(url).post(formBody);
		
		if(null!=headerJO) {
			for(String key:headerJO.keySet()) {
				System.out.println(key+":--------------"+headerJO.getString(key));
				requestBuilder.addHeader(key, headerJO.getString(key));
			}
		}
		
		Request request = requestBuilder.build();*/
		Headers headers=null;
		Headers.Builder headerBuilder =new Headers.Builder();
		if(null!=headerJO) {
			for(String key:headerJO.keySet()) {
				//System.out.println(key+":--------------"+headerJO.getString(key));
				headerBuilder.add(key, headerJO.getString(key));
			}
		}
		headers=headerBuilder.build();
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");  	  
	    RequestBody body = RequestBody.create(JSON, paramJO.toJSONString());  
	    Request request = new Request.Builder()  
	            .url(url)  
	            .post(body)
	            .headers(headers)
	            .build();  
		
		Response response;
		Call call = okHttpClient.newCall(request);
		try {
			response =call.execute();
			//System.out.println(response.headers());
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "请求错误";
		}
		
		
	}
}

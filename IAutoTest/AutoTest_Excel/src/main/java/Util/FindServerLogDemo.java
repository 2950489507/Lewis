package com.test.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class FindServerLogDemo {
	
	public static void main(String[] args) {
		String url = "http://www.baidu.com/";
		String domian=InetAddressDemo.getDomain(url);
		//String[] IPs = InetAddressDemo.getIPByDomain(domian);
		String[] IPs={"47.112.253.240"};
		System.out.println(IPs[0]);
		boolean findLogSuccess=false;
		String[] logKeywords= {"liwei","liwei test","get result success liwei","sdf liwei return success"};
		String logFilePath="/home/test.log";
		Properties serverProp = new Properties();
		try {
			serverProp.load(new FileReader("config/server.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String serverIP=null;
		String userName=null;
		String password=null;
		List<String> result=new ArrayList<String>();
		for(int i=0;i<IPs.length;i++) {
			serverIP=IPs[i];
			if(serverProp.getProperty(IPs[i])!=null) {
				String[] username_pwd=serverProp.getProperty(IPs[i]).split("/");
				userName=username_pwd[0];
				password=username_pwd[1];
			}	
			
			System.out.println(serverIP+"-"+userName+"-"+password);
			result=getServerLog(serverIP, logFilePath, userName, password, logKeywords[0]);
			boolean[] existKeywords=new boolean[logKeywords.length-1];
			for(int j=1;j<logKeywords.length;j++) {
				existKeywords[j-1]=result.contains(logKeywords[j]);
			}
			int booleanCount=0;
			for(int x=0;x<existKeywords.length;x++) {
				if(existKeywords[x])
					booleanCount++;
			}
			if(booleanCount==existKeywords.length) {
				findLogSuccess=true;
			}
		}
		System.out.println(findLogSuccess);
	}

	//获取服务器日志文件中包含关键字的日志记录
	public static List<String> getServerLog(String serverIP,String logFilePath,
			String username,String password,String keyword) {
		List<String> result=new ArrayList<String>();
		JSch sshSingleton=new JSch();
		try {
			Session session=sshSingleton.getSession(username, serverIP);
			session.setPassword(password);
			Properties config=new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			
			ChannelExec channel=(ChannelExec)session.openChannel("exec");
			BufferedReader in = new BufferedReader(new InputStreamReader(
                    channel.getInputStream()));
			channel.setCommand("cat " + logFilePath + " |grep -w "
                    + keyword);
            channel.connect();
            result=new ArrayList<String>();
            String msg;
            while((msg=in.readLine())!=null) {
            	result.add(msg);
            	System.out.println(msg);
            }
            channel.disconnect();
            session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}
}

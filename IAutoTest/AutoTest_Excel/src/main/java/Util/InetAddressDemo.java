package com.test.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	public static void main(String[] args) {

		String url = "http://www.baiud.com/";
		String domian=getDomain(url);
		String[] IP = getIPByDomain(domian);

		  for(int i=0;i<IP.length;i++) {
		  
		  System.out.println(IP[i]);
		  
		  }
		 
		 
	}
	//通过域名获取IP
	public static String[] getIPByDomain(String domain) {
		try {
			InetAddress[] address = InetAddress.getAllByName(domain);
			String[] IP = new String[address.length];
			for (int i = 0; i < address.length; i++) {
				// System.out.println(address[i].getHostAddress());
				IP[i] = address[i].getHostAddress();

			}
			return IP;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//通过url获取DomainName
	 public static String getDomain(String url) {
	        String result = "";
	        int j = 0, startIndex = 0, endIndex = 0;
	        for (int i = 0; i < url.length(); i++) {
	            if (url.charAt(i) == '/') {
	                j++;
	                if (j == 2)
	                    startIndex = i;
	                else if (j == 3)
	                    endIndex = i;
	            }
	 
	        }
	        result = url.substring(startIndex + 1, endIndex);
	        return result;
	 }
}

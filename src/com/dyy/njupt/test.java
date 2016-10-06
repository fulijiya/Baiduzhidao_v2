package com.dyy.njupt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t=new test();
		String response=t.getResponse("http://zhidao.baidu.com/question/1121042.html");
		int start_accept = response.indexOf("wgt-best");// 被采纳者回答信息
		start_accept = response.indexOf("answer-time",start_accept);
		start_accept = response.indexOf(">",start_accept);
		int end_accept = response.indexOf("其他类似问题", start_accept);
		String accept = response.substring(start_accept + 1, end_accept);
		System.out.println(accept);
		
		int start_acceptid = accept.indexOf("user-name");// 被采纳者的id
		start_acceptid = accept.indexOf(">", start_acceptid);
		int end_acceptid = accept.indexOf("<", start_acceptid);
		String acceptid = accept.substring(start_acceptid + 1, end_acceptid);
		System.out.println(acceptid);
		
		// 回答时间
	//	int start_accepttime = accept.indexOf("ins");// 被采纳者回答时间
		int start_accepttime = accept.indexOf(">2");
		int end_accepttime = accept.indexOf("<", start_accepttime);
		String accepttime = accept.substring(start_accepttime + 1, end_accepttime);
		System.out.println(accepttime);
		
		
		
		// 回答内容
		int start_acceptcontent = response.indexOf("aContent");
		start_acceptcontent = response.indexOf(">", start_acceptcontent);
		int end_acceptcontent = response.indexOf("</pre>", start_acceptcontent);
		String acceptcontent = response.substring(start_acceptcontent + 1, end_acceptcontent);
		acceptcontent = acceptcontent.replaceAll("<img.*?>", "").replaceAll("<.*?>", "");
		System.out.println(acceptcontent);
	}
	public String getResponse(String url) {
		HttpURLConnection httpUrlConnection = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String html = "";
		try {
			httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			httpUrlConnection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpUrlConnection.setUseCaches(true);
		try {
			httpUrlConnection.connect();
			inputStream = httpUrlConnection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk")); // 选择合�?�的编码方式，如"gbk","gb2312"
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String string;
		try {
			while ((string = bufferedReader.readLine()) != null) {
				html += string;
			}
			bufferedReader.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpUrlConnection.disconnect();
		return html;
	}

}

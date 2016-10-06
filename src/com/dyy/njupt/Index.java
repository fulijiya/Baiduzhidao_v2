package com.dyy.njupt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Index {
	public static final String LINE = System.getProperty("line.separator");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void index(String userid, String indexfile) {
		try {
			File f = new File("C:\\Users\\fulijiya\\Desktop\\spiderdata911\\index.txt");
			if (!f.exists())
				f.createNewFile();
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<userid>" + userid + "</userid>" +LINE);
			bw.write("<indexfile>" + indexfile + "</indexfile>" +LINE);
			bw.flush();
			bw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}

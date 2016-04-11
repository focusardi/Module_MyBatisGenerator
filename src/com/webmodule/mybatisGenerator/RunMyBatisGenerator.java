package com.webmodule.mybatisGenerator;

public class RunMyBatisGenerator {

	public static void main(String[] args) throws Exception {

		System.out.println("開始產生Mybatis Code");
		ModuleGenerator rg = new ModuleGenerator();
		try {
			rg.run();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("結束Mybatis Code");
		
	}


}

package com.nit.beans;

import java.text.DecimalFormat;


public class GetColl {

	public static double main(double a) {
		
		 //double a=2;
		 if(a==1)
			 a=a+0.000052;
		double b=40*a;
		
		Double result1=Math.pow(a,80);
		Double result2=Math.pow(2,b);
		
		
		
	/*	System.out.println(result1);
		System.out.println(result2);*/
		
		String r1=result1.toString().substring(0,6);
		String r2=result2.toString().substring(0,6);
		
		Double re1=Double.parseDouble(r1);
		Double re2=Double.parseDouble(r2);
		
		
		double result= re1/re2;
		
		System.out.println(Math.abs(1-result));
		return Math.abs(1-result);
		
		
	
	}
}

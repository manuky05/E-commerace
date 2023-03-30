package com.mantuIT.Exception;

public class DoNothingMethod {
	public void doprocess() 
	{
		
		System.out.println("Do process method Started");
		dowork("msg");
		System.out.println("do work method Ended");
	}
	
public void dowork(String str)
{
	
	System.out.println("This is help method od dowork");
	
}
}

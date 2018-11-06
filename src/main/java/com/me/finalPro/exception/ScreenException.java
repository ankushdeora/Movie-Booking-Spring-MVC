package com.me.finalPro.exception;

public class ScreenException extends Exception{

	public ScreenException(String message)
	{
		super("ScreenException-"+message);
	}
	
	public ScreenException(String message, Throwable cause)
	{
		super("ScreenException-"+message,cause);
	}
	
}

package com.me.finalPro.exception;

public class TheatreException extends Exception{

	public TheatreException(String message)
	{
		super("TheatreException-"+message);
	}
	
	public TheatreException(String message, Throwable cause)
	{
		super("TheatreException-"+message,cause);
	}	
	
}

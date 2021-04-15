package ru.kraiush.spring.exception;

public class ProductException extends RuntimeException
{
	public ProductException(final String message) {
		super(message);
	}
	
	public ProductException(final String message, Throwable cause)	{
		super(message, cause);
	}
}

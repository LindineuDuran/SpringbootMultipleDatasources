package com.lduran.multdb.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateFactory
{
	private static DateFactory instance;

	public static DateFactory getInstance()
	{
		if (instance == null)
		{
			instance = new DateFactory();
		}

		return instance;
	}

	public String dateModelConverter(String s)
	{
		return s.substring(4, 8) + s.substring(2, 4) + s.substring(0, 2);
	}
}
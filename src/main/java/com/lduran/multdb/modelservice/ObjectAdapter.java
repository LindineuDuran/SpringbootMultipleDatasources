package com.lduran.multdb.modelservice;

import java.util.List;

import com.lduran.multdb.db1.modelentity.ObjectBI;

public abstract class ObjectAdapter implements ObjectService
{
	@Override
	public ObjectBI getObject(List<String> fileContent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectBI getObject(String lineContent, ObjectBI obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent, String criteria)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getObjectHeader()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
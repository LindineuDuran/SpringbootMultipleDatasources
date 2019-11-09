package com.lduran.multdb.modelservice;

import java.util.List;

import com.lduran.multdb.db1.modelentity.ObjectBI;

public interface ObjectService
{
	public ObjectBI getObject(List<String> fileContent);

	public ObjectBI getObject(String lineContent, ObjectBI obj);

	public List<? extends ObjectBI> getObjectList(List<String> fileContent);

	public List<? extends ObjectBI> getObjectList(List<String> fileContent, String criteria);

	public String getObjectHeader();

	public String getObjectContent(ObjectBI obj);
}
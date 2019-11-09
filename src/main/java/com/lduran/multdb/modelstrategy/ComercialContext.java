package com.lduran.multdb.modelstrategy;

import java.util.List;

import com.lduran.multdb.db1.modelentity.ObjectBI;
import com.lduran.multdb.modelservice.ObjectService;

public class ComercialContext
{
	private ObjectService service;

	// this can be set at runtime by the application preferences
	public void setObjectService(ObjectService service)
	{
		this.service = service;
	}

	// use the strategy
	public List<? extends ObjectBI> processaDados(List<String> fileContent)
	{
		return this.service.getObjectList(fileContent);
	}
}
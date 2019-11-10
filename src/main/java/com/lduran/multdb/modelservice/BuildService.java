package com.lduran.multdb.modelservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bs")
public class BuildService
{
	@Autowired
	private OrganizacaoService organizacaoSERVICE;

	@Autowired
	private ProdServService prodServService;

	@Autowired
	private ComercialService comercialService;

	@Autowired
	private ComercialH2Service comercialH2Service;

	public ObjectService getObjectService(String objectType)
	{
		switch (objectType)
		{
		case "Organizacao":
			return (ObjectService) this.organizacaoSERVICE;
		case "ProdServ":
			return (ObjectService) this.prodServService;
		case "Comercial":
			return (ObjectService) this.comercialService;
		case "ComercialH2":
			return (ObjectService) this.comercialH2Service;
		default:
			return null;
		}
	}
}

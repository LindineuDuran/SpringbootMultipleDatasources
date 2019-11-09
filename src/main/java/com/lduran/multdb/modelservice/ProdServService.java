package com.lduran.multdb.modelservice;

import java.util.List;

import com.lduran.multdb.db1.modelentity.ProdServ;

public interface ProdServService
{
	public ProdServ save(ProdServ prodServ);

	public void saveAll(List<ProdServ> lstProdServ);
}
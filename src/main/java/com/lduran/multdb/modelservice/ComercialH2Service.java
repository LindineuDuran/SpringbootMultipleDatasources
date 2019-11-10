package com.lduran.multdb.modelservice;

import java.util.List;

import com.lduran.multdb.db2.modelentity.ComercialH2;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;

public interface ComercialH2Service
{
	public ComercialH2 save(ComercialH2 com);

	public void delete();

	public List<ComercialH2Agreg> listAgreg();
}

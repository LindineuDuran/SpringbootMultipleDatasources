package com.lduran.multdb.modelservice;

import java.util.List;

import com.lduran.multdb.db1.modelentity.Comercial;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;

public interface ComercialService
{
	public Comercial save(Comercial com);

	public void saveAll(List<Comercial> lstMovCom);

	public Comercial convertComercialH2AgregToComercial(ComercialH2Agreg com);
}
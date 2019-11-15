package com.lduran.multdb.modelservice;

import java.util.List;

import com.lduran.multdb.db1.modelentity.Comercial;
import com.lduran.multdb.db1.modelentity.ObjectBI;
import com.lduran.multdb.db2.modelentity.ComercialH2;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;

public interface ComercialH2Service
{
	public ComercialH2 save(ComercialH2 com);

	public void saveAll(List<Comercial> lstNotasFiscaisServico);

	public void delete();

	public List<ComercialH2Agreg> listAgreg();

	public ComercialH2 getObject(String lineContent, ComercialH2 movCom);

	public String getObjectHeader();

	public String getObjectContent(ObjectBI obj);
}

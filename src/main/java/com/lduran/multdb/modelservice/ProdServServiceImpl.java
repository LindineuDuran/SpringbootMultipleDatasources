package com.lduran.multdb.modelservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lduran.multdb.db1.modelentity.ObjectBI;
import com.lduran.multdb.db1.modelentity.ProdServ;
import com.lduran.multdb.db1.modelrepository.ProdServRepo;
import com.lduran.multdb.util.ToolsFactory;

@Service
public class ProdServServiceImpl extends ObjectAdapter implements ProdServService
{
	@Autowired
	ProdServRepo prodServRepo;

	@Override
	public ProdServ save(ProdServ e)
	{
		return this.prodServRepo.save(e);
	}

	@Override
	public void saveAll(List<ProdServ> lstProdServ)
	{
		this.prodServRepo.saveAll(lstProdServ);
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent, String criteria)
	{
		List<ProdServ> objectListProdutos = new LinkedList<>();

		for (String line : fileContent)
		{
			String[] item = line.split("\\|");

			ProdServ novo = new ProdServ();

			if ((item.length > 2) && (item[2] != null))
			{
				novo.setIdProdServ(item[2]);
			}
			if ((item.length > 3) && (item[3] != null))
			{
				novo.setDescricao(item[3]);
			}
			if ((item.length > 7) && (item[7] != null))
			{
				novo.setTipoProdServ(ToolsFactory.getInstance().obtemTipoItemCredito("TI" + item[7]));
			}

			objectListProdutos.add(novo);
		}

		return objectListProdutos;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdProduto;Produto;TipoDeProdutoServiço";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		ProdServ produto = (ProdServ) obj;
		return produto.getIdProdServ() + ";" + produto.getDescricao() + ";" + produto.getTipoProdServ();
	}
}
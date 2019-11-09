package com.lduran.multdb.modelservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lduran.multdb.db1.modelentity.ObjectBI;
import com.lduran.multdb.db1.modelentity.Organizacao;
import com.lduran.multdb.db1.modelrepository.OrgRepo;

@Service
public class OrganizacaoServiceImpl extends ObjectAdapter implements OrganizacaoService
{
	@Autowired
	OrgRepo orgRepo;

	@Override
	public Organizacao save(Organizacao org)
	{
		return this.orgRepo.save(org);
	}

	@Override
	public ObjectBI getObject(List<String> fileContent)
	{
		Organizacao org = new Organizacao();

		String[] item = fileContent.get(0).split("\\|");

		if ((item.length > 8) && (item[8] != null))
		{
			org.setRazaoSocial(item[8]);
		}
		if ((item.length > 9) && (item[9] != null))
		{
			org.setCnpj(item[9]);
		}
		if ((item.length > 10) && (item[10] != null))
		{
			org.setUf(item[10]);
		}
		if ((item.length > 11) && (item[11] != null))
		{
			org.setCodigoIBGE(item[11]);
		}

		return org;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdOrganizacao;Organizacao;IdCidade;UF";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		Organizacao org = (Organizacao) obj;
		return org.getCnpj() + ";" + org.getRazaoSocial() + ";" + org.getCodigoIBGE() + ";" + org.getUf();
	}
}
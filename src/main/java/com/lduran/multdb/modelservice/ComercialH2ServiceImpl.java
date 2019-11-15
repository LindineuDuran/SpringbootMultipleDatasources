package com.lduran.multdb.modelservice;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lduran.multdb.db1.modelentity.Comercial;
import com.lduran.multdb.db1.modelentity.ObjectBI;
import com.lduran.multdb.db2.modelentity.ComercialH2;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;
import com.lduran.multdb.db2.modelrepository.ComRepoH2;
import com.lduran.multdb.util.DateFactory;
import com.lduran.multdb.util.ToolsFactory;

@Service
public class ComercialH2ServiceImpl extends ObjectAdapter implements ComercialH2Service
{
	@Autowired
	ComRepoH2 comRepoH2;

	@Override
	public ComercialH2 save(ComercialH2 com)
	{
		return this.comRepoH2.save(com);
	}

	@Override
	public void saveAll(List<Comercial> lstMovCom)
	{
		lstMovCom.forEach(com ->
		{
			ComercialH2 comTemp = this.convertComercialToComercialH2(com);
			this.comRepoH2.save(comTemp);
		});
	}

	@Override
	public void delete()
	{
		this.comRepoH2.deleteAll();
	}

	public ComercialH2 convertComercialToComercialH2(Comercial com)
	{
		ComercialH2 comTemp = new ComercialH2();

		comTemp.setOrganizacao(com.getOrganizacao());
		comTemp.setOperacao(com.getOperacao());
		comTemp.setEmissao(com.getEmissao());
		comTemp.setParticipante(com.getParticipante());
		comTemp.setSituacao(com.getSituacao());
		comTemp.setProduto(com.getProduto());
		comTemp.setDataEmissao(com.getDataEmissao());
		comTemp.setDataMovimento(com.getDataMovimento());
		comTemp.setPagamento(com.getPagamento());
		comTemp.setCstCOFINS(com.getCstCOFINS());
		comTemp.setCstPIS(com.getCstPIS());
		comTemp.setAliquotaCOFINS(com.getAliquotaCOFINS());
		comTemp.setAliquotaPIS(com.getAliquotaPIS());
		comTemp.setBaseCredito(com.getBaseCredito());
		comTemp.setOrigemCredito(com.getOrigemCredito());
		comTemp.setValorTotalNFe(com.getValorTotalNFe());
		comTemp.setValorTotal(com.getValorTotal());
		comTemp.setDesconto(com.getDesconto());
		comTemp.setValorCOFINS(com.getValorCOFINS());
		comTemp.setValorPIS(com.getValorPIS());
		comTemp.setBaseCOFINS(com.getBaseCOFINS());
		comTemp.setBasePIS(com.getBasePIS());

		return comTemp;
	}

	@Override
	public List<ComercialH2Agreg> listAgreg()
	{
		List<ComercialH2Agreg> all = new LinkedList<>();
		this.comRepoH2.listAgreg().forEach((com) ->
		{
			all.add(com);
		});
		return all;
	}

	@Override
	public ComercialH2 getObject(String lineContent, ComercialH2 movCom)
	{
		ComercialH2 movComTemp = new ComercialH2();

		String codigo = lineContent.substring(1, 5);
		String[] item = null;

		switch (codigo)
		{
		case "C010":
			item = lineContent.split("\\|");
			if ((item.length > 2) && (item[2] != null))
			{
				movComTemp.setOrganizacao(item[2]);
			}

			break;

		case "C100":
			item = lineContent.replace(",", ".").split("\\|");

			movComTemp.setOrganizacao(movCom.getOrganizacao());

			if ((item.length > 2) && (item[2] != null))
			{
				movComTemp.setOperacao(item[2].equals("0") ? "Servico Contratado pelo Estabelecimento" : "Servico Prestado pelo Estabelecimento");
			}
			if ((item.length > 3) && (item[3] != null))
			{
				movComTemp.setEmissao(item[3].equals("0") ? "Propria" : "Terceiro");
			}
			if ((item.length > 4) && (item[4] != null))
			{
				movComTemp.setParticipante(item[4]);
			}
			if ((item.length > 6) && (item[6] != null))
			{
				movComTemp.setSituacao(item[6].equals("00") ? "Documento regular" : "Documento cancelado");
			}
			if ((item.length > 10) && (item[10] != null) && (item[10].length() == 8))
			{
				movComTemp.setDataEmissao(DateFactory.getInstance().dateModelConverter(item[10]));
			}
			if ((item.length > 11) && (item[11] != null) && (item[11].length() == 8))
			{
				movComTemp.setDataMovimento(DateFactory.getInstance().dateModelConverter(item[11]));
			}
			if ((item.length > 12) && (item[12] != null) && (ToolsFactory.getInstance().isNumeric(item[12])))
			{
				movComTemp.setValorTotalNFe(new BigDecimal(Double.parseDouble(item[12])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 13) && (item[13] != null))
			{
				movComTemp.setPagamento(ToolsFactory.getInstance().obtemIndicadorTipoPagamento(item[13]));
			}

			break;

		case "C175":
			item = lineContent.replace(",", ".").split("\\|");

			movComTemp.setOrganizacao(movCom.getOrganizacao());
			movComTemp.setOperacao(movCom.getOperacao());
			movComTemp.setEmissao(movCom.getEmissao());
			movComTemp.setParticipante(movCom.getParticipante());
			movComTemp.setSituacao(movCom.getSituacao());
			movComTemp.setDataEmissao(movCom.getDataEmissao());
			movComTemp.setDataMovimento(movCom.getDataMovimento());
			movComTemp.setPagamento(movCom.getPagamento());

			if ((item.length > 3) && (item[3] != null) && (ToolsFactory.getInstance().isNumeric(item[3])))
			{
				movComTemp.setValorTotal(new BigDecimal(Double.parseDouble(item[3])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 4) && (item[4] != null) && (ToolsFactory.getInstance().isNumeric(item[4])))
			{
				movComTemp.setDesconto(new BigDecimal(Double.parseDouble(item[4])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 5) && (item[5] != null))
			{
				movComTemp.setCstPIS(item[5]);
			}
			if ((item.length > 6) && (item[6] != null) && (ToolsFactory.getInstance().isNumeric(item[6])))
			{
				movComTemp.setBasePIS(new BigDecimal(Double.parseDouble(item[6])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 7) && (item[7] != null) && (ToolsFactory.getInstance().isNumeric(item[7])))
			{
				movComTemp.setAliquotaPIS(new BigDecimal(Double.parseDouble(item[7])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 10) && (item[10] != null) && (ToolsFactory.getInstance().isNumeric(item[10])))
			{
				movComTemp.setValorPIS(new BigDecimal(Double.parseDouble(item[10])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 11) && (item[11] != null))
			{
				movComTemp.setCstCOFINS(item[11]);
			}
			if ((item.length > 12) && (item[12] != null) && (ToolsFactory.getInstance().isNumeric(item[12])))
			{
				movComTemp.setBaseCOFINS(new BigDecimal(Double.parseDouble(item[12])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 13) && (item[13] != null) && (ToolsFactory.getInstance().isNumeric(item[13])))
			{
				movComTemp.setAliquotaCOFINS(new BigDecimal(Double.parseDouble(item[13])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if ((item.length > 16) && (item[16] != null) && (ToolsFactory.getInstance().isNumeric(item[16])))
			{
				movComTemp.setValorCOFINS(new BigDecimal(Double.parseDouble(item[16])).setScale(2, BigDecimal.ROUND_HALF_UP));
			}

			break;
		}

		return movComTemp;
	}

	@Override
	public String getObjectHeader()
	{
		return "IdOrganizacao;Operacao;Emissao;IdParticipante;IdSituacao;IdProduto;DataDaEmissao;DataDoMovimento;" + "Pagamento;CstCOFINS;CstPIS;AliquotaCOFINS;AliquotaPIS;BaseCalculoDoCredito;OrigemDoCredito;TotalDoDocumentoFiscal;"
				+ "TotalDoItem;ValorDesconto;ValorCOFINS;ValorPIS;BaseDeCalculoCOFINS;BaseDeCalculoPIS";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		NumberFormat formatadorNumerico = NumberFormat.getNumberInstance();

		ComercialH2 com = (ComercialH2) obj;

		try
		{
			return com.getOrganizacao() + ";" + com.getOperacao() + ";" + com.getEmissao() + ";" + com.getParticipante() + ";" + com.getSituacao() + ";" + com.getProduto() + ";" + com.getDataEmissao() + ";" + com.getDataMovimento() + ";"
					+ com.getPagamento() + ";" + com.getCstCOFINS() + ";" + com.getCstPIS() + ";" + formatadorNumerico.format(com.getAliquotaCOFINS()) + ";" + formatadorNumerico.format(com.getAliquotaPIS()) + ";" + com.getBaseCredito()
					+ ";" + com.getOrigemCredito() + ";" + formatadorNumerico.format(com.getValorTotalNFe()) + ";" + formatadorNumerico.format(com.getValorTotal()) + ";" + formatadorNumerico.format(com.getDesconto()) + ";"
					+ formatadorNumerico.format(com.getValorCOFINS()) + ";" + formatadorNumerico.format(com.getValorPIS()) + ";" + formatadorNumerico.format(com.getBaseCOFINS()) + ";" + formatadorNumerico.format(com.getBasePIS());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}

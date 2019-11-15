package com.lduran.multdb.modelstrategy;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.lduran.multdb.db1.modelentity.*;
import com.lduran.multdb.modelservice.ComercialServiceImpl;
import com.lduran.multdb.util.DateFactory;
import com.lduran.multdb.util.ToolsFactory;

public class MovComStrategy extends ComercialServiceImpl
{
	@Override
	public ObjectBI getObject(String lineContent, ObjectBI obj)
	{
		Comercial movCom = (Comercial) obj;
		Comercial movComTemp = new Comercial();
		ComercialPK comPK = new ComercialPK();

		String codigo = lineContent.substring(1, 5);
		String[] item = null;

		String serie = "";
		String numero = "";
		String chave = "";
		Integer itemNF = 0;

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
			itemNF = 0;
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
			if ((item.length > 5) && (item[5] != null))
			{
				movComTemp.setModelo(item[5]);
			}
			if ((item.length > 6) && (item[6] != null))
			{
				movComTemp.setSituacao(item[6].equals("00") ? "Documento regular" : "Documento cancelado");
			}
			if ((item.length > 7) && (item[7] != null))
			{
				serie = item[7];
			}
			if ((item.length > 8) && (item[8] != null))
			{
				numero = item[8];
			}
			if ((item.length > 9) && (item[9] != null))
			{
				chave = item[9];
			}
			if ((chave.equals("")) && (item[4] != null) && (item[7] != null) && (item[8] != null))
			{
				chave = item[4] + item[7] + item[8];
			}
			if ((item.length > 10) && (item[10] != null) && (item[10].length() == 8))
			{
				movComTemp.setDataEmissao(DateFactory.getInstance().dateModelConverter(item[10]));
			}
			if ((item.length > 11) && (item[11] != null) && (item[11].length() == 8))
			{
				movComTemp.setDataMovimento(DateFactory.getInstance().dateModelConverter(item[11]));
			}
			if ((item.length > 13) && (item[13] != null))
			{
				movComTemp.setPagamento(ToolsFactory.getInstance().obtemIndicadorTipoPagamento(item[13]));
			}
			if ((item.length > 17) && (item[17] != null))
			{
				movComTemp.setModFrete(item[17]);
			}

			// Forma a Chave Primária
			comPK = new ComercialPK(chave, itemNF, numero, serie);
			movComTemp.setComercialPK(comPK);

			break;

		case "C175":
			item = lineContent.replace(",", ".").split("\\|");

			itemNF = movCom.getComercialPK().getItemNfe();
			++itemNF;

			movComTemp.setOrganizacao(movCom.getOrganizacao());
			movComTemp.setOperacao(movCom.getOperacao());
			movComTemp.setEmissao(movCom.getEmissao());
			movComTemp.setParticipante(movCom.getParticipante());
			movComTemp.setModelo(movCom.getModelo());
			movComTemp.setSituacao(movCom.getSituacao());
			movComTemp.setDataEmissao(movCom.getDataEmissao());
			movComTemp.setDataMovimento(movCom.getDataMovimento());
			movComTemp.setPagamento(movCom.getPagamento());
			movComTemp.setModFrete(movCom.getModFrete());

			// Forma a Chave Primária
			comPK = new ComercialPK(movCom.getComercialPK().getChave(), itemNF, movCom.getComercialPK().getNumero(), movCom.getComercialPK().getSerie());
			movComTemp.setComercialPK(comPK);

			if ((item.length > 2) && (item[2] != null))
			{
				movComTemp.setCFOP(item[2]);
			}
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
	public List<? extends ObjectBI> getObjectList(List<String> fileContent)
	{
		// Nota Fiscal de Serviço - notaFiscalServico
		List<Comercial> lstMovimentacoesComerciais = new LinkedList<>();
		Comercial movCom = new Comercial();

		Integer end = fileContent.size();
		Integer line = 0;
		String[] item = null;

		while (line < end)
		{
			movCom = (Comercial) this.getObject(fileContent.get(line), movCom);

			if (movCom.getValorTotal().doubleValue() != 0)
			{
				lstMovimentacoesComerciais.add(movCom);
			}

			line++;
		}
		return lstMovimentacoesComerciais;
	}
}
package com.lduran.multdb.modelstrategy;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.lduran.multdb.db1.modelentity.*;
import com.lduran.multdb.modelservice.ComercialServiceImpl;
import com.lduran.multdb.util.DateFactory;
import com.lduran.multdb.util.ToolsFactory;

public class NotFiscServStrategy extends ComercialServiceImpl
{
	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent)
	{
		// Nota Fiscal de Serviço - notaFiscalServico
		List<Comercial> notasFiscaisServico = new LinkedList<>();
		ComercialPK comPK = new ComercialPK();
		Comercial itemNotFiscServ = new Comercial();

		Integer itemNF = 0;
		String organizacao = "";
		String serie = "";
		String numero = "";
		String chave = "";
		String participante = "";
		String modelo = "";
		String situacao = "";
		String dataEmissao = "";
		String dataMovimento = "";
		BigDecimal valorTotalNFe = new BigDecimal(0);
		String operacao = "";
		String emissao = "";
		String pagamento = "";
		String modFrete = "";
		BigDecimal frete = new BigDecimal(0);
		BigDecimal seguro = new BigDecimal(0);
		BigDecimal outrasDespesas = new BigDecimal(0);

		Integer end = fileContent.size();
		Integer line = 0;
		String[] item = null;

		while (line < end)
		{
			String codigo = fileContent.get(line).substring(1, 5);

			switch (codigo)
			{
			case "A010":
				item = fileContent.get(line).split("\\|");
				if ((item.length > 2) && (item[2] != null))
				{
					organizacao = item[2];
				}

				break;

			case "A100":
				itemNF = 0;
				item = fileContent.get(line).replace(",", ".").split("\\|");

				if ((item.length > 2) && (item[2] != null))
				{
					operacao = item[2].equals("0") ? "Servico Contratado pelo Estabelecimento" : "Servico Prestado pelo Estabelecimento";
				}
				if ((item.length > 3) && (item[3] != null))
				{
					emissao = item[3].equals("0") ? "Propria" : "Terceiro";
				}
				if ((item.length > 4) && (item[4] != null))
				{
					participante = item[4];
				}
				if ((item.length > 5) && (item[5] != null))
				{
					situacao = item[5].equals("00") ? "Documento regular" : "Documento cancelado";
				}
				if ((item.length > 6) && (item[6] != null))
				{
					serie = item[6];
				}
				if ((item.length > 8) && (item[8] != null))
				{
					numero = item[8];
				}
				if ((item.length > 9) && (item[9] != null))
				{
					chave = item[9];
				}
				if ((chave.equals("")) && (item[4] != null) && (item[6] != null) && (item[8] != null))
				{
					chave = item[4] + item[6] + item[8];
				}
				if ((item.length > 10) && (item[10] != null) && (item[10].length() == 8))
				{
					dataEmissao = DateFactory.getInstance().dateModelConverter(item[10]);
				}
				if ((item.length > 11) && (item[11] != null) && (item[11].length() == 8))
				{
					dataMovimento = DateFactory.getInstance().dateModelConverter(item[11]);
				}
				if ((item.length > 13) && (item[13] != null))
				{
					pagamento = ToolsFactory.getInstance().obtemIndicadorTipoPagamento(item[13]);
				}

				// Forma a Chave Primária
				comPK = new ComercialPK(chave, itemNF, numero, serie);

				// Forma Serviços de Transporte
				itemNotFiscServ = this.constroiObjeto(organizacao, itemNF + 1, operacao, emissao, participante, modelo, situacao, dataEmissao, dataMovimento, valorTotalNFe, frete, seguro, outrasDespesas, pagamento, modFrete, comPK);

				break;

			case "A170":
				itemNF++;
				item = fileContent.get(line).replace(",", ".").split("\\|");

				// Forma a Chave Primária
				comPK = new ComercialPK(chave, itemNF, numero, serie);

				// Forma Serviços de Transporte
				itemNotFiscServ = this.constroiObjeto(organizacao, itemNF + 1, operacao, emissao, participante, modelo, situacao, dataEmissao, dataMovimento, valorTotalNFe, frete, seguro, outrasDespesas, pagamento, modFrete, comPK);

				if ((item.length > 3) && (item[3] != null))
				{
					itemNotFiscServ.setProduto(item[3]);
				}
				if ((item.length > 5) && (item[5] != null) && (ToolsFactory.getInstance().isNumeric(item[5])))
				{
					itemNotFiscServ.setValorTotal(new BigDecimal(Double.parseDouble(item[5])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 6) && (item[6] != null) && (ToolsFactory.getInstance().isNumeric(item[6])))
				{
					itemNotFiscServ.setDesconto(new BigDecimal(Double.parseDouble(item[6])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 7) && (item[7] != null))
				{
					itemNotFiscServ.setBaseCredito(ToolsFactory.getInstance().obtemBaseCalculoCredito("BCC" + item[7]));
				}
				if ((item.length > 8) && (item[8] != null))
				{
					itemNotFiscServ.setOrigemCredito(item[8].equals("0") ? "Operacao no Mercado Interno" : "Operacao de Importacao");
				}
				if ((item.length > 9) && (item[9] != null))
				{
					itemNotFiscServ.setCstPIS(item[9]);
				}
				if ((item.length > 10) && (item[10] != null) && (ToolsFactory.getInstance().isNumeric(item[10])))
				{
					itemNotFiscServ.setBasePIS(new BigDecimal(Double.parseDouble(item[10])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 11) && (item[11] != null) && (ToolsFactory.getInstance().isNumeric(item[11])))
				{
					itemNotFiscServ.setAliquotaPIS(new BigDecimal(Double.parseDouble(item[11])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 12) && (item[12] != null) && (ToolsFactory.getInstance().isNumeric(item[12])))
				{
					itemNotFiscServ.setValorPIS(new BigDecimal(Double.parseDouble(item[12])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 13) && (item[13] != null))
				{
					itemNotFiscServ.setCstCOFINS(item[13]);
				}
				if ((item.length > 14) && (item[14] != null) && (ToolsFactory.getInstance().isNumeric(item[14])))
				{
					itemNotFiscServ.setBaseCOFINS(new BigDecimal(Double.parseDouble(item[14])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 15) && (item[15] != null) && (ToolsFactory.getInstance().isNumeric(item[15])))
				{
					itemNotFiscServ.setAliquotaCOFINS(new BigDecimal(Double.parseDouble(item[15])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if ((item.length > 16) && (item[16] != null) && (ToolsFactory.getInstance().isNumeric(item[16])))
				{
					itemNotFiscServ.setValorCOFINS(new BigDecimal(Double.parseDouble(item[16])).setScale(2, BigDecimal.ROUND_HALF_UP));
				}

				notasFiscaisServico.add(itemNotFiscServ);

				break;
			}

			line++;
		}

		return notasFiscaisServico;
	}
}
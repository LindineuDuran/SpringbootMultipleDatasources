package com.lduran.multdb.modelservice;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lduran.multdb.db1.modelentity.*;
import com.lduran.multdb.db1.modelrepository.ComRepo;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;
import com.lduran.multdb.modelstrategy.*;

@Service
public class ComercialServiceImpl extends ObjectAdapter implements ComercialService
{
	@Autowired
	ComRepo comRepo;

	@Override
	public Comercial save(Comercial com)
	{
		return this.comRepo.save(com);
	}

	@Override
	public void saveAll(List<Comercial> lstMovCom)
	{
		this.comRepo.saveAll(lstMovCom);
	}

	@Override
	public List<? extends ObjectBI> getObjectList(List<String> fileContent, String criteria)
	{
		ComercialContext ctx = new ComercialContext();

		List<Comercial> objectListMovCom = new LinkedList<>();
		switch (criteria)
		{
		case "NotaFiscalDeServico":
			ctx.setObjectService(new NotFiscServStrategy());
			break;

		case "MovimentacoesComerciais":
			ctx.setObjectService(new MovComStrategy());
			break;
		}

		objectListMovCom = (List<Comercial>) ctx.processaDados(fileContent);

		return objectListMovCom;
	}

	/**
	 * Forma objeto Movimentação Comercial com dados comuns à todas as
	 * linhas
	 *
	 * @param org
	 * @param itemNF
	 * @param operacao
	 * @param emiPropria
	 * @param participante
	 * @param modelo
	 * @param situacao
	 * @param dataEmissao
	 * @param dataMovimentacao
	 * @param valorTotalNFe
	 * @param frete
	 * @param seguro
	 * @param outrasDespesas
	 * @param pagamento
	 * @param modFrete
	 * @param comPK
	 * @return
	 */
	public Comercial constroiObjeto(String organizacao, Integer itemNF, String operacao, String emiPropria, String participante, String modelo, String situacao, String dataEmissao, String dataMovimentacao, BigDecimal valorTotalNFe,
			BigDecimal frete, BigDecimal seguro, BigDecimal outrasDespesas, String pagamento, String modFrete, ComercialPK comPK)
	{
		// Forma a Movimentação Comercial
		Comercial com = new Comercial();

		com.setComercialPK(comPK);
		com.setModelo(modelo);
		com.setDataMovimento(dataMovimentacao);
		com.setDataEmissao(dataEmissao);

		com.setEmissao(emiPropria);
		com.setOperacao(operacao);
		com.setParticipante(participante);
		com.setSituacao(situacao);
		com.setModFrete(modFrete);
		com.setPagamento(pagamento);
		com.setOrganizacao(organizacao);

		if (itemNF == 1)
		{
			com.setValorTotalNFe(valorTotalNFe);
			com.setFrete(frete);
			com.setSeguro(seguro);
			com.setOutrasDespesas(outrasDespesas);
		}

		return com;
	}

	@Override
	public Comercial convertComercialH2AgregToComercial(ComercialH2Agreg com)
	{
		Comercial comTemp = new Comercial();

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
	public String getObjectHeader()
	{
		return "IdOrganizacao;Operacao;Emissao;IdParticipante;IdSituacao;IdProduto;DataDaEmissao;DataDoMovimento;" + "Pagamento;CstCOFINS;CstPIS;AliquotaCOFINS;AliquotaPIS;BaseCalculoDoCredito;OrigemDoCredito;TotalDoDocumentoFiscal;"
				+ "TotalDoItem;ValorDesconto;ValorCOFINS;ValorPIS;BaseDeCalculoCOFINS;BaseDeCalculoPIS";
	}

	@Override
	public String getObjectContent(ObjectBI obj)
	{
		NumberFormat formatadorNumerico = NumberFormat.getNumberInstance();

		Comercial com = (Comercial) obj;

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
package com.lduran.multdb.db2.modelentity;

import java.math.BigDecimal;

public interface ComercialH2Agreg
{
	String getOrganizacao();

	String getParticipante();

	String getSituacao();

	String getProduto();

	String getBaseCredito();

	String getOrigemCredito();

	String getDataEmissao();

	String getDataMovimento();

	BigDecimal getValorTotalNFe();

	String getOperacao();

	String getEmissao();

	String getCstCOFINS();

	String getCstPIS();

	String getPagamento();

	BigDecimal getValorTotal();

	BigDecimal getDesconto();

	BigDecimal getValorCOFINS();

	BigDecimal getValorPIS();

	BigDecimal getBaseCOFINS();

	BigDecimal getBasePIS();

	BigDecimal getAliquotaCOFINS();

	BigDecimal getAliquotaPIS();
}
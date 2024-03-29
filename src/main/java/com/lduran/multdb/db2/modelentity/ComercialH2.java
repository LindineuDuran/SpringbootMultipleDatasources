package com.lduran.multdb.db2.modelentity;

import java.math.BigDecimal;

import javax.persistence.*;

import com.lduran.multdb.db1.modelentity.ObjectBI;

import lombok.*;

@Entity
@Table(name = "MovComAgreg")
@Getter
@Setter
@ToString
public class ComercialH2 extends ObjectBI
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comercial_id;

	private String organizacao = " ";
	private String participante = " ";
	private String situacao = " ";
	private String produto = " ";
	private String baseCredito = " ";
	private String origemCredito = " ";
	private String dataEmissao = " ";
	private String dataMovimento = " ";
	private BigDecimal valorTotalNFe = new BigDecimal(0);
	private String operacao = " ";
	private String emissao = " ";
	private String cstCOFINS = " ";
	private String cstPIS = " ";
	private String pagamento = " ";
	private BigDecimal valorTotal = new BigDecimal(0);
	private BigDecimal desconto = new BigDecimal(0);
	private BigDecimal valorCOFINS = new BigDecimal(0);
	private BigDecimal valorPIS = new BigDecimal(0);
	private BigDecimal baseCOFINS = new BigDecimal(0);
	private BigDecimal basePIS = new BigDecimal(0);
	private BigDecimal aliquotaCOFINS = new BigDecimal(0);
	private BigDecimal aliquotaPIS = new BigDecimal(0);
}
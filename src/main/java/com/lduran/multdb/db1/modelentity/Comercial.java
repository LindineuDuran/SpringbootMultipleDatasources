package com.lduran.multdb.db1.modelentity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "MovCom")
@Getter
@Setter
@ToString
public class Comercial extends ObjectBI implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComercialPK comercialPK;

	@Column(name = "IdOrganizacao")
	private String organizacao = "";

	@Column(name = "IdParticipante")
	private String participante = "";

	@Column(name = "IdTransportador")
	private String transportador = "";

	@Column(name = "IdOperacao")
	private String idOperacao = "";

	@Column(name = "IdStatusNFe")
	private String status = ""; // VERIFICA SE A NF ESTÁ AUTORIZADA OU CANCELADA
	@Column(name = "IdModelo")
	private String modelo = "";

	@Column(name = "IdSituacao")
	private String situacao = "";

	@Column(name = "IdVendedor")
	private String vendedor = "";

	@Column(name = "IdGrupoDeProduto")
	private String grupoProduto = "";

	@Column(name = "IdTipoDeProduto")
	private String tipoProduto = "";

	@Column(name = "IdFamilliaDeProduto")
	private String familiaProduto = "";

	@Column(name = "IdCidade")
	private String codiMunicipio = "";

	@Column(name = "IdCep")
	private String cep = "";

	@Column(name = "IdCondicaoDePagamento")
	private String condicaoPgto = "";

	@Column(name = "IdPedidoDeVenda")
	private String pedidoVenda = "";

	@Column(name = "IdOperacaoTES")
	private String operacaoTes = "";

	@Column(name = "IdProduto")
	private String produto = "";

	@Column(name = "BaseCalculoDoCredito")
	private String baseCredito = "";

	@Column(name = "OrigemDoCredito")
	private String origemCredito = "";

	@Column(name = "IdCfop")
	private String CFOP = "";

	@Column(name = "DataDaEmissao")
	private String dataEmissao = "";

	@Column(name = "HoraDaEmissao")
	private LocalTime horaEmissao;

	@Column(name = "DataDoMovimento")
	private String dataMovimento = "";

	@Column(name = "TotalDoDocumentoFiscal")
	private BigDecimal valorTotalNFe = new BigDecimal(0);

	@Column(name = "Operacao")
	private String operacao = "";

	@Column(name = "NaturezaDaOperacao")
	private String natOp = "";

	@Column(name = "Emissao")
	private String emissao = "";

	@Column(name = "CodigoDoEnquadramentoDoIPI")
	private String cEnq = "";

	@Column(name = "CstIPI")
	private String cstIPI = "";

	@Column(name = "CstICMS")
	private String cstICMS = "";

	@Column(name = "CstCOFINS")
	private String cstCOFINS = "";

	@Column(name = "CstPIS")
	private String cstPIS = "";

	@Column(name = "ModalidadeBaseDeCalculo")
	private String modBC = "";

	@Column(name = "TributacaoTotal")
	private BigDecimal tributacaoTotal = new BigDecimal(0);

	@Column(name = "Pagamento")
	private String pagamento = "";

	@Column(name = "TipoDoFrete")
	private String modFrete = "";

	@Column(name = "SufixoDoCFOP")
	private String sufixoCFOP = "";

	@Column(name = "NumeroSatEcf")
	private String satECF = "";

	@Column(name = "TipoDeDocumento")
	private String tipoDocumento = "";

	@Column(name = "ItemDoPedidoDeVenda")
	private String itemPedidoVenda = "";

	@Column(name = "Quantidade")
	private Double quantidade = 0.0;

	@Column(name = "PrecoDeTabela")
	private BigDecimal precoTabela = new BigDecimal(0);

	@Column(name = "TotalDoItemDeTabela")
	private BigDecimal totalTabela = new BigDecimal(0);

	@Column(name = "PrecoUnitario")
	private BigDecimal precoUnitario = new BigDecimal(0);

	@Column(name = "TotalDoItem")
	private BigDecimal valorTotal = new BigDecimal(0);

	@Column(name = "PercentualDoDesconto")
	private BigDecimal percentualDesconto = new BigDecimal(0);

	@Column(name = "ValorDesconto")
	private BigDecimal desconto = new BigDecimal(0);

	@Column(name = "ValorICMS")
	private BigDecimal valorICMS = new BigDecimal(0);

	@Column(name = "ValorIPI")
	private BigDecimal valorIPI = new BigDecimal(0);

	@Column(name = "ValorCOFINS")
	private BigDecimal valorCOFINS = new BigDecimal(0);

	@Column(name = "ValorPIS")
	private BigDecimal valorPIS = new BigDecimal(0);

	@Column(name = "ValorICMSST")
	private BigDecimal valorICMSST = new BigDecimal(0);

	@Column(name = "ValorPISST")
	private BigDecimal valorPISST = new BigDecimal(0);

	@Column(name = "ValorCOFINSST")
	private BigDecimal valorCOFINSST = new BigDecimal(0);

	@Column(name = "BaseDeCalculoICMS")
	private BigDecimal baseICMS = new BigDecimal(0);

	@Column(name = "BaseDeCalculoICMSST")
	private BigDecimal baseICMSST = new BigDecimal(0);

	@Column(name = "BaseDeCalculoIPI")
	private BigDecimal baseIPI = new BigDecimal(0);

	@Column(name = "BaseDeCalculoCOFINS")
	private BigDecimal baseCOFINS = new BigDecimal(0);

	@Column(name = "BaseDeCalculoPIS")
	private BigDecimal basePIS = new BigDecimal(0);

	@Column(name = "ValorDaReducaoDaBaseDeCalculo")
	private BigDecimal redBaseCalculo = new BigDecimal(0);

	@Column(name = "AliquotaICMS")
	private BigDecimal aliquotaICMS = new BigDecimal(0);

	@Column(name = "AliquotaReducaoBaseCalculo")
	private BigDecimal aliquotaRedBC = new BigDecimal(0);

	@Column(name = "AliquotaIPI")
	private BigDecimal aliquotaIPI = new BigDecimal(0);

	@Column(name = "AliquotaCOFINS")
	private BigDecimal aliquotaCOFINS = new BigDecimal(0);

	@Column(name = "AliquotaPIS")
	private BigDecimal aliquotaPIS = new BigDecimal(0);

	@Column(name = "AliquotaICMSST")
	private BigDecimal aliquotaICMSST = new BigDecimal(0);

	@Column(name = "ValorFrete")
	private BigDecimal frete = new BigDecimal(0);

	@Column(name = "ValorSeguro")
	private BigDecimal seguro = new BigDecimal(0);

	@Column(name = "ValorOutrasDespesasAcessorias")
	private BigDecimal outrasDespesas = new BigDecimal(0);

	@Column(name = "Custo")
	private BigDecimal custo = new BigDecimal(0);
}
package com.lduran.multdb.listas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseCalculoCredito
{
	BCC01("Aquisicao de bens para revenda"),
	BCC02("Aquisicao de bens utilizados como insumo"),
	BCC03("Aquisicao de servicos utilizados como insumo"),
	BCC04("Energia eletrica e termica, inclusive sob a forma de vapor"),
	BCC05("Alugueis de predios"),
	BCC06("Alugueis de maquinas e equipamentos"),
	BCC07("Armazenagem de mercadoria e frete na operacao de venda"),
	BCC08("Contraprestacoes de arrendamento mercantil"),
	BCC09("Maquinas, equipamentos e outros bens incorporados ao ativo imobilizado (credito sobre encargos de depreciacao)."),
	BCC10("Maquinas, equipamentos e outros bens incorporados ao ativo imobilizado (credito com base no valor de Aquisicao)."),
	BCC11("Amortizacao e Depreciacao de edificacoes e benfeitorias em imóveis"),
	BCC12("Devolucao de Vendas Sujeitas à Incidência Não-Cumulativa"),
	BCC13("Outras Operacoes com Direito a Credito (inclusive os creditos presumidos sobre receitas)"),
	BCC14("Atividade de Transporte de Cargas – Subcontratacao"),
	BCC15("Atividade Imobiliaria – Custo Incorrido de Unidade Imobiliaria"),
	BCC16("Atividade Imobiliaria – Custo Orçado de unidade não concluída"),
	BCC17("Atividade de Prestacao de servicos de Limpeza, Conservacao e Manutencao – vale-transporte, vale-refeicao ou vale-alimentacao, fardamento ou uniforme."),
	BCC18("Estoque de abertura de bens");

	private String descricao;
}
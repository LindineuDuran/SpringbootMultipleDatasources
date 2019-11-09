package com.lduran.multdb.util;

import java.util.*;
import java.util.stream.Collectors;

import com.lduran.multdb.listas.*;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ToolsFactory
{
	private static ToolsFactory instance;

	/**
	 * @return the instance
	 */
	public static ToolsFactory getInstance()
	{
		if (instance == null)
		{
			instance = new ToolsFactory();
		}

		return instance;
	}

	/**
	 * Check if a string is numeric [with regex]
	 *
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

	/**
	 * Create RegEx from List<String>
	 *
	 * @param grupo
	 * @return
	 */
	public StringBuilder makePattern(List<String> grupo)
	{
		// Ex.: grupo = {"|9900|", "|C500|", "|D100|", "|D500|", "|D990|"};
		StringBuilder ptn = new StringBuilder("");
		for (String grp : grupo)
		{
			// Ensures no null items will come
			if (grp != null)
			{
				// Ex.: "\\|9900\\|"
				grp = grp.replace("|", "\\|");

				if (ptn.length() == 0)
				{
					// Ex.: "^\\|9900\\|"
					ptn.append("^");
					ptn.append(grp);
				}
				else
				{
					// Ex.: "^\\|9900\\||^\\|C500\\||^\\|D500\\|"
					ptn.append("|");
					ptn.append("^");
					ptn.append(grp);
				}
			}
		}

		return ptn;
	}

	/**
	 * Get groups for the given process list
	 *
	 * @param processos
	 * @return
	 */
	public List<String> obtemGruposDeProcessos(String[] processos)
	{
		// Get groups for blocks to be processed
		List<String> grupo = new LinkedList<>();

		for (Object item : processos)
		{
			grupo.addAll(AvailableProcesses.stream().filter(d -> d.name().equals(item.toString())).map(d -> d.getGrupo()).flatMap(x -> x.stream()).collect(Collectors.toList()));
		}
		return grupo;
	}

	/**
	 * Obtem a descrição do enum TipoItem
	 *
	 * @param codigo
	 * @return
	 */
	public String obtemTipoItemCredito(String codigo)
	{
		Optional<String> descricao = EnumSet.allOf(TipoItem.class).stream().filter(bcc -> bcc.name().contains(codigo)).findFirst().map(TipoItem::getDescricao);

		return descricao.get();
	}

	/**
	 * Obtem o Indicador do tipo de pagamento
	 *
	 * @param codigo
	 * @return
	 */
	public String obtemIndicadorTipoPagamento(String codigo)
	{
		String descricao = "";

		switch (codigo)
		{
		case "0":
			descricao = "A vista";
			break;
		case "1":
			descricao = "A prazo";
			break;
		case "9":
			descricao = "Sem pagamento";
			break;
		}

		return descricao;
	}

	/**
	 * Obtem a descrição do enum BaseCalculoCredito
	 *
	 * @param codigo
	 * @return
	 */
	public String obtemBaseCalculoCredito(String codigo)
	{
		Optional<String> descricao = EnumSet.allOf(BaseCalculoCredito.class).stream().filter(bcc -> bcc.name().contains(codigo)).findFirst().map(BaseCalculoCredito::getDescricao);

		return descricao.get();
	}
}
package com.lduran.multdb.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component("fh")
public class FileHandler
{
	/**
	 * Get number of lines from file
	 *
	 * @param inputFile
	 * @return
	 */
	public int obtemQuantLinhasArq(String inputFile)
	{
		int qtdLinha = 0;

		try
		{
			File arquivoLeitura = new File(inputFile);
			LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));

			long valor = arquivoLeitura.length();

			linhaLeitura.skip(arquivoLeitura.length());
			qtdLinha = linhaLeitura.getLineNumber();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qtdLinha;
	}

	/**
	 * File reading routine using stream () and RegEx feature.
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<String> readStream(String filePath) throws IOException
	{
		// Blocos que serão processados
		String[] processos =
		{ "Organizacoes", "ProdServ", "NotaFiscalDeServico" };

		// Recebe grupos dos processos
		List<String> grupo = ToolsFactory.getInstance().obtemGruposDeProcessos(processos);

		// Make the RegEx
		StringBuilder ptn = ToolsFactory.getInstance().makePattern(grupo);

		// Get the pattern
		Pattern p = Pattern.compile(ptn.toString());

		final Path path = Paths.get(filePath);

		List<String> listFile = Files.lines(path, StandardCharsets.UTF_8).filter(p.asPredicate()).collect(Collectors.toList());

		return listFile;
	}
}

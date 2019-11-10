package com.lduran.multdb.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lduran.multdb.db2.modelentity.ComercialH2;
import com.lduran.multdb.listas.AvailableProcesses;
import com.lduran.multdb.modelservice.BuildService;
import com.lduran.multdb.modelservice.ComercialH2ServiceImpl;

@Component("fh")
public class FileHandler
{
	@Autowired
	BuildService bs;

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
	 * @param inputFile
	 * @return
	 * @throws IOException
	 */
	public List<String> readStream(String inputFile) throws IOException
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

		final Path path = Paths.get(inputFile);

		List<String> listFile = Files.lines(path, StandardCharsets.UTF_8).filter(p.asPredicate()).collect(Collectors.toList());

		return listFile;
	}

	/**
	 * Reads text file and simultaneously inserts into database
	 *
	 * @param inputFile
	 * @param qtdLineFile
	 */
	public void readAndProcessStreamOriginal(String inputFile)
	{
		// Nota Fiscal de Serviço - notaFiscalServico
		ComercialH2 movCom = new ComercialH2();

		// Receives groups from MovimentacoesComerciais
		List<String> grupo = AvailableProcesses.MovimentacoesComerciais.getGrupo();

		// Make the RegEx
		StringBuilder ptn = ToolsFactory.getInstance().makePattern(grupo);

		// Get the pattern
		Pattern p = Pattern.compile(ptn.toString());

		final Path path = Paths.get(inputFile);
		int[] contador =
		{ 0 };

		int[] qtdLineFile =
		{ 0 };
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8).filter(p.asPredicate()))
		{
			qtdLineFile[0] = (int) stream.count();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8).filter(p.asPredicate()))
		{
			stream.forEach(line ->
			{
				final ComercialH2 movComTemp = ComercialH2ServiceImpl.getObject(line, movCom);

				movCom.setOrganizacao(movComTemp.getOrganizacao());
				movCom.setOperacao(movComTemp.getOperacao());
				movCom.setEmissao(movComTemp.getEmissao());
				movCom.setParticipante(movComTemp.getParticipante());
				movCom.setSituacao(movComTemp.getSituacao());
				movCom.setDataEmissao(movComTemp.getDataEmissao());
				movCom.setDataMovimento(movComTemp.getDataMovimento());
				movCom.setPagamento(movComTemp.getPagamento());

				if ((movComTemp.getValorTotalNFe().doubleValue() != 0) || (movComTemp.getValorTotal().doubleValue() != 0))
				{
					((ComercialH2ServiceImpl) this.bs.getObjectService("ComercialH2")).save(movComTemp);
				}

				contador[0] = contador[0] + 1;

				if ((contador[0] % 1000 == 0) || (contador[0] == qtdLineFile[0]))
				{
					System.out.println("Processada linha " + contador[0] + " de " + qtdLineFile[0]);
				}
			});
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

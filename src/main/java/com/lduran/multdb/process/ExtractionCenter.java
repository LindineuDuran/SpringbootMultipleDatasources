package com.lduran.multdb.process;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lduran.multdb.util.FileHandler;

@Component
public class ExtractionCenter
{
	@Autowired
	FileHandler fh;

	@Bean
	public void start()
	{
		// Metric Start
		LocalDateTime dateTimeIni = LocalDateTime.now();
		System.out.println("START " + dateTimeIni);

		// Defines the file to be processed
		String inputFile = "C:\\GitHub\\SpringbootMultipleDatasources\\ExampleData.txt";

		// Get number of lines from file
		int qtdLineFile = this.fh.obtemQuantLinhasArq(inputFile);
		System.out.println("The file has " + qtdLineFile + " lines.");

		// Read file and generate list of contents
		List<String> file = new LinkedList<>();
		try
		{
			file = this.fh.readStream(inputFile);
			System.out.println("Launched itens: " + file.size() + " lines.");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// End of metric
		LocalDateTime dateTimeFim = LocalDateTime.now();
		System.out.println("START " + dateTimeIni + " - END " + dateTimeFim);
	}
}

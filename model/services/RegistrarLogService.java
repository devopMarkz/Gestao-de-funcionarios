package model.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrarLogService {
	
	private static final File ARQUIVOS_DE_LOG = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\atividade_GPT_heranca-polimorf.-excec.-enum\\logs.txt");
	
	public static void registraLog(String msg) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVOS_DE_LOG, true))){
			
			bw.write(msg);
			bw.newLine();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}

package services;

import java.io.*;

public class ArquivosPerguntas {
    private static final File file = new File("formulario.txt");
    private static void crairArquivo() {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("Qual o nome e sobrenome do pet?");
            bufferedWriter.newLine();
            bufferedWriter.write("Qual o tipo do pet (Cachorro/Gato)");
            bufferedWriter.newLine();
            bufferedWriter.write("Qual o sexo do animal?");
            bufferedWriter.newLine();
            bufferedWriter.write("Qual endereço e bairro que ele foi encontrado?");
            bufferedWriter.newLine();
            bufferedWriter.write("Qual a idade aproximada do pet?");
            bufferedWriter.newLine();
            bufferedWriter.write("Qual o peso aproximado do pet?");
            bufferedWriter.newLine();
            bufferedWriter.write("Qual a raça do pet?");
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo");
            throw new RuntimeException(e);
        }
    }

    public static String[] lerPerguntas() {
        if (!file.exists()){
            crairArquivo();
        }
        String[] perguntas = new String[7];
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            int i = 0;
            while ((linha = bufferedReader.readLine()) != null) {
                perguntas[i] = linha;
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return perguntas;
    }
}

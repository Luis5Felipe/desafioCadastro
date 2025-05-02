package fomulario;

import java.io.*;

public class LerFormulario {
    private static final File file = new File("formulario.txt");

    private void crairArquivo() {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("Qual o nome e sobrenome do pet?");
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
    public void lerArquivo(){
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            linha = bufferedReader.readLine();
            System.out.println(linha);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

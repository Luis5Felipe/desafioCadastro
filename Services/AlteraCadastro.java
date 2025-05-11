package Services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AlteraCadastro {

    public static void alterarPet() {
        Scanner input = new Scanner(System.in);
        int id = ArmazenarDadosEexbirDados.ExibirDados();
        File arquivo = ArmazenarDadosEexbirDados.getArquivosArmazenados().get(id - 1);
        List<String> novasLinhas = new ArrayList<>();

        try (FileReader fileReader = new FileReader(arquivo);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                if (linha.startsWith("2 - ") || linha.startsWith("3 - ")) {
                    System.out.println("Essa linha não pode ser Alterada" + linha);
                    novasLinhas.add(linha);
                } else {
                    System.out.println("Digite o que deseja altera nessa linha:" + linha
                            + " ou aperte Enter para manter");
                    String linhanova = input.nextLine();
                    if (linhanova.isBlank()) {
                        novasLinhas.add(linha);
                    } else {
                        String prefixo = linha.substring(0, 4);
                        novasLinhas.add(prefixo + linhanova);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever ao ler o arquivo: " + e.getMessage());
        }
        try (FileWriter fileWriter = new FileWriter(arquivo);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String novaLinha : novasLinhas) {
                bufferedWriter.write(novaLinha);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo: " + e.getMessage());
        }
        System.out.println("Arquivo atualizado com sucesso!");
    }
}



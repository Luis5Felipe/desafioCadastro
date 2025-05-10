package Services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlteraCadastro {
    protected static List<File> arquivosArmazenados = new ArrayList<>();

    public static void ArmazenarArquivos(File arquivos) {
        arquivosArmazenados.add(arquivos);
    }

    public static void alterarPet() {
        Scanner input = new Scanner(System.in);
        if (arquivosArmazenados.isEmpty()) {
            System.out.println("Você precisa Iniciar uma Busca Primeiro Antes de altera");
            MenuDeBusca.menu();
        }
        System.out.println("Lista de arquivos que podem ser Alterados");

        for (int index = 0; index < arquivosArmazenados.size(); index++) {
            System.out.println((index + 1) + " - " + arquivosArmazenados.get(index).getName());
        }
        boolean erro;
        int id = 0;
        do {
            erro = false;
            try {
                System.out.println("Digite o ID do arquivo");
                id = input.nextInt();
                input.nextLine();
                if (id <= 0 || id > arquivosArmazenados.size()) {
                    System.err.println("Você Digitou um ID que não existe, Faça o Busca novamente!");
                    MenuDeBusca.menu();
                }
            } catch (NumberFormatException e) {
                System.err.println("Não são aceitos números ou Símbolos aqui!");
                erro = true;
            }
        } while (erro);

        File arquivo = arquivosArmazenados.get(id - 1);
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



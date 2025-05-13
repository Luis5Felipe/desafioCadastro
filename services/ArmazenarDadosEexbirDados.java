package services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArmazenarDadosEexbirDados {
    protected static List<File> arquivosArmazenados = new ArrayList<>();
    public static void armazenarArquivos(File arquivos) {
        arquivosArmazenados.add(arquivos);
    }

    public static List<File> getArquivosArmazenados() {
        return arquivosArmazenados;
    }

    public static int ExibirDados(){
        Scanner input = new Scanner(System.in);
        if (getArquivosArmazenados().isEmpty()) {
            System.out.println("Você precisa Iniciar uma Busca Primeiro Antes de altera");
            MenuDeBusca.Menu();
        }
        System.out.println("Lista de arquivos que podem ser Alterados");

        for (int index = 0; index < getArquivosArmazenados().size(); index++) {
            System.out.println((index + 1) + " - " + getArquivosArmazenados().get(index).getName());
        }
        boolean erro;
        int id = 0;
        do {
            erro = false;
            try {
                System.out.println("Digite o ID do arquivo");
                id = input.nextInt();
                input.nextLine();
                if (id <= 0 || id > ArmazenarDadosEexbirDados.getArquivosArmazenados().size()) {
                    System.err.println("Você Digitou um ID que não existe, Faça o Busca novamente!");
                    MenuDeBusca.Menu();
                }
            } catch (NumberFormatException e) {
                System.err.println("Não são aceitos números ou Símbolos aqui!");
                erro = true;
            }
        } while (erro);
        return id;
    }
}

package Services;

import java.io.File;
import java.util.Scanner;

public class DeletarArquivos {

    public static void DeletarArquivo(){
        Scanner input = new Scanner(System.in);
        int id = ArmazenarDadosEexbirDados.ExibirDados();

        File arquivo = ArmazenarDadosEexbirDados.getArquivosArmazenados().get(id - 1);

        System.out.println("Você tem certaza que quer deletar essa arquivo: "+ arquivo.getName());
        System.out.println("Digite Sim para Deletar ou aperte Não");
        String opcao = input.nextLine();

        if (opcao.equalsIgnoreCase("sim")){
            if (arquivo.delete()){
                System.out.println("Arquivo deletado com sucesso!!");
            }else {
                System.out.println("Erro ao Deletar o arquivo");
            }
        }
    }

}

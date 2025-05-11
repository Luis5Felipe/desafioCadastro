import Services.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        while (true) {
            try {
                System.out.println();
                System.out.println("Escolha uma das opcao");
                System.out.println("1 - Cadastrar um novo pet");
                System.out.println("2 - Alterar os dados do pet cadastrado");
                System.out.println("3 - Deletar um pet cadastrado");
                System.out.println("4 - Listar todos os pets cadastrados");
                System.out.println("5 - Listar pets por algum critério (idade, nome, raça)");
                System.out.println("6 - Sair");
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Você só pode Digitar Número Inteiros");
                input.nextLine();
            }

            switch (opcao) {
                case 1:
                    CadastrarPet.ArmazenarDados();
                    break;
                case 2:
                    AlteraCadastro.alterarPet();
                    break;
                case 3:
                    DeletarArquivos.DeletarArquivo();
                    break;
                case 4:
                    ImprimirArquivos.imprimirArquivo();
                    break;
                case 5:
                    MenuDeBusca.menu();
                    break;
                case 6:
                    input.close();
                    return;
                default:
                    System.err.println("Opção inválida você só pode digitar do 1 ao 6");
            }
        }
    }
}

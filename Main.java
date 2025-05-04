import Services.BuscarPet;
import Services.CadastrarPet;
import Services.MenuDeBusca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao;
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Escolha uma das opcao");
                System.out.println("1 - Cadastrar um novo pet");
                System.out.println("2 - Alterar os dados do pet cadastrado");
                System.out.println("3 - Deletar um pet cadastrado");
                System.out.println("4 - Listar todos os pets cadastrados");
                System.out.println("5 - Listar pets por algum critério (idade, nome, raça)");
                System.out.println("6 - Sair");
                opcao = input.nextInt();
                if (opcao < 1 || opcao > 6) {
                    System.out.println("Opção inválida você só pode digitar do 1 ao 6");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Você só pode Digitar Número Inteiros");
                input.nextLine();
            }
        }
        switch (opcao){
            case 1:
                CadastrarPet.ArmazenarDados();
                break;
            case 2:
                MenuDeBusca.menu();
                break;
        }
        input.close();
    }
}

package services;

import java.util.Scanner;

public class MenuDeBusca {
    public static void Menu() {
        boolean erro;
        Scanner input = new Scanner(System.in);
        do {
            int opcao = 0;
            try {
                System.out.println("Escolha uma Opção de busca    ");
                System.out.println("1- Nome ou sobrenome");
                System.out.println("2- Sexo");
                System.out.println("3- Idade");
                System.out.println("4- Peso");
                System.out.println("5- Raça");
                System.out.println("6- Endereço");
                System.out.println("7- Nome ou sobrenome e idade  ");
                System.out.println("8- Idade e Peso");
                erro = false;
                opcao = input.nextInt();
            } catch (RuntimeException e) {
                System.err.println("Apenas números inteiros são permitidos");
                erro = true;
                input.nextLine();
            }
            switch (opcao) {
                case 1:
                    BuscarPet.buscarPetNome();
                    break;
                case 2:
                    BuscarPet.buscarPetSexo();
                    break;
                case 3:
                    BuscarPet.buscarPetIdade();
                    break;
                case 4:
                    BuscarPet.buscarPorPeso();
                    break;
                case 5:
                    BuscarPet.buscarPorRece();
                    break;
                case 6:
                    BuscarPet.buscarPetEndereco();
                    break;
                case 7:
                    BuscarPet.PesquisaPorNomeEidade();
                    break;
                case 8:
                    BuscarPet.buscarPorIdadeEpeso();
                    break;
                default:
                    System.err.println("Opção invalida, Tente novamente!");
                    erro = true;
            }
        } while (erro);
    }
}

package Services;

import java.util.Scanner;

public class MenuDeBusca {
    public static void menu() {
        boolean erro;
        int opcao = 0;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("Escolha uma Opção de busca    ");
                System.out.println("1- Nome ou sobrenome");
                System.out.println("2- Sexo");
                System.out.println("3- Idade");
                System.out.println("4- Peso");
                System.out.println("5- Raça");
                System.out.println("6- Endereço");
                System.out.println("Busca Dupla 7 Nome ou sobrenome e idade ou 8 - Idade e Peso");
                erro = false;
                opcao = input.nextInt();
            } catch (RuntimeException e) {
                System.err.println("Apenas números inteiros são permitidos");
                erro = true;
                input.nextLine();
            }
        } while (erro);

        switch (opcao){
            case 1:
                BuscarPet.buscarPetNome();
                break;
            case 2:
                BuscarPet.buscarPetSexo();
                break;
            case 3:
                BuscarPet.buscarPetIdade();
                break;
        }



    }
}

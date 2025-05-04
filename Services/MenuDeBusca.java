package Services;

import java.util.Scanner;

public class MenuDeBusca {
    public static void menu() {
        boolean erro;
        int opcao = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Escolha uma Opção de busca 1- Nome ou sobrenome 2- Sexo 3- Idade 4- Peso  5- Raça 6- Endereço");
        System.out.println("Busca Dupla 7 Nome ou sobrenome e idade ou 8 - Idade e Peso");
        do {
            try {
                erro = false;
                opcao = 1; //input.nextInt();
            } catch (RuntimeException e) {
                System.err.println("Apenas números inteiros são permitidos");
                erro = true;
            }
        } while (erro);

        switch (opcao){
            case 1:
                BuscarPet.buscarPetNome();
                break;
        }



    }
}

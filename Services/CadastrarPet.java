package Services;

import Entidades.Pet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastrarPet {
    private static String nome;

    public void ArmazenarDados() {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();
        String[] perguntas = ArquivosPerguntas.lerPerguntas();
        System.out.println(perguntas[0]);
        nome = input.nextLine().trim();
        String[] contadorPalavras = nome.split("\\s");
        if (contadorPalavras.length < 2) {
            System.out.println("Você deve inserir o sobrenome também");
        }
        String regex = "\\w";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = Pattern.matches(nome);

    }
}

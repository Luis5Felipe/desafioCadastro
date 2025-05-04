package Services;

import Utils.TipoAnimal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BuscarPet {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final File pasta = new File("D:\\Projetos\\Desafios\\desafioCadastro\\petsCadastrados");
    public static void buscarPetNome() {
        System.out.println("Digite o nome ou sobrenome ou apenas um deles");
        String nome = INPUT.nextLine();
        System.out.println("Tipo de Animal");
        String tipo = INPUT.nextLine();
        if (TipoAnimal.CACHORRO.getTipo().equalsIgnoreCase(tipo)) {
            tipo = TipoAnimal.CACHORRO.getTipo();
        } else {
            tipo = TipoAnimal.GATO.getTipo();
        }
        if (pasta.exists() && pasta.isDirectory()) {
            File[] arquivos = pasta.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                        String linha;

                        while ((linha = bufferedReader.readLine()) != null) {
                            if (nome.equalsIgnoreCase(linha.replace("1 - ", ""))
                                    && (linha = bufferedReader.readLine()) != null &&
                                    tipo.equalsIgnoreCase(linha.replace("2 - ", "").trim())) {
                                        imprimirArquivo(arquivo);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    private static void imprimirArquivo(File arquivo){
        String linha;
        try (FileReader fileReader = new FileReader(arquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package Services;

import Entidades.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ArmazenarCadastro {

    public static void armazenarCadastro(Pet pet){
        LocalDateTime localDateTime = LocalDateTime.now();
        String data = localDateTime
                .withNano(0)
                .withSecond(0)
                .toString()
                .replace("-", "")
                .replace(":", "");

        String arquivo = data + "-" + pet.getNome().replace(" ","").toUpperCase() + ".txt";
        File file = new File("D:\\Projetos\\Desafios\\desafioCadastro\\petsCadastrados\\" + arquivo);

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("1 - " + pet.getNome());
            bufferedWriter.newLine();
            bufferedWriter.write("2 - " + pet.getTipoAnimal());
            bufferedWriter.newLine();
            bufferedWriter.write("3 - " + pet.getSexoPet());
            bufferedWriter.newLine();
            bufferedWriter.write("4 - " + pet.getEndereco());
            bufferedWriter.newLine();
            bufferedWriter.write("5 - " + pet.getIdade()+" anos");
            bufferedWriter.newLine();
            bufferedWriter.write("6 - " + pet.getPeso()+"kg");
            bufferedWriter.newLine();
            bufferedWriter.write("7 - " + pet.getRace());
            bufferedWriter.newLine();
            System.out.println("Pet Cadastrado Com Sucesso!");
            System.out.println("Nome do arquivo: "+arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar o arquivo: "+ e.getMessage());
        }

    }

}

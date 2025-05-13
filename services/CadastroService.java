package services;

import entidades.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroService {

    public static void armazenarPet(Pet pet){
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

    public static void alteraPet() {
        boolean petAlterado = false;
        Scanner input = new Scanner(System.in);
        int id = ArmazenarDadosEexbirDados.ExibirDados();
        File arquivo = ArmazenarDadosEexbirDados.getArquivosArmazenados().get(id - 1);
        List<String> novasLinhas = new ArrayList<>();

        try (FileReader fileReader = new FileReader(arquivo);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                processarLinha(linha,input,novasLinhas);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever ao ler o arquivo: " + e.getMessage());
        }

        try (FileWriter fileWriter = new FileWriter(arquivo);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String novaLinha : novasLinhas) {
                bufferedWriter.write(novaLinha);
                bufferedWriter.newLine();
                petAlterado = true;
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo: " + e.getMessage());
        }
        if (petAlterado){
            System.out.println("Cadastro alterado com sucesso");
        }
    }

    private static void processarLinha(String linha, Scanner input, List<String> novasLinhas){
        if (linha.startsWith("2 - ") || linha.startsWith("3 - ")) {
            System.out.println("Essa linha não pode ser Alterada" + linha);
            novasLinhas.add(linha);
        } else {
            System.out.println("Digite o que deseja altera nessa linha:" + linha
                    + " ou aperte Enter para manter");
            String linhanova = input.nextLine();
            if (linhanova.isBlank()) {
                novasLinhas.add(linha);
            } else {
                String prefixo = linha.substring(0, 4);
                novasLinhas.add(prefixo + linhanova);
            }
        }
    }
}

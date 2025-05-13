package services;

import entidades.Pet;
import utils.exception.IdadeException;
import utils.exception.PesoException;
import utils.SexoPet;
import utils.TipoAnimal;

import java.util.Scanner;

public class CadastrarPet {
    public static void ArmazenarDados() {
        Pet pet = new Pet();
        Scanner input = new Scanner(System.in);

        String[] perguntas = ArquivosPerguntas.lerPerguntas();
        boolean erro;

        do {
            erro = false;
            String regex = ".*[^a-zA-ZÀ-ÿ\\s].*";
            System.out.println(perguntas[0]);
            String nome = input.nextLine();
            String[] contadorPalavras = nome.split("\\s");
            if (contadorPalavras.length < 2) {
                System.out.println("Você deve inserir o sobrenome também");
                erro = true;
            } else if (nome.matches(regex)) {
                System.out.println("Você não pode inserir números no nome");
                erro = true;
            } else {
                pet.setNome(nome);
            }
        } while (erro);

        do {
            erro = false;
            System.out.println(perguntas[1]);
            String tipoAnimal = input.nextLine();
            if (TipoAnimal.CACHORRO.getTipo().equalsIgnoreCase(tipoAnimal)) {
                pet.setTipoAnimal(TipoAnimal.CACHORRO);
            } else if (TipoAnimal.GATO.getTipo().equalsIgnoreCase(tipoAnimal)) {
                pet.setTipoAnimal(TipoAnimal.GATO);
            } else {
                System.out.println("Você deve Digita {Cachorro} ou {Gato}");
                erro = true;
            }
        } while (erro);

        do {
            erro = false;
            System.out.println(perguntas[2]);
            String tipoSexoAnimal = input.nextLine();
            if (SexoPet.MACHO.getSexoPet().equalsIgnoreCase(tipoSexoAnimal)) {
                pet.setSexoPet(SexoPet.MACHO);
            } else if (SexoPet.FEMEA.getSexoPet().equalsIgnoreCase(tipoSexoAnimal)) {
                pet.setSexoPet(SexoPet.FEMEA);
            } else {
                System.out.println("Você deve Digita {Macho} ou {Fêmea}");
                erro = true;
            }
        } while (erro);

        do {
            erro = false;
            final String naoInformado = "NÃO INFORMADO";
            String regex = ".*[^0-9a-zA-ZÀ-ÿ\\s,].*";
            String[] endereco = new String[3];
            System.out.println(perguntas[3]);
            System.out.println("Número da casa");
            endereco[0] = input.nextLine();
            if(endereco[0] == null){
                endereco[0] = naoInformado;
            }
            System.out.println("Cidade");
            endereco[1] = input.nextLine();
            System.out.println("Rua");
            endereco[2] = input.nextLine();
            String juntarStrings = endereco[0] + "," + endereco[1] + "," + endereco[2];
            if (endereco[1] == null || endereco[2] == null) {
                System.out.println("Você deve inserir o endereço completo, Exemplo [555 Rio de Janeiro Rua J]");
                erro = true;
            } else if (juntarStrings.matches(regex)) {
                System.out.println("Você não pode inserir Símbolos no endereço");
                erro = true;
            } else {
                pet.setEndereco(juntarStrings);
            }
        } while (erro);

        do {
            try {
                erro = false;
                System.out.println(perguntas[4]);
                String idade = input.nextLine();
                double idadeDouble = Double.parseDouble(idade.replace(",", "."));
                if (idadeDouble > 20) {
                    throw new IdadeException("Idade Maior que 20 anos");
                } else if (idadeDouble < 0) {
                    throw new IdadeException("Idade não pode ser negativa");
                }
                if (idadeDouble < 12) {
                    double idadeEmAnos = idadeDouble / 12;
                    idadeEmAnos = Math.floor(idadeEmAnos * 10.0) / 10.0;
                    pet.setIdade(idadeEmAnos);
                }else {
                    pet.setIdade(idadeDouble);
                }
            } catch (NumberFormatException e) {
                System.err.println("Você não pode digita números ou letras aqui");
                erro = true;
            } catch (IdadeException e) {
                System.err.println(e.getMessage());
                erro = true;
            }
        } while (erro);

        do {
            try {
                erro = false;
                System.out.println(perguntas[5]);
                String peso = input.nextLine();
                double pesoDouble = Double.parseDouble(peso.replace(",", "."));
                if (pesoDouble > 60 || pesoDouble < 0.5) {
                    throw new PesoException("O peso deve estar entre 0.5kg e 60kg.");
                }
                pet.setPeso(pesoDouble);
            } catch (NumberFormatException e) {
                System.err.println("Formato inválido. Digite um número válido para o peso.");
                erro = true;
            } catch (PesoException e) {
                System.err.println(e.getMessage());
                erro = true;
            }
        } while (erro);

        do {
            erro = false;
            String regex = ".*[^a-zA-ZÀ-ÿ\\\\s].*";
            System.out.println(perguntas[6]);
            String rece = input.nextLine();
            if (rece.matches(regex)){
                System.out.println("Você não pode usar simbólos ou números");
                erro = true;
            }
            pet.setRace(rece);
        }while (erro);
        CadastroService.armazenarPet(pet);
    }
}

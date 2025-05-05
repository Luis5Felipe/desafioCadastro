package Services;

import Utils.TipoAnimal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarPet {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final File pasta = new File("D:\\Projetos\\Desafios\\desafioCadastro\\petsCadastrados");
    private static final File[] arquivos = pasta.listFiles();
    private static boolean encontrado = false;
    private static int contador = 0;



    private static void imprimirArquivo(File arquivo) {
        String linha;
        List<String> registros;
        try (FileReader fileReader = new FileReader(arquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            registros = new ArrayList<String>();
            String juntarRegistro = "";
            while ((linha = bufferedReader.readLine()) != null) {
                registros.add(linha.replaceAll("^\\d+\\s*-\\s*", ""));
                juntarRegistro = String.join(" - ", registros);
            }
            contador++;
            System.out.println(contador + ". " + juntarRegistro);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






    public static void buscarPetSexo() {
        String tipo;
        System.out.println("Tipo de Animal");
        tipo = INPUT.nextLine();
        if (TipoAnimal.CACHORRO.getTipo().equalsIgnoreCase(tipo) || TipoAnimal.GATO.getTipo().equalsIgnoreCase(tipo)) {
            System.out.println("Lista de possiveis resultados");
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    imprimirArquivo(arquivo);
                }
            }
        } else {
            System.out.println("Tipo incorreto. Só é aceito cachorro ou gato.");
        }
    }

    public static void buscarPetIdade() {
        boolean erro;
        double idadeDouble = 0;
        int cont = 0;
        do {
            erro = false;
            System.out.println("Idade do Animal");
            try {
                String idade = INPUT.nextLine();
                idadeDouble = Double.parseDouble(idade);
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Digite um número válido para a idade.");
                erro = true;
            }
        } while (erro);
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try (FileReader fileReader = new FileReader(arquivo);
                     BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                    String linha;
                    while ((linha = bufferedReader.readLine()) != null) {

                        if ((!linha.startsWith(" - "))){
                            cont++;
                        }
                        if (cont == 5) {
                            try {
                                String idadeStr = linha.replace("anos", "").replace("5 - ","").trim();
                                double idadeLida = Double.parseDouble(idadeStr);
                                if (idadeLida == idadeDouble) {
                                    imprimirArquivo(arquivo);
                                    encontrado = true;
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Existe idade mal formatada no arquivo: " + linha);
                                break;
                            }
                        }
                    }
                    cont = 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (!encontrado){
            System.out.println("Nenhum Animal Encontrado");
        }
    }


    public static void buscarPetNome() {
        System.out.println("Digite o nome ou sobrenome ou apenas um deles");
        String nome = INPUT.nextLine().trim();
        String tipo;
        while (true) {
            System.out.println("Tipo de Animal");
            tipo = INPUT.nextLine();
            if (TipoAnimal.CACHORRO.getTipo().equalsIgnoreCase(tipo)) {
                tipo = TipoAnimal.CACHORRO.getTipo();
                break;
            } else if (TipoAnimal.GATO.getTipo().equalsIgnoreCase(tipo)) {
                tipo = TipoAnimal.GATO.getTipo();
                break;
            } else {
                System.out.println("Tipo incorreto. Só é aceito cachorro ou gato.");
            }
        }


        System.out.println("Lista de possiveis resultados");
        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                        String linha;
                        String[] nomesParaVerificar = nome.split(",");
                        while ((linha = bufferedReader.readLine()) != null) {
                            if (linha.startsWith("1 - ")) {
                                String nomeDoArquivo = linha.substring(4).toLowerCase();
                                for (String palavra : nomesParaVerificar) {
                                    if (nomeDoArquivo.contains(palavra.toLowerCase())) {
                                        String linhaTipo = bufferedReader.readLine();
                                        if (linhaTipo != null && linhaTipo.startsWith("2 - ")) {
                                            String tipoNoArquivo = linhaTipo.substring(4).trim().toLowerCase();
                                            if (tipoNoArquivo.equals(tipo.toLowerCase())) {
                                                imprimirArquivo(arquivo);
                                                encontrado = true;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com o nome {" + nome + "} e tipo {" + tipo + "}");
        }
    }


}

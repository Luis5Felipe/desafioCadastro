package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BuscarPet {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final File pasta = new File("D:\\Projetos\\Desafios\\desafioCadastro\\petsCadastrados");
    private static final File[] arquivos = pasta.listFiles();
    private static boolean encontrado = false;
    private static int contador = 0;
    private static boolean erro = false;
    private static final String regex = ".*[^a-zA-ZÀ-ÿ\\\\s].*";


    private static void imprimirArquivo(File arquivo) {
        String linha;
        List<String> registros;
        try (FileReader fileReader = new FileReader(arquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            registros = new ArrayList<>();
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

    public static void buscarPetNome() {
        String tipo;
        String nome;
        do {
            erro = false;
            System.out.println("Digite o nome ou sobrenome ou apenas um deles");
            nome = INPUT.nextLine().toLowerCase().trim();
            System.out.println("Tipo de Animal");
            tipo = INPUT.nextLine().toLowerCase().trim();
            if (nome.matches(regex) || tipo.matches(regex)) {
                System.out.println("Você não pode Digitar Números ou Símbolos aqui");
                erro = true;
            }
        } while (erro);

        System.out.println("Lista de possiveis resultados");

        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo);
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                        String linha;
                        String[] nomesParaVerificar = nome.split(",");

                        while ((linha = bufferedReader.readLine()) != null) {

                            if (linha.startsWith("1 - ")) {

                                String nomeDoArquivo = linha.substring(4).toLowerCase();
                                for (String palavra : nomesParaVerificar) {

                                    if (nomeDoArquivo.contains(palavra.toLowerCase())) {

                                        String TipoAnimal = bufferedReader.readLine();

                                        if (TipoAnimal != null && TipoAnimal.startsWith("2 - ")) {

                                            String tipoNoArquivo = TipoAnimal.substring(4).trim().toLowerCase();

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
        } else {
            System.err.println("Diretório não encontrado");
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com o nome {" + nome + "} e tipo {" + tipo + "}");
        }
    }

    public static void buscarPetSexo() {
        String sexo;
        String tipo;
        int cont = 0;
        do {
            erro = false;
            ;
            System.out.println("Sexo do Animal");
            sexo = INPUT.nextLine().toLowerCase();
            System.out.println("Tipo Do Animal");
            tipo = INPUT.nextLine().toLowerCase();
            System.out.println("Lista de possiveis resultados");
            if (sexo.matches(regex) || tipo.matches(regex)) {
                System.out.println("Você não pode Digitar Números ou Símbolos aqui");
                erro = true;
            }
        } while (erro);

        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo);
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                        String linha;
                        String TipoAnimal = "";

                        while ((linha = bufferedReader.readLine()) != null) {
                            if (!linha.startsWith(" - ")) {
                                cont++;
                            }
                            if (linha.startsWith("2 - ")) {
                                TipoAnimal = linha;
                            }
                            if (cont == 3 && linha.replace("3 - ", "").toLowerCase().equals(sexo) &&
                                    TipoAnimal.replace("2 - ", "").toLowerCase().equals(tipo)) {

                                imprimirArquivo(arquivo);
                                encontrado = true;
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Falha ao tentar Ler o arquivo!!");
                    }
                    cont = 0;
                }
            }
        } else {
            System.err.println("Diretório não encontrado");
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com o nome {" + sexo + "} e tipo {" + tipo + "}");
        }
    }

    public static void buscarPetIdade() {
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
        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo);
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                        String linha;
                        while ((linha = bufferedReader.readLine()) != null) {

                            if ((!linha.startsWith(" - "))) {
                                cont++;
                            }
                            if (cont == 5) {
                                try {
                                    String idadeStr = linha.replace("anos", "").replace("5 - ", "").trim();
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
        } else {
            System.err.println("Diretório não encontrado");
        }
        if (!encontrado) {
            System.out.println("Nenhum Animal Encontrado");
        }
    }

    public static void buscaPorPeso() {
        double peso = 0;
        int cont = 0;
        do {
            erro = false;
            System.out.println("Digite o peso do Animal");
            try {
                peso = INPUT.nextDouble();
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Digite um número válido para o peso.");
                erro = true;
            }
        } while (erro);
        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo);
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                        String linha;
                        while ((linha = bufferedReader.readLine()) != null) {

                            if ((!linha.startsWith(" - "))) {
                                cont++;
                            }
                            if (cont == 6) {
                                try {
                                    String pesoStr = linha.replace("kg", "").replace("6 - ", "").trim();
                                    double pesoLido = Double.parseDouble(pesoStr);
                                    if (pesoLido == peso) {
                                        imprimirArquivo(arquivo);
                                        encontrado = true;
                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println("Existe peso mal formatada no arquivo: " + linha);
                                    break;
                                }
                            }
                        }
                        cont = 0;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.err.println("Diretório não encontrado");
            }
            if (!encontrado) {
                System.out.println("Nenhum Animal Encontrado");
            }
        }
    }

    public static void buscaPorRece() {
        int cont = 0;
        String rece;
        String tipo;
        do {
            erro = false;
            System.out.println("Dite a Raça do animal");
            rece = INPUT.nextLine().toLowerCase();
            System.out.println("Tipo de Animal");
            tipo = INPUT.nextLine().toLowerCase();
            if (rece.matches(regex) || tipo.matches(regex)) {
                System.out.println("Você não pode usar simbólos ou números");
                erro = true;
            }
        } while (erro);
        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo);
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                        String linha;
                        String tipoAnimal = "";
                        while ((linha = bufferedReader.readLine()) != null) {

                            if (!linha.startsWith(" - ")) {
                                cont++;
                            }
                            if (linha.startsWith("2 - ")) {
                                tipoAnimal = linha;
                            }
                            if (cont == 7) {
                                if (rece.equals(linha.replace("7 - ", "").trim().toLowerCase())
                                        && tipoAnimal.replace("2 - ", "").toLowerCase().equals(tipo)) {
                                    imprimirArquivo(arquivo);
                                    encontrado = true;
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
        } else {
            System.err.println("Diretório não encontrado");
        }
        if (!encontrado) {
            System.out.println("Nenhum Animal Encontrado");
        }
    }

    public static void BuscarPetEndereco() {
        String tipo;
        String endereco;
        int cont = 0;
        String regex = ".*[^0-9a-zA-ZÀ-ÿ\\s,].*";
        do {
            erro = false;
            System.out.println("Digite o endereço");
            endereco = INPUT.nextLine().trim().toLowerCase();
            System.out.println("Tipo de Animal");
            tipo = INPUT.nextLine().toLowerCase().trim();
            if (endereco.matches(regex) || tipo.matches(regex)) {
                System.out.println("Você não pode Digitar Símbolos aqui");
                erro = true;
            }
        } while (erro);

        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        String tipoAnimal = "";
                        String[] enderecoDividido = endereco.trim().split(",");
                        while ((linha = bufferedReader.readLine()) != null) {

                            if (linha.startsWith("2 - ")) {
                                tipoAnimal = linha.substring(4).trim();
                            }
                            if (linha.startsWith("4 - ")) {
                                String enderecoNoArquivo = linha.substring(4).trim().toLowerCase();
                                for (String parteEndereco : enderecoDividido) {
                                    if (enderecoNoArquivo.contains(parteEndereco.trim().toLowerCase())) {
                                        if (tipoAnimal.equalsIgnoreCase(tipo)) {
                                            imprimirArquivo(arquivo);
                                            encontrado = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
                    }
                }
            } else {
                System.err.println("Não Existe arquivos a ser pesquisado");
            }
        } else {
            System.err.println("Diretório não encontrado");
        }
        if (!encontrado) {
            System.out.println("Nenhum Animal Encontrado");
        }
    }

    public static void PesquisaPorNomeEidade() {
        String tipo;
        String nome;
        double idadeDouble = 0;
        do {
            erro = false;
            System.out.println("Digite o nome ou sobrenome ou apenas um deles");
            nome = INPUT.nextLine().toLowerCase().trim();
            System.out.println("Tipo de Animal");
            tipo = INPUT.nextLine().toLowerCase().trim();
            try {
                System.out.println("Digite a idade");
                String idade = INPUT.nextLine();
                idadeDouble = Double.parseDouble(idade);
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Digite um número válido para a idade.");
                erro = true;
            }
            if (nome.matches(regex) || tipo.matches(regex)) {
                System.out.println("Você não pode Digitar Números ou Símbolos aqui");
                erro = true;
            }
        } while (erro);

        System.out.println("Lista de possiveis resultados");

        if (pasta.exists() && pasta.isDirectory()) {
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (FileReader fileReader = new FileReader(arquivo);
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                        String linha;
                        String[] nomesParaVerificar = nome.split(",");
                        String nomeDoArquivo = "";
                        String tipoNoArquivo = "";
                        double idadeLida = 0;
                        while ((linha = bufferedReader.readLine()) != null) {
                            if (linha.startsWith("1 - ")) {
                                nomeDoArquivo = linha.substring(4).toLowerCase();
                            } else if (linha.startsWith("2 - ")) {
                                tipoNoArquivo = linha.substring(4).trim().toLowerCase();
                            } else if (linha.startsWith("5 - ")) {
                                try {
                                    String idadeStr = linha.replace("anos", "").substring(4).trim();
                                    idadeLida = Double.parseDouble(idadeStr);
                                    for (String palavra : nomesParaVerificar) {
                                        if (nomeDoArquivo.contains(palavra.toLowerCase()) && tipoNoArquivo.equals(tipo.toLowerCase()) && idadeDouble == idadeLida) {
                                            imprimirArquivo(arquivo);
                                            encontrado = true;
                                            break;
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println("Existe idade mal formatada no arquivo: " + arquivo.getName());
                                }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                System.err.println("Não Existe arquivos a ser pesquisado");
            }
        } else {
            System.err.println("Diretório não encontrado");
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com o nome {" + nome + "} e tipo {" + tipo + "}" + "{" + idadeDouble + "}");
        }
    }



}

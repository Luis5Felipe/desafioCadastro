package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Services.ArmazenarDadosEexbirDados.ArmazenarArquivos;
import static Services.ArmazenarDadosEexbirDados.arquivosArmazenados;
import static Services.ImprimirArquivos.ImprimirArquivo;

public class BuscarPet {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final File pasta = new File("D:\\Projetos\\Desafios\\desafioCadastro\\petsCadastrados");
    private static boolean encontrado = false;
    private static boolean erro = false;
    private static final String regex = ".*[^a-zA-ZÀ-ÿ\\s].*";
    private static String tipo;
    private static String linha;

    private static List<File> VerificarArquivos() {
        List<File> lista = new ArrayList<>();
        if (pasta.exists() && pasta.isDirectory()) {
            File[] arquivos = pasta.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile()) {
                        lista.add(arquivo);
                    }
                }
            } else {
                System.err.println("Não existe arquivos no Diretório");
            }
        } else {
            System.err.println("Diretório não encontrado");
        }
        return lista;
    }

    public static void BuscarPetNome() {
        String nome;
        arquivosArmazenados.clear();
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
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String[] nomesParaVerificar = nome.split(",");
                String tipoAnimal = "";
                String nomeDoArquivo = "";
                encontrado = false;
                while ((linha = bufferedReader.readLine()) != null && !encontrado) {
                    if (linha.startsWith("1 - ")) {
                        nomeDoArquivo = linha.substring(4).toLowerCase();
                    } else if (linha.startsWith("2 - ")) {
                        tipoAnimal = linha.substring(4).toLowerCase().trim();
                    }
                    for (String palavra : nomesParaVerificar) {
                        if (nomeDoArquivo.contains(palavra.toLowerCase()) && tipo.equals(tipoAnimal)) {
                            ImprimirArquivo(arquivo);
                            ArmazenarArquivos(arquivo);
                            encontrado = true;
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Falha ao tentar Ler o arquivo!!" + e.getMessage());
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com o nome {" + nome + "} e tipo {" + tipo + "}");
        }
    }

    public static void BuscarPetSexo() {
        String sexo;
        arquivosArmazenados.clear();
        do {
            erro = false;
            System.out.println("Sexo do Animal");
            sexo = INPUT.nextLine().toLowerCase().trim();
            System.out.println("Tipo Do Animal");
            tipo = INPUT.nextLine().toLowerCase().trim();
            System.out.println("Lista de possiveis resultados");
            if (sexo.matches(regex) || tipo.matches(regex)) {
                System.out.println("Você não pode Digitar Números ou Símbolos aqui");
                erro = true;
            }
        } while (erro);
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String tipoAnimal = "";
                encontrado = false;
                while ((linha = bufferedReader.readLine()) != null) {
                    if (linha.startsWith("2 - ")) {
                        tipoAnimal = linha.substring(4).toLowerCase();
                    } else if (linha.startsWith("3 - ")) {
                        if (sexo.equals(linha.substring(4).toLowerCase()) && tipoAnimal.equals(tipo)) {
                            ImprimirArquivo(arquivo);
                            ArmazenarArquivos(arquivo);
                            encontrado = true;
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Falha ao tentar Ler o arquivo!!" + e.getMessage());
            }
        }
        if (!encontrado) {
            System.err.println("Nenhum animal encontrado com o nome {" + sexo + "} e tipo {" + tipo + "}");
        }
    }

    public static void BuscarPetIdade() {
        double idadeDouble = 0;
        arquivosArmazenados.clear();
        String idade;
        do {
            erro = false;
            System.out.println("Qual é o tipo do animal");
            tipo = INPUT.nextLine();
            try {
                System.out.println("Idade do Animal");
                idade = INPUT.nextLine();
                idadeDouble = Double.parseDouble(idade);
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Digite um número válido para a idade.");
                erro = true;
            }
        } while (erro);
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String tipoAnimal = "";
                String idadeStr;
                while ((linha = bufferedReader.readLine()) != null) {
                    if (linha.startsWith("2 - ")) {
                        tipoAnimal = linha.substring(4).toLowerCase();
                    } else if (linha.startsWith("5 - ")) {
                        idadeStr = linha.replace("anos", "").replace("5 - ", "").trim();
                        try {
                            double idadeLida = Double.parseDouble(idadeStr);
                            if (idadeLida == idadeDouble && tipoAnimal.equals(tipo)) {
                                ImprimirArquivo(arquivo);
                                ArmazenarArquivos(arquivo);
                                encontrado = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Existe idade mal formatada no arquivo: " + linha);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Falha ao tentar Ler o arquivo!!" + e.getMessage());
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com essa {" + idadeDouble + "} e  tipo {" + tipo + "}");
        }
    }

    public static void BuscarPorPeso() {
        double peso = 0;
        arquivosArmazenados.clear();
        do {
            erro = false;
            System.out.println("Digite o tipo do animal");
            tipo = INPUT.nextLine();
            try {
                System.out.println("Digite o peso do Animal");
                peso = INPUT.nextDouble();
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Digite um número válido para o peso.");
                erro = true;
            }
        } while (erro);
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String tipoAnimal = "";
                while ((linha = bufferedReader.readLine()) != null) {

                    if (linha.startsWith("2 - ")) {
                        tipoAnimal = linha.substring(4).toLowerCase();
                    } else if (linha.startsWith("6 - ")) {
                        String pesoStr = linha.replace("kg", "").replace("6 - ", "").trim();
                        try {
                            double pesoLido = Double.parseDouble(pesoStr);
                            if (pesoLido == peso && tipoAnimal.equals(tipo)) {
                                ImprimirArquivo(arquivo);
                                ArmazenarArquivos(arquivo);
                                encontrado = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Existe peso mal formatada no arquivo: " + linha);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Falha ao tentar Ler o arquivo!!" + e.getMessage());
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com esse {" + peso + "} e  tipo {" + tipo + "}");
        }
    }

    public static void BuscarPorRece() {
        String rece;
        arquivosArmazenados.clear();
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
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String linha;
                String tipoAnimal = "";
                while ((linha = bufferedReader.readLine()) != null) {
                    if (linha.startsWith("2 - ")) {
                        tipoAnimal = linha.substring(4).toLowerCase();
                    } else if (linha.startsWith("7 - ")) {
                        if (rece.equals(linha.substring(4).toLowerCase()) && tipoAnimal.equals(tipo)) {
                            ImprimirArquivo(arquivo);
                            ArmazenarArquivos(arquivo);
                            encontrado = true;
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Falha ao tentar Ler o arquivo!!" + e.getMessage());
            }
            if (!encontrado) {
                System.out.println("Nenhum animal encontrado com a raça {" + rece + "} e  tipo {" + tipo + "}");
            }
        }
    }

    public static void BuscarPetEndereco() {
        String endereco;
        arquivosArmazenados.clear();
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
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {
                String tipoAnimal = "";
                String linha;
                String[] enderecoDividido = endereco.trim().toLowerCase().split(",");
                while ((linha = bufferedReader.readLine()) != null) {
                    if (linha.startsWith("2 - ")) {
                        tipoAnimal = linha.substring(4).trim();
                    } else if (linha.startsWith("4 - ")) {
                        String enderecoNoArquivo = linha.substring(4).trim().toLowerCase();
                        for (String parteEndereco : enderecoDividido) {
                            if (enderecoNoArquivo.contains(parteEndereco.trim().toLowerCase())) {
                                if (tipoAnimal.equalsIgnoreCase(tipo)) {
                                    ImprimirArquivo(arquivo);
                                    ArmazenarArquivos(arquivo);
                                    encontrado = true;
                                }
                                break;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Falha ao tentar Ler o arquivo!!" + e.getMessage());
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com a Endereço {" + endereco + "} tipo {" + tipo + "}");
        }
    }

    public static void PesquisaPorNomeEidade() {
        String tipo;
        String nome;
        double idadeDouble = 0;
        arquivosArmazenados.clear();
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
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String linha;
                String[] nomesParaVerificar = nome.split(",");
                String nomeDoArquivo = "";
                String tipoNoArquivo = "";
                double idadeLida;
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
                                    ImprimirArquivo(arquivo);
                                    ArmazenarArquivos(arquivo);
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
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com o nome {" + nome + "} e tipo {" + tipo + "}" + "{" + idadeDouble + "}");
        }
    }

    public static void BuscarPorIdadeEpeso() {
        double peso = 0;
        double idade = 0;
        String tipo;
        arquivosArmazenados.clear();
        do {
            erro = false;
            System.out.println("Digite o tipo do Animal");
            tipo = INPUT.nextLine().toLowerCase();
            try {
                System.out.println("Digite o peso do Animal");
                peso = INPUT.nextDouble();
                System.out.println("Digite a Idade do Animal");
                idade = INPUT.nextDouble();
                if (tipo.matches(regex)) {
                    System.err.println("Você não pode Digitar Símbolos aqui");
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Digite um número válido para o peso.");
                erro = true;
            }
        } while (erro);
        List<File> arquivos = VerificarArquivos();
        for (File arquivo : arquivos) {
            try (FileReader fileReader = new FileReader(arquivo);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String linha;
                String tipoAnimal = "";
                double pesoLido = 0;
                double idadeLido = 0;
                while ((linha = bufferedReader.readLine()) != null) {
                    try {
                        if (linha.startsWith("2 - ")) {
                            tipoAnimal = linha.substring(4).toLowerCase().trim();
                        } else if (linha.startsWith("5 - ")) {
                            String idadeStr = linha.replace("anos", "").replace("5 - ", "").trim();
                            idadeLido = Double.parseDouble(idadeStr);
                        } else if (linha.startsWith("6 - ")) {
                            String pesoStr = linha.replace("kg", "").replace("6 - ", "").trim();
                            pesoLido = Double.parseDouble(pesoStr);
                        }
                        if (pesoLido == peso && idade == idadeLido && tipo.equals(tipoAnimal)) {
                            ImprimirArquivo(arquivo);
                            ArmazenarArquivos(arquivo);
                            encontrado = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter número em: " + linha + " no arquivo: " + arquivo.getName());
                        break;
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com a idade {" + idade + "} e peso {" + peso + "} tipo {" + tipo + "}");
        }
    }
}
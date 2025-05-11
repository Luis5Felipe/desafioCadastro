package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImprimirArquivos {
    private static int contador = 0;

    public static void imprimirArquivo() {
        File arquivos = new File("D:\\Projetos\\Desafios\\desafioCadastro\\petsCadastrados");
        File[] arquivo = arquivos.listFiles();
        if (arquivo != null){
            for (File arq : arquivo){
                imprimirArquivo(arq);
            }
        }
    }

    public static void imprimirArquivo(File arquivo) {
        String linha;
        List<String> registros  = new ArrayList<>();
        String juntarRegistro = "";
        try (FileReader fileReader = new FileReader(arquivo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
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


}

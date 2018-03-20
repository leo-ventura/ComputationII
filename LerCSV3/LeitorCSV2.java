import java.sql.Timestamp;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class LeitorCSV2 {

    public static class Dados {
        Timestamp[] ts;
        String[] nomeColuna;
        double[][] colunas;
    }

    public Dados lerDoArquivo( String fileName, String separator ) {
        Dados dados = new Dados();
        FileInputStream stream = new FileInputStream("arquivo.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String[] linha = br.readLine();

        for(int i=0; i++ < linha.length;) {
            System.out.println(i);
        }
        return null;
    }

    public static void main(String[] args) {
        lerDoArquivo("ex.txt", "|");
    }
}

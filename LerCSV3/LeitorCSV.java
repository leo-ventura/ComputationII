import java.sql.Timestamp;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Arrays;

public class LeitorCSV {
    public static class Dados {
        Timestamp[] ts;
        String[] nomeColuna;
        double[][] colunas;
    }
    public Dados lerDoArquivo( String fileName, String separador ) throws Exception{
        Dados dados = new Dados();
        Path p = Paths.get("", fileName);
        Charset c = Charset.forName("UTF-8");
        List<String> lines = Files.readAllLines(p, c);
        String[] l = reArray(lines.get(0).split("[" + separador + "]"));
        int tsIndex = Arrays.asList(l).indexOf("ts");
        ArrayList<Integer> orderToFollow = getOrder(l);
        dados.colunas = new double[orderToFollow.size()][lines.size()-1];
        dados.ts = new Timestamp[lines.size()-1];
        dados.nomeColuna = new String[orderToFollow.size()];
        for(int i = 0; i < orderToFollow.size(); i++) dados.nomeColuna[i] = l[orderToFollow.get(i)];
        for(int i = 1; i < lines.size(); i++) {
            String[] linha = lines.get(i).split("[" + separador + "]");
            if(linha[tsIndex].substring(0,10).contains(".")) linha[tsIndex] = linha[tsIndex].substring(0,10).replace('.', '-') + linha[tsIndex].substring(10);
            dados.ts[i-1] = Timestamp.valueOf(linha[tsIndex]);
            for(int j = 0; j < orderToFollow.size(); j++) dados.colunas[j][i-1] = Double.parseDouble(linha[orderToFollow.get(j)]);
        }
        return dados;
    }
    public ArrayList<Integer> getOrder(String[] array) {
        ArrayList<Integer> ord = new ArrayList<>();
        if(Arrays.asList(array).contains("avg")) ord.add(Arrays.asList(array).indexOf("avg"));
        if(Arrays.asList(array).contains("open")) ord.add(Arrays.asList(array).indexOf("open"));
        if(Arrays.asList(array).contains("high")) ord.add(Arrays.asList(array).indexOf("high"));
        if(Arrays.asList(array).contains("low")) ord.add(Arrays.asList(array).indexOf("low"));
        if(Arrays.asList(array).contains("close")) ord.add(Arrays.asList(array).indexOf("close"));
        if(Arrays.asList(array).contains("vol")) ord.add(Arrays.asList(array).indexOf("vol"));
        for(String x : array) {
            if(!x.equals("ts")   && !x.equals("avg") && !x.equals("open") && !x.equals("high") && !x.equals("low") && !x.equals("close") && !x.equals("vol")) ord.add(Arrays.asList(array).indexOf(x));
        }
        return ord;
    }
    public String[] reArray(String[] array) {
        for(int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase("ts")       || array[i].equalsIgnoreCase("timestamp")
             || array[i].equalsIgnoreCase("datahora") || array[i].equalsIgnoreCase("time-stamp")) {
                array[i] = "ts";
            } else if (array[i].equalsIgnoreCase("avg")   || array[i].equalsIgnoreCase("average")
                    || array[i].equalsIgnoreCase("media") || array[i].equalsIgnoreCase("média")
                    || array[i].equalsIgnoreCase("preco_medio")) {
                array[i] = "avg";
            } else if (array[i].equalsIgnoreCase("open") || array[i].equalsIgnoreCase("abertura")
                    || array[i].equalsIgnoreCase("o")) {
                array[i] = "open";
            } else if (array[i].equalsIgnoreCase("high")   || array[i].equalsIgnoreCase("max")
                    || array[i].equalsIgnoreCase("máximo") || array[i].equalsIgnoreCase("maximo")) {
                array[i] = "high";
            } else if (array[i].equalsIgnoreCase("low") || array[i].equalsIgnoreCase("min")
                    || array[i].equalsIgnoreCase("mínima")) {
                array[i] = "low";
            } else if (array[i].equalsIgnoreCase("close") || array[i].equalsIgnoreCase("fecho")
                    || array[i].equalsIgnoreCase("fechamento")) {
                array[i] = "close";
            } else if (array[i].equalsIgnoreCase("vol") || array[i].equalsIgnoreCase("volume")) {
                array[i] = "vol";
            }
        }
        return array;
    }
}

import java.util.*;
import java.lang.*;
import java.io.*;

public class AvaliadorDeTextos {
    ArrayList<String> texto = new ArrayList<>();
    ArrayList<String> stopWords = new ArrayList<>();


    int numeroDePalavras = 0;
    ArrayList<String> palavrasTotal = new ArrayList<>();
    ArrayList<String> palavrasDistintas = new ArrayList<>();
    ArrayList<String> palavrasFrequentes = new ArrayList<>();
    ArrayList<String> stopWordsFrequentes = new ArrayList<>();

    Map<String, Integer> mapWords = new TreeMap<String, Integer>();
    Map<String, Integer> mapStopWords = new TreeMap<String, Integer>();

    public AvaliadorDeTextos(ArrayList<String> text, ArrayList<String> sw) {
        this.texto = text;
        this.stopWords = sw;
        quantasPalavras();
        sortWords();
        sortStopWords();
    }

    public void quantasPalavras() {
        int n = getNumeroDeLinhas();
        for(int i = 0; i < n; i++) {
            String palavras[] = texto.get( i ).split( "[. ,;!?\t-:]+" );
            numeroDePalavras += palavras.length;
            for(int j = 0; j < palavras.length; j++) {
                palavrasTotal.add(palavras[j].toLowerCase());
                if(!palavrasDistintas.contains(palavras[j].toLowerCase())) {
                    palavrasDistintas.add(palavras[j].toLowerCase());
                }
            }
        }
    }


    public int getNumeroDeLinhas() {
        return texto.size();
    }

    public int getNumeroDePalavras() {
        return numeroDePalavras;
    }

    public int getNumeroDePalavrasDistintas() {
        return palavrasDistintas.size();
    }

    public ArrayList<String> getPalavrasMaisFrequentes( int n ) {
        ArrayList<String> arr = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr.add(palavrasFrequentes.get(i));
        }
        return arr;
    }

    public ArrayList<String> getStopWordsMaisFrequentes( int n ) {
        ArrayList<String> arr = new ArrayList<>();
        for(int i = 0; i < Math.min(stopWordsFrequentes.size(), n); i++) {
            arr.add(stopWordsFrequentes.get(i));
        }
        return arr;
    }

    public Map<String, Integer> getFrequenciaDePalavras() {
        return mapWords;
    }

    public Map<String, Integer> getFrequenciaDeStopWords() {
        return mapStopWords;
    }



    public void sortStopWords() {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        int k = palavrasDistintas.size();
        for(int i = 0; i < k; i++) {
            if(stopWords.contains(palavrasDistintas.get(i))) {
                map.put(palavrasDistintas.get(i), Collections.frequency(palavrasTotal, palavrasDistintas.get(i)));
            }
        }

        for(Map.Entry<String, Integer> entry  : entriesSortedByValues(map)) {
            stopWordsFrequentes.add(entry.getKey());
            mapStopWords.put(entry.getKey(), entry.getValue());
        }
    }

    public void sortWords() {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        int k = palavrasDistintas.size();
        for(int i = 0; i < k; i++) {
            if(!stopWords.contains(palavrasDistintas.get(i))) {
                map.put(palavrasDistintas.get(i), Collections.frequency(palavrasTotal, palavrasDistintas.get(i)));
            }
        }

        for(Map.Entry<String, Integer> entry  : entriesSortedByValues(map)) {
            palavrasFrequentes.add(entry.getKey());
            mapWords.put(entry.getKey(), entry.getValue());
        }
    }

    static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
        new Comparator<Map.Entry<K,V>>() {
            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                int res = e2.getValue().compareTo(e1.getValue());
                if (e2.getKey().equals(e1.getKey())) {
                    return res; // Code will now handle equality properly
                } else {
                    return res != 0 ? res : 1; // While still adding all entries
                }
            }
        }
    );
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
    }
}

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Possato {

    static Map<String, Map<Integer, ?>> leArquivo(Path p, Charset c, String separador) throws Exception {
        Map<String, Map<Integer, ?>> map = new TreeMap<>();
        Map<Integer, Object> menorMap = new TreeMap<>();
        int id = 0;
        Class<?> classe = null;
        List<String> lines = Files.readAllLines(p, c);
        String[] campos = null;
        boolean newClass = false;
        for (String line : lines) {
            if (line.equals("")) {
                menorMap = new TreeMap<>();
                newClass = false;
                continue;
            } else {
                if (!newClass) {
                    campos = line.split(separador);
                    newClass = true;
                } else {
                    String[] elementos = line.split(separador);
                    Object o = null;
                    for (int i = 0; i < elementos.length; i++) {
                        if (campos[i].equalsIgnoreCase("class")) {
                            classe = Class.forName(elementos[i]);
                            o = classe.newInstance();
                        } else {
                            if (campos[i].equalsIgnoreCase("id")) {
                                id = Integer.parseInt(elementos[i]);
                            }
                            boolean exist = false;
                            for (Method m : classe.getMethods()) {
                                if (m.getName().equalsIgnoreCase("set" + campos[i])) {
                                    try {
                                        // System.out.println("o -> " + o + " / !elementos["+ i + "]! -> " +
                                        // elementos[i] + "\n");
                                        m.invoke(o, elementos[i]);
                                        exist = true;
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            if (!exist) {
                                throw new Exception(
                                        "Campo " + campos[i] + " não encontrado na classe " + classe.getName());
                            }
                        }
                    }
                    System.out.println("id -> " + id + " | o -> " + o + "\n");
                    menorMap.put(id, o);
                    map.put(classe.getName(), menorMap);
                }
            }
        }
        System.out.println("Real output:\n" + map);
        return map;
    }

    public static void main(String[] args) throws Exception {
        Path p = Paths.get("", "entrada.txt");
        Charset c = Charset.forName("UTF-8");
        leArquivo(p, c, ";");
    }
}
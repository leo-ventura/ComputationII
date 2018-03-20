import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.lang.reflect.Method;

public class Leitor {
    public static Map<String, Map<Integer, ?>> leArquivo( Path p, Charset c, String separador ) throws Exception {
        Map<String, Map<Integer, ?>> r = new TreeMap<>();
        Map<Integer,Object> map = new TreeMap<>();
        
        List<String> lines = Files.readAllLines(p,c);
        
        ArrayList<String> stringMetodos = findMetodos(lines.get(0).split(separador));
        Class<?> classe = findClasse(lines.get(0).split(separador), lines, separador, 0);
        int classeIndex = findClasseIndex(lines.get(0).split(separador));
        ArrayList<Method> arrayMetodos = findArrayMetodos(stringMetodos, classe, classeIndex);
        int idIndex = findIdIndex(lines.get(0).split(separador));

        //System.out.println("Entrando no for das linhas");
        for(int i=1; i < lines.size(); i++) {
            //System.out.println(" linha: " + (i+1));
            if(lines.get(i).length() < 1) {
                r = putMap(r,map,classe);
                map = new TreeMap<>();
                classe = findClasse(lines.get(i + 1).split(separador), lines, separador, i + 1);
                stringMetodos = findMetodos(lines.get(i + 1).split(separador));
                classeIndex = findClasseIndex(lines.get(i + 1).split(separador));
                arrayMetodos = findArrayMetodos(stringMetodos, classe, classeIndex);
                idIndex = findIdIndex(lines.get(i + 1).split(separador));
                i+=2;
            }
            Object obj = instanciaObjeto(classeIndex, classe, arrayMetodos, lines, i, separador, idIndex);
            map.put(Integer.valueOf(lines.get(i).split(separador)[idIndex]), obj);
        }
        return r = putMap(r,map,classe);
    }

    public static Map<String, Map<Integer, ?>> putMap(Map<String,Map<Integer,?>> r, Map<Integer,Object> map, Class<?> classe) {
        if(!r.containsKey(classe.getName()))
            r.put(classe.getName(), map);
        else {
            Map<Integer, Object> aux = new TreeMap<>();
            for (Map.Entry<Integer, ?> m : r.get(classe.getName()).entrySet()) {
                aux.put(m.getKey(), m.getValue());
            }
            aux.putAll(map);
            r.put(classe.getName(), aux);
        }
        return r;
    }

    public static Object instanciaObjeto(int classeIndex, Class<?> classe, ArrayList<Method> arrayMetodos, List<String> lines, int i, String separador,
                                         int idIndex) throws Exception{
        Object obj = classe.newInstance();
        for (int j = 0; j < arrayMetodos.size()+1; j++) {
            try {
                if(j == classeIndex) continue;
                if(j == idIndex) {
                    arrayMetodos.get(idIndex).invoke(obj, Integer.valueOf(lines.get(i).split(separador)[idIndex]));
                } else arrayMetodos.get(j).invoke(obj, lines.get(i).split(separador)[j]);
            } catch (Exception e) {}
        }
        return obj;
    }

    public static Class<?> findClasse(String[] stringMetodos, List<String> lines, String separador, int index) throws Exception{
        for (int i = 0; i < stringMetodos.length; i++) {
            if (stringMetodos[i].equalsIgnoreCase("class")) {
                return Class.forName(lines.get(index+1).split(separador)[i]);
            }
        }
        return null;
    }

    public static int findClasseIndex(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase("class")) return i;
        }
        return -1;
    }

    public static int findIdIndex(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase("id")) return i;
        }
        return -1;
    }

    public static ArrayList<String> findMetodos(String[] array) {
        ArrayList<String> stringMetodos = new ArrayList<>();
        for(String x : array) {
            if(!x.equalsIgnoreCase("class")) {
                stringMetodos.add(x);
            }
        }
        return stringMetodos;
    }

    public static ArrayList<Method> findArrayMetodos(ArrayList<String> stringMetodos, Class<?> classe, int classeIndex) throws Exception{
        ArrayList<Method> arrayMetodos = new ArrayList<>();
        for(String campo : stringMetodos) {
            boolean found = false;
            for(Method m : classe.getMethods()) {
                if (m.getName().equalsIgnoreCase("set" + campo)) {
                    found = true;
                    arrayMetodos.add(m);
                }
            }
            if(!found) throw new Exception("Campo " + campo + " não encontrado na classe " + classe.getName());
        }
        arrayMetodos.add(classeIndex, null);
        return arrayMetodos;
    }

    public static void main(String[] args) throws Exception{
        Path p = Paths.get("", "entrada.txt");
        Charset c = Charset.forName("UTF-8");
        leArquivo(p, c, ";");
    }
}

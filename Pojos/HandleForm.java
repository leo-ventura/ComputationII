import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.reflect.*;

public class HandleForm {
    public static Object processaEntrada() throws Exception {
        try (Scanner s = new Scanner(System.in);) {
            String in[] = s.nextLine().split("[\",\"]+[\":\"]+");
            for (int i = 0; i < in.length; i++)
                in[i] = in[i].replaceAll("\"", "");
            TreeMap<String,String> map = new TreeMap<>();
            for(int i=2; i<in.length-1;i+=2)
                map.put(in[i], in[i+1]);
            return instanciaForm(in[1], map);
        }
    }
    public static Object instanciaForm(String nome, Map<String, String> map) throws Exception {
        Class<?> classe = null;
        try {
            classe = Class.forName(nome);
        } catch(ClassNotFoundException e) {
            return null;
        }
        Object f=classe.newInstance();
        try {
            for(Map.Entry<String,String> e : map.entrySet()) {
                Method m = null;
                try {
                    m = classe.getMethod("set" + capitalize(e.getKey()), String.class);
                    m.invoke(f,e.getValue());
                } catch(NoSuchMethodException excep) {
                    return "Campo não encontrado: " + e.getKey() + " em " + nome;
                }
            }
            return f;
        } catch(Exception e) {}
        return null;
    }
    public static String capitalize(String x) {
        return Character.toUpperCase(x.charAt(0)) + x.substring(1);
    }
}

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.lang.reflect.Method;

public class Leitor {
    public static ArrayList<Object> leArquivo(Path p, Charset c, String separador, Class<?> classe) throws Exception { //metodo pra juntar todos certinho
        ArrayList<Object> array = new ArrayList<>();
        List<String> lines = Files.readAllLines(p, c);
        String stringCampos[] = lines.get(0).split(separador);
        ArrayList<Method> setMethod = new ArrayList<>();

        for (String campo : stringCampos) {
            boolean foundSet = false;
            boolean foundGet = false;
            for (Method m : classe.getMethods()) {
                if (m.getName().equalsIgnoreCase("set" + campo)) {
                    foundSet = true;
                    setMethod.add(m);
                }
                if (m.getName().equalsIgnoreCase("get" + campo)) {
                    foundGet = true;
                }
            }
            if (!foundSet || !foundGet && !campo.equals("id"))
                throw new Exception("Campo " + campo + " não encontrado na classe " + classe.getName());
        }

        for(int i=1; i < lines.size(); i++) {
            Object obj = classe.newInstance();
            for(int j=0; j < stringCampos.length; j++) {
                try {
                    setMethod.get(j).invoke(obj,lines.get(i).split(separador)[j]);
                } catch(Exception e) {}
            }
            array.add(obj);
        }
        return array;
    }

    public static void main(String[] args) throws Exception{
        Path p = Paths.get("", "entrada.txt");
        Charset c = Charset.forName("UTF-8");
        leArquivo(p, c, "\t", FormAluno.class);
        //System.out.println(leArquivo(p, c, "\t", FormAluno.class));
    }
}
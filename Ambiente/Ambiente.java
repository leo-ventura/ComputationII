public class Ambiente {
    public static Object instanciar(String nome, Object...args) throws Exception {
        Object o = null;
        for(Constructor<?> construc : Class.forName(nome).getConstructors()) {
            try {
                o = construc.newInstance(args);
            } catch(Exception e) {}
        }
        return o;
    }
}
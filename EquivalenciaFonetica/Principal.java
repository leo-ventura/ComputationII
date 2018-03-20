import java.util.Map;
import java.util.TreeMap;

public class Principal {

    public static void main( String[] args ) {
        Map<String, String> m = new TreeMap<>();

        m.put( "ch", "x" );
        m.put( "ç", "s" );

        SomEquivalente porSom = new SomEquivalente( m );

        System.out.println( porSom.equivalente( null, ""));

        //System.out.println(porSom.equivalente("salsa", "çalça"));
    }

}

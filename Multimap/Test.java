import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sun.awt.OSInfo.OSType;

public class Test {

    @Test
    public void test_putGet_01() {
        Multimap<String, Integer> m = new TreeMultimap<>();

        m.put( "Jan", 31 );
        assertEquals( 1, m.get( "Jan" ).size() );

        m.put( "Fev", 28, 29 );
        assertEquals( 2, m.get( "Fev" ).size() );

        m.put( "Fev", 28, 29 );
        assertEquals( 4, m.get( "Fev" ).size() );
        assertEquals( "[28, 29, 28, 29]", m.get( "Fev" ).toString() );

        assertEquals( null, m.get( "Abr" ) );
    }    
    @Test
    public void test_putGet_02() {
        Multimap<String, Integer> m = new TreeMultimap<>();

        m.put( "Jan", 31, (Integer[]) null );
        assertEquals( "[31]", m.get( "Jan" ).toString() );
    }

    @Test
    public void test_replace_01() {
        Multimap<String, Integer> m = new TreeMultimap<>();

        m.put( "Jan", 29, 31 );

        m.replace( "Jan", 1, 2 );
        assertEquals( "[1, 2]", m.get( "Jan" ).toString() );
    }

    @Test
    public void test_replace_02() {
        Multimap<String, Integer> m = new TreeMultimap<>();

        m.put( "Jan", 29, 31 );

        m.replace( "Jan" );
        assertEquals( null, m.get( "Jan" ) );
    }
}
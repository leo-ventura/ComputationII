import java.util.Arrays;
import java.util.Comparator;

public class Principal {

    public static void main( String[] args ) {
        Data d[] = { new Data(), new Data(), new Data(), new Data() };

        d[0].setData( 22, 12, 1998 );
        d[1].setData( 3, 9, 2017 );
        d[2].setData( 28, 8, 2017);


        for( Data x : new RangeData( d[0], d[1] ) )
            System.out.println( x );
    }

}

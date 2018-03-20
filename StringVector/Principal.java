import java.util.*;
import java.lang.*;
import java.io.*;

public class Principal {
  public static void main(String[] args) throws VectorSizeException {
    StringVector v = new StringVector(3);

    //  teste 2
    v.put( 0, "A" );
    v.put( 1, "B" );
    v.put( 2, "C" );

    /* teste 4 */
    v.newSize( 6 );
    v.put( 1, "" );
    v.put( 5, "D" );
    v.newSize( 4 );
    System.out.println( "newSize com buracos ok" );

    /* teste 3
    v.newSize( 6 );
    v.put( 0, "" );
    v.put( 5, "D" );
    v.newSize( 1 );*/

    /* teste 2
    v.newSize( 4 );
    v.put( 0, "" );
    v.newSize( 1 );*/

    /* teste 1
    v.newSize( 4 );
    if( v.at( 3 ).equals( "" ) )
      System.out.println( "newSize ok" );*/
  }
}

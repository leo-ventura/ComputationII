import java.util.*;
import java.lang.*;
import java.lang.reflect.*;

public class Principal {
    public static void main(String[] args) 
            throws Exception {
        Ambiente a = new Ambiente();
        Object b = a.instanciar("Amb", 1);
        System.out.println(b);
    }
}
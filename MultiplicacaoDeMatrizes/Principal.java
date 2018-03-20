import java.util.Random;
import java.util.Map;
import java.util.TreeMap;

public class Principal {
    public static void main(String[] args) throws Exception {
        MultiplicaMatriz calc = new MultiplicaMatriz( 4 );
        double[][] c = null;
                                                                                // mas sai:
        double[][] a = {{2, 3}, {4,5}};         // [ 2 3 ]     [ 10 10 ]          [ 40 0 ]
                                                // [ 4 5 ]     [ 18 18 ]          [ 0 0 ]

        double[][] b = {{2, 2}, {2,2}};                      // [ 2 2 ]
                                                             // [ 2 2 ]

        //double[][] a = randomMatrix(3,3);
        //double[][] b = randomMatrix(3,2);

        System.out.println("== a ==");
        for (double[] ai : a) {
            for (double aj : ai)
                System.out.print(aj + " ");
            System.out.println();
        }
        System.out.println("== b ==");
        for (double[] bi : b) {
            for (double bj : bi)
                System.out.print(bj + " ");
            System.out.println();
        }

        calc.divisaoDeTarefas( 2, 2 );
        c = calc.calcula( a, b );

        System.out.println("== c ==");
        for (double[] ci : c) {
            for (double cj : ci)
                System.out.print(cj + " ");
            System.out.println();
        }

        //System.out.println("Tempo gasto: " + calc.tempoGasto());
        //System.out.println("Tempo detalhado: " + calc.tempoDetalhado());

        if (calc.tempoDetalhado() != null && calc.tempoDetalhado().size() > 0) {
            System.out.println("Tempo detalhado:");
            Map<String, Long> m = new TreeMap<>();
            for (Long t : calc.tempoDetalhado()) {
                String v = Long.valueOf(t).toString() + "ms";
                m.put(v, m.get(v) == null ? 1 : m.get(v) + 1);
            }
            System.out.println(m);
        }
    }

    public static double[][] randomMatrix(int n, int m) {
        Random r = new Random();
        double[][] out = new double[n][];
        for (int i = 0; i < n; i++) {
            out[i] = new double[m];
            for (int j = 0; j < m; j++) {
                out[i][j] = 100 * r.nextDouble();
            }
        }
        return out;
    }
}
import java.util.concurrent.Callable;

public class Tarefa implements Callable<Long> {
    private long tempo = 0;

    public Tarefa( double a[][], double b[][], double c[][], int inicioLinha, int fimLinha, int inicioColuna, int fimColuna ) throws Exception{
        long t = call();
        for (int i = inicioLinha; i < fimLinha; i++) { // percorrendo o ai
            for (int j = inicioColuna; j < fimColuna; j++) { // percorrendo o bj
                if(i >= a.length) continue; // caso invalido
                for (int k = 0; k < a[0].length; k++) { // percorrendo o aj
                    // testando casos invalidos
                    if(k >= a[0].length
                    || k >= b.length
                    || i >= c.length
                    || j >= c[0].length
                    || j >= b[k].length) continue;
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        this.tempo = call() - t;
    }

    public long getTime() {
        return this.tempo;
    }

    @Override
    public Long call() throws Exception {
        // Tempo gasto em milisegundos para executar essa tarefa
        long milisegundos = System.currentTimeMillis();

        return milisegundos;
    }
}
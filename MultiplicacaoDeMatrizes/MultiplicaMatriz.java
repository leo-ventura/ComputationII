import java.util.ArrayList;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiplicaMatriz {
    Thread[] threads = null;
    int numCols = 0;
    int numLinhas = 0;
    ArrayList<Tarefa> tasks = new ArrayList<>();

    public MultiplicaMatriz( int nThreads ) {
        this.threads = new Thread[nThreads];
    }

    public double[][] calcula( double a[][], double b[][] ) throws Exception { // to multiply aj == bi, ci = ai and cj = bj
        if(a[0].length != b.length) throw new Exception( "Não é possível multiplicar as matrizes: " + a.length + "x" + a[0].length + " por " + b.length
            + "x" + b[0].length ); // handle exception

        double[][] c = new double[a.length][b[0].length];
        int nProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(nProcessors);

        for(int i = 0; i < a.length / numLinhas + 1; i++) {
            for(int j = 0; j < b[0].length / numCols + 1; j++) {
                tasks.add(new Tarefa(a, b, c, i*numLinhas, (i+1)*numLinhas, j*numCols, (j+1)*numCols));
            }
        }

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();

        return c;
    }

    public void divisaoDeTarefas( int numCols, int numLinhas ) {
        this.numCols = numCols;
        this.numLinhas = numLinhas;
    }

    public long tempoGasto() {
        ArrayList<Long> t = tempoDetalhado();
        long tempoGasto = 0;
        for(long l : t) tempoGasto += l;
        return tempoGasto;
    }

    public ArrayList<Long> tempoDetalhado() {
        ArrayList<Long> time = new ArrayList<>();
        for(Tarefa t : tasks) time.add(t.getTime());
        return time;
    }

}


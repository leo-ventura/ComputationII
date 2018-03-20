public class Principal {
    public static void main(String[] args) throws Exception{
        LeitorCSV leitor = new LeitorCSV();
        leitor.lerDoArquivo("ex3.txt", ",");
    }
}
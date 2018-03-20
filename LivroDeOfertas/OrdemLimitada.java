public class OrdemLimitada {
    public int cliente, corretora, quantidade;
    public double preco;
    public LivroDeOfertas.Direcao direcao;

    public OrdemLimitada( int cliente, int numCorretora, LivroDeOfertas.Direcao compra, int i, double d ) {
        this.cliente = cliente;
        this.corretora = numCorretora;
        this.direcao = compra;
        this.quantidade = i;
        this.preco = d;
    }

    public void setLote(int l) {
        this.quantidade = l;
    }
}
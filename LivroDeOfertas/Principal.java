public class Principal {

    public static void main( String[] args ) {
        LivroDeOfertas livro = new LivroDeOfertas( 0.5 );
        int numCorretora = 45;
        int cliente = 23;


        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 5, 14.97));
        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 2, 14.97));

        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 2, 14.98));
        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 4, 14.98));

        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 15, 14.97));
        

        //livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 500, 14.97 ) );
        
        System.out.println( livro.getPrecoCompra() );
        System.out.println( livro.getPrecoVenda() );
        System.out.println( livro.getQuantidadeCompra( 14.97 ) );
        System.out.println( livro.getQuantidadeVenda( 14.98 ) );
    }
}

/*    private static void setup01() {
        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 5, 14.97));
        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 2, 14.97));

        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 2, 14.98));
        livro.registra(new OrdemLimitada(cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 4, 14.98));
    }

    private static void setup02() {
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 1, 14.95 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 4, 14.96 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 1, 14.96 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 1, 14.96 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.COMPRA, 1, 14.97 ) );

        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 1, 15.01 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 3, 14.99 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 2, 14.99 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 1, 14.98 ) );
        livro.registra( new OrdemLimitada( cliente, numCorretora, LivroDeOfertas.Direcao.VENDA, 3, 14.99 ) );
    }*/
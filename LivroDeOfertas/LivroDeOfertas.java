import java.util.*;

public class LivroDeOfertas {
    public static enum Direcao {
        COMPRA, VENDA;
    };
    
    private Map<Double, ArrayList<OrdemLimitada>> compra; //quem quer comprar
    private Map<Double, ArrayList<OrdemLimitada>> venda; //quem quer vender

    public LivroDeOfertas( double tick ) {
        this.compra = new TreeMap<>();
        this.venda = new TreeMap<>();
    }

    public void registra( OrdemLimitada ordemLimitada ) {
            if(ordemLimitada.direcao == LivroDeOfertas.Direcao.VENDA) { //caso de venda
                if(ordemLimitada.preco <= getPrecoCompra()) { //quem quer vender, quer vender por um preco menor ou igual ao de quem quer comprar
                    System.out.println("o valor " + ordemLimitada.preco + "entrou no execvenda");
                    execVenda(ordemLimitada);
                } else {
                    boolean existe = false;
                    for(Map.Entry<Double, ArrayList<OrdemLimitada>> entry : venda.entrySet()) {
                        if(entry.getKey() == ordemLimitada.preco) { //se o preco que to checando agora for igual ao preco que o cliente deseja, adiciono essa ordenLimitada ao array
                            entry.getValue().add(ordemLimitada);
                            existe = true;
                        }
                    }
                    if(!existe) { //se nao existe esse preco, cria uma nova entry no map com esse preco e a ordem limitada
                        venda.put(ordemLimitada.preco, new ArrayList<OrdemLimitada>(Arrays.asList(ordemLimitada)));
                    }
                }
            } else { //caso de compra
                if(ordemLimitada.preco >= getPrecoVenda()) { //quem quer comprar, quer comprar por um preco maior ou igual ao de quem quer vender
                    execCompra(ordemLimitada);
                } else {
                    boolean existe = false;
                    for(Map.Entry<Double, ArrayList<OrdemLimitada>> entry : compra.entrySet()) {
                        if(entry.getKey() == ordemLimitada.preco) {
                            entry.getValue().add(ordemLimitada);
                            existe = true;
                        }
                    }
                    if(!existe) {
                        compra.put(ordemLimitada.preco, new ArrayList<OrdemLimitada>(Arrays.asList(ordemLimitada)));
                    }
                }
            }
    }

    public void execCompra(OrdemLimitada ordem) { //retira a quantidade de lotes solicitado pela ordemLimitada
        /**
         * 1) vou ter que usar um for pra passar por todo o array
         * 2) checar se to no menor preco
         * 3) se estiver, realizo a compra:
         *   3.1) se eu quiser mais do que tem disponivel, compro as disponiveis, e deixo o resto como ordem de compra
         *   3.2) se eu quiser menos ou tudo que tenha disponivel, compro e troco o valor da quantidade de ordem de venda
         */
        for(Map.Entry<Double, ArrayList<OrdemLimitada>> entry : compra.entrySet()) {
            if(entry.getKey() == getPrecoVenda()) { // tenho certeza de que estou no melhor valor
                if(ordem.quantidade <= getQuantidadeVenda(getPrecoVenda())) {
                    for(int i=0; i < entry.getValue().size(); i++) {
                        OrdemLimitada atual = entry.getValue().get(i);
                        if(atual.quantidade==0) continue; //checo se a quantidade daquela ordem de venda ta vazia
                        else if(atual.quantidade >= ordem.quantidade) {
                            atual.quantidade -= ordem.quantidade;
                            ordem.quantidade=0;
                            break;
                        } else {
                            ordem.quantidade -= atual.quantidade;
                            atual.quantidade=0;
                        }
                    }
                } else { // ordem.quantidade > getQuantidadeVenda
                    /**
                     * vou precisar retirar o maximo que conseguir e depois deixar como ordem de compra
                     */
                    for(int i=0; i < entry.getValue().size(); i++) {
                        if(getQuantidadeVenda(getPrecoVenda()) != 0) {
                            OrdemLimitada atual = entry.getValue().get(i);
                            ordem.quantidade -= atual.quantidade;
                            atual.setLote(0);
                        } else { // ou seja, acabaram as vendas, vou ter que colocar como ordem de compra agora
                            boolean existe = false;
                            for (Map.Entry<Double, ArrayList<OrdemLimitada>> _entry : compra.entrySet()) {
                                if (_entry.getKey() == ordem.preco) {
                                    _entry.getValue().add(ordem);
                                    existe = true;
                                }
                            }
                            if (!existe) {
                                compra.put(ordem.preco, new ArrayList<OrdemLimitada>(Arrays.asList(ordem)));
                            }
                        }
                    }
                }
            }
            for (double x : compra.keySet()) {
                if (compra.get(x).size() == 0)
                    compra.remove(x);
                for (int i = 0; i < compra.get(x).size(); i++) {
                    if (compra.get(x).get(i).quantidade == 0)
                        compra.get(x).remove(i);
                }
            }
        }

        // for pra limpar os zerados
        /*for(Map.Entry<Double, ArrayList<OrdemLimitada>> e : compra.entrySet()) {
            ArrayList<OrdemLimitada> arr = e.getValue();
            int index=0;
            for(OrdemLimitada o : arr) {
                if(o.quantidade == 0) {
                    arr.remove(index);
                }
                index++;
            }
        }*/
    }

    public void execVenda(OrdemLimitada ordem) { //ordem pra comprar por um preco maior ou igual ao preco que as pessoas querem vender compra
        for (Map.Entry<Double, ArrayList<OrdemLimitada>> entry : venda.entrySet()) {
            if (entry.getKey() == getPrecoCompra()) { // tenho certeza de que estou no melhor valor
                if (ordem.quantidade <= getQuantidadeCompra(getPrecoCompra())) {
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        OrdemLimitada atual = entry.getValue().get(i);
                        if (atual.quantidade == 0)
                            continue; //checo se a quantidade daquela ordem de venda ta vazia
                        else if (atual.quantidade >= ordem.quantidade) {
                            atual.quantidade -= ordem.quantidade;
                            ordem.quantidade = 0;
                            break;
                        } else {
                            ordem.quantidade -= atual.quantidade;
                            atual.setLote(0);
                        }
                    }
                } else { // ordem.quantidade > getQuantidadeVenda
                    /**
                     * vou precisar retirar o maximo que conseguir e depois deixar como ordem de venda
                     */
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        if (getQuantidadeCompra(entry.getKey()) != 0) {
                            OrdemLimitada atual = entry.getValue().get(i);
                            ordem.quantidade -= atual.quantidade;
                            atual.setLote(0);
                        } else { // ou seja, acabaram as vendas, vou ter que colocar como ordem de venda agora
                            boolean existe = false;
                            for (Map.Entry<Double, ArrayList<OrdemLimitada>> entry1 : venda.entrySet()) {
                                if (entry1.getKey() == ordem.preco) {
                                    entry1.getValue().add(ordem);
                                    existe = true;
                                }
                            }
                            if (!existe) {
                                venda.put(ordem.preco, new ArrayList<OrdemLimitada>(Arrays.asList(ordem)));
                            }
                        }
                    }
                }
            }
        }

        for(double x : venda.keySet()) {
            if(venda.get(x).size() == 0) venda.remove(x);
            for(int i=0; i < venda.get(x).size(); i++) {
                if(venda.get(x).get(i).quantidade == 0) venda.get(x).remove(i);
            }
        }
    }

    public double getPrecoCompra() { //retorna maior preco de compra
        double preco=0;
        for(double x : compra.keySet()) {
            if(x > preco) preco=x;
        }
        return preco;
    }

    public double getPrecoVenda() { //retorna menor preco de venda
        double preco=Double.MAX_VALUE; //venda.get(0).get(0).preco;
        for (double x : venda.keySet()) {
            if (x < preco) preco=x;
        }
        return preco;
    }

    public double getQuantidadeCompra( double d ) { //retorna a quantidade total de quantidades de compra ao preco d
        for(Map.Entry<Double, ArrayList<OrdemLimitada>> entry : compra.entrySet()) {
            if (entry.getKey() == d) {
                double res=0;
                for (int i=0; i < entry.getValue().size(); i++) {
                    res+=entry.getValue().get(i).quantidade;
                }
                return res;
            }
        }
        return 0;
    }

    public double getQuantidadeVenda( double d ) { //retorna a quantidade total de lotes de venda ao preco d
        for (Map.Entry<Double, ArrayList<OrdemLimitada>> entry : venda.entrySet()) {
            if (entry.getKey() == d) {
                double res=0;
                for(int i=0; i < entry.getValue().size(); i++) {
                    res+=entry.getValue().get(i).quantidade;
                }
                return res;
            }
        }
        return 0;
    }

}
package src;

import java.util.Comparator;

public class ABB<K extends Comparable<K>, V> {

    No<K, V> raiz;
    private Comparator<K> comparador;
    private int tamanho;

    public ABB() {
        this.raiz = null;
    }

    @SuppressWarnings("unchecked")
    private void init(Comparator<K> comparador) {
        raiz = null;
        tamanho = 0;

        if (comparador == null) {
            comparador = (Comparator<K>) Comparator.naturalOrder();
        }

        this.comparador = comparador;
    }

    public ABB(Comparator<K> comparador) {
        init(comparador);
    }

    public Boolean vazia() {
        return this.raiz == null;
    }

    //Pesquisa
    public V pesquisar(K chave) {
        return pesquisarRec(chave, raiz);
    }

    public V pesquisarRec(K chave, No<K, V> no) {
        if (no == null) {
            return null;
        } else if (chave.equals(no.getChave())) {
            return no.getItem();
        } else if (chave.compareTo(no.getChave()) > 0) {
            return pesquisarRec(chave, no.getDir());
        } else {
            return pesquisarRec(chave, no.getEsq());
        }
    }

    //Inserção
    public void inserir(K chave, V item) {
        raiz = inserirRec(chave, item, raiz);
    }

    public No<K, V> inserirRec(K chave, V item, No<K, V> no) {
        if (no == null) {
            no = new No<>(chave, item);
        }
        int comp = chave.compareTo(no.getChave());
        if (comp < 0) {
            no.setEsq(inserirRec(chave, item, no.getEsq()));
        } else if (comp > 0) {
            no.setDir(inserirRec(chave, item, no.getDir()));
        } else {
            System.out.println("Chaves iguais");
        }
        return no;
    }

    //Remoção
    public V remover(K chave) {
        V removido = pesquisar(chave);
        this.raiz = removerRec(raiz, chave);
        return removido;
    }

    private No<K, V> removerRec(No<K, V> raizArvore, K chave) {
        int comp;
        if (raizArvore == null) {
            System.out.println("Arvore nao existe");
            return null;
        }
        comp = chave.compareTo(raizArvore.getChave());

        if (comp < 0) {
            raizArvore.setEsq(removerRec(raizArvore.getEsq(), chave));
        } else if (comp > 0) {
            raizArvore.setDir(removerRec(raizArvore.getDir(), chave));
        } else { //encontrado
            if (raizArvore.getDir() == null) {
                return raizArvore.getEsq();
                // 0 filhos ou filho a esquerda
            } else if (raizArvore.getEsq() == null) {
                return raizArvore.getDir();
                // 1 filho a direita
            } else {
                raizArvore.setEsq(antecessor(raizArvore, raizArvore.getEsq()));
                // nó com 2 filhos
            }
        }
        return raizArvore;
    }


    // Achar antecessor
    private No<K, V> antecessor(No<K, V> noRetirar, No<K, V> raizArvore) {
        if (raizArvore.getDir() != null) {
            raizArvore.setDir(antecessor(noRetirar, raizArvore.getDir()));
        } else {
            noRetirar.setChave(raizArvore.getChave());
            noRetirar.setItem(raizArvore.getItem());
            raizArvore = raizArvore.getEsq();
        }
        return raizArvore;
    }

    // caminhamento pré-ordem
    public String caminhamentoPreOrdem() {
        if (this.raiz == null) {
            throw new RuntimeException("Arvore vazia");
        }
        return caminhamentoPreOrdemRec(this.raiz).trim();
    }

    private String caminhamentoPreOrdemRec(No<K, V> no) {
        if (no == null) {
            return "";
        }
        String s = "";
        V item = no.getItem();
        if (item != null) {
            s += item.toString() + " ";
        }
        s += caminhamentoPreOrdemRec(no.getEsq());
        s += caminhamentoPreOrdemRec(no.getDir());
        return s;
    }

    //caminhamento pos-ordem
    public String caminhamentoPosOrdem() {
        if (this.raiz == null) {
            throw new RuntimeException("Arvore vazia");
        }

        return caminhamentoPosOrdemRec(this.raiz).trim();
    }

    private String caminhamentoPosOrdemRec(No<K, V> no) {
        if (no == null) {
            return "";
        }
        String s = "";
        s += caminhamentoPosOrdemRec(no.getEsq());
        s += caminhamentoPosOrdemRec(no.getDir());
        V item = no.getItem();
        if (item != null) {
            s += item.toString() + " ";
        }
        return s;
    }
    //caminhamento em ordem
    public String caminhamentoEmOrdem() {
        if (this.raiz == null) {
            throw new RuntimeException("Arvore vazia");
        }
        return caminhamentoEmOrdemRec(this.raiz);
    }

    private String caminhamentoEmOrdemRec(No<K, V> no) {
        if (no != null) {
            String resp = caminhamentoEmOrdemRec(no.getEsq());

            resp += no.getItem() + "\n";
            resp += caminhamentoEmOrdemRec(no.getDir());
            return resp;
        }
        return "";
    }

    //Imprimir em ordem decrescente
    public String caminhamentoDecrescente() {
        if (this.raiz == null) {
            throw new RuntimeException("Arvore vazia");
        }
        return caminhamentoDecrescenteRec(this.raiz);
    }

    private String caminhamentoDecrescenteRec(No<K, V> no) {
        if (no != null) {
            String resp = caminhamentoDecrescenteRec(no.getDir());

            resp += no.getItem() + "\n";
            resp += caminhamentoDecrescenteRec(no.getEsq());
            return resp;
        }
        return "";

    }

    // Obter menor valor
    public V obterMenor(No<K, V> no) {
        if (this.raiz == null) {
            throw new RuntimeException("Arvore vazia");
        }

        while (no.getEsq() != null) {
            no = no.getEsq();
        }

        return no.getItem();
    }

    //Clonar Árvore
    @Override
    public ABB<K, V> clone() {
        ABB<K, V> novaArvore = new ABB<>(this.comparador);
        novaArvore.raiz = clonarSubArvore(this.raiz);
        novaArvore.tamanho = this.tamanho;
        return novaArvore;
    }


    //Clonar uma arvore em outra
    private No<K, V> clonarSubArvore(No<K, V> no) {
        if (no == null) {
            return null;
        }

        No<K, V> novoNo = no.clone();

        novoNo.setEsq(clonarSubArvore(no.getEsq()));
        novoNo.setDir(clonarSubArvore(no.getDir()));

        return novoNo;

    }
    // Obter numeros maiores que a raiz
    public ABB<K,V> obterSubconjuntoMaiores(K chave) {
        return null;
    }
}

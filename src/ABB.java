package src;

public class ABB<K extends Comparable<K>, V> {

    No<K, V> raiz;

    public ABB() {
        this.raiz = null;
    }

    //Pesquisa
    public V pesquisar(K chave) {
        return pesquisarRec(chave, raiz);
    }

    public V pesquisarRec(K chave, No<K, V> no) {
        if (no == null) {
            return null; 
        }else if (chave.equals(no.getChave())) {
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

    private No<K,V> antecessor(No<K,V> noRetirar, No<K,V> raizArvore){
        if(raizArvore.getDir() != null){
            raizArvore.setDir(antecessor(noRetirar, raizArvore.getDir()));
        }
        else{
            noRetirar.setChave(raizArvore.getChave());
            noRetirar.setItem(raizArvore.getItem());
            raizArvore = raizArvore.getEsq();
        }
        return raizArvore;
    }

}

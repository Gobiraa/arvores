package src;

public class ABB<K extends Comparable<K>,V>{
    No<K,V> raiz;
    public ABB(){
        this.raiz=null;
    }
    public V pesquisar(K chave){
        return pesquisarRec(chave,raiz);
    }

    public V pesquisarRec(K chave, No<K,V> no){
        if(no==null)
            return null;
        else if(chave.equals(no.getChave())){
            return no.getItem();
        }
        else if(chave.compareTo(no.getChave()) > 0){
            return pesquisarRec(chave, no.getDir());
        }
        else
            return pesquisarRec(chave, no.getEsq());
    }
    


}
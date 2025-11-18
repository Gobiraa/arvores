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
    
    public void inserir(K chave, V item){
        raiz = inserirRec(chave, item, raiz);
    }

    public No<K,V> inserirRec(K chave, V item, No<K,V> no){
        if(no==null){
            no = new No<> (chave, item);
        }
        int comp = chave.compareTo(no.getChave());
        if(comp < 0){
            no.setEsq(inserirRec(chave, item, no.getEsq()));
        }
        else if(comp > 0){
            no.setDir(inserirRec(chave, item, no.getDir()));
        }
        else{
            System.out.println("Chaves iguais");
        }
        return no;
    }


}
package src;

public class No<K,V> {
    private K chave;
    private V item;
    private No<K,V> esq;
    private No<K,V> dir;
    
    public No(K chave, V item){
        this.chave=chave;
        this.item=item;
        esq=null;
        dir=null;
    }

    public K getChave() {
        return chave;
    }

    public void setChave(K chave) {
        this.chave = chave;
    }

    public V getItem() {
        return item;
    }

    public void setItem(V item) {
        this.item = item;
    }

    public No<K, V> getEsq() {
        return esq;
    }

    public void setEsq(No<K, V> esq) {
        this.esq = esq;
    }

    public No<K, V> getDir() {
        return dir;
    }

    public void setDir(No<K, V> dir) {
        this.dir = dir;
    }

    @Override
    public No<K, V> clone(){
        return new No<>(this.chave, this.item);
    }


}

/*Guilherme Gomes Pinho - 10755529
Moabe Guedes - 10748053 */
public class Vetor<T> {
    public T[ ] A; // armazena os elementos do vetor
    public int capacity; // capacidade do vetor
    public int size; // elementos no vetor
    
    @SuppressWarnings("unchecked")
    public Vetor(int capacity) {
        this.A = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }
    
    public boolean isEmpty() {
        //verifica se o vetor está vazio
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    public int size() {
        //retorna a quantidade de elementos no vetor
        return size;
    }
    
    public T get(int i) throws Exception {
        // retorna o elemento que está na posição i do vetor
        if (i>=size) {
            throw new Exception("Posição inexistente");
        }
        if (isEmpty()) {
            throw new Exception("Lista Vazia.");
        }
        return A[i];
    }
    
    public void set(int i, T n) throws Exception {
        // substitui o conteúdo da posição i por n
        if (i>=size) {
            throw new Exception("Posição inexistente");
        }
        if (isEmpty()) {
            throw new Exception("Lista Vazia.");
        }
        A[i] = n;
    }
    
    public void add(int i, T n) throws Exception {
        // insere o novo elemento n na posição i
        if (size == A.length) {
            throw new Exception("Lista Cheia!!");
        }
        if (i>size) {
            throw new Exception("Posição inválida para inserir");
        }
        for (int x=size; x>i ;x--) {
            A[x] = A[x - 1];
        }
        A[i] = n;
        size++;
    }
    
    public void remove(int i) throws Exception {
        //exclui o elemento da posição i
        if (i>=size) {
            throw new Exception("Posição inexistente");
        }
        if (isEmpty()) {
            throw new Exception("Lista Vazia.");
        }
        for (int x=i; x<=size-2;x++){
            A[x] = A[x+1];            
        }
        size--;
    }
    
    public int search(T n) {
        //busca n na lista
        for (int i=0; i<size; i++) {
            if (A[i].equals(n)) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostraLista() {
        System.out.println("\n-- Ranking --");
        for (int i=0; i<size;i++) {
            System.out.println(A[i]);
        }
    }
}
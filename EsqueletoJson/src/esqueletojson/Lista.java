/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

/**
 *
 * @author berta
 * @param <T>
 */
public class Lista<T extends Comparable<T>> {
    protected Nodo<T> head;
    protected Nodo<T> tail;   
    private int length;
    
    public Lista(){
        head = null;
        tail = null;
        length = 0;       
    }
    public T get(int i){
      if (i >length){
          return null;
      }else{
        int conteo = 0;
        Nodo<T> actual = this.head;
        while(conteo < i){
            actual = actual.getNext();
            conteo ++;
        }
        return  actual.getDato();
      }
    }
    public int get_Length(){
        return length;
    }
    /**
     * funcion add
     * recibe el parametro n que se va a insertar en la lista
     * @param n 
     */
    public void Add(T n){
        Nodo<T> nuevo = new Nodo<T>(n);
        if(length == 0){//si la lista es vacia
          head = nuevo;
          tail = head;
          length +=1;
        }
        else{// si la lista contiene elementos
            tail.setNext(nuevo); 
            tail = nuevo;
            length +=1;
        }
    }
    /**
     * funcion print
     * recorre la lista e imprime el dato
     */
    public void Print(){
        Nodo<T> temp = head;
        System.out.println("contenido de la lista");
        while( temp != null){
            System.out.println("["+ temp.getDato()+"]");
            temp = temp.getNext() ;
        }
    }
    /**
     * Funcion Delete
     * Borra el ultimo elemento de la lista
     */
    public void Delete(){
        if (length > 1){
            Nodo<T> temp = head;
            while( temp.getNext().getNext() != null){
                temp = temp.getNext();
                
            }
            temp.setNext(null);
            tail = temp;
            length -= 1;
        } else{
            if(length ==1){
                head = null;
                tail = head;
                length -= 1;
            }else{
                System.out.println("no hay nada que borrar");  
            }
        }
    }

    public Nodo<T> Buscar(T buscado) {
        Nodo<T> actual = this.head;
        while (actual != null) {
                if (actual.getDato().compareTo(buscado) == 0) {
                        return actual;
                } else {
                        actual = actual.getNext();
                }
        }
        return null;
	
    }
    public boolean Existe(T buscado){
      Nodo<T> actual = this.head;
        while (actual != null) {
                if (actual.getDato().compareTo(buscado) == 0) {
                        return true ;
                } else {
                        actual = actual.getNext();
                }
        }
        return false;  
    }
}

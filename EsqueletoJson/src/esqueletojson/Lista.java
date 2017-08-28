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
public class Lista<T> {
    protected Nodo<T> head;
    protected Nodo<T> tail;   
    private int length;
    
    public Lista(){
        head = null;
        tail = null;
        length = 0;       
    }
    private void set_Length(int l){
        length += l;
    }
    private int get_Length(){
        return length;
    }
    /**
     * funcion add
     * recibe el parametro n que se va a insertar en la lista
     * @param n 
     */
    public void Add(T n){
        Nodo<T> nuevo = new Nodo<>(n);
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
}

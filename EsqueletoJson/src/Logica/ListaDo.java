/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author berta
 */
public class ListaDo<T extends Comparable<T>> {
  protected NodoCir<T> head;
    protected NodoCir<T> tail;   
    private int length;
    
    public ListaDo(){
        head = null;
        tail = null;
        length = 0;       
    }
    /**
     * Me retorna el valor de la posicion (i) donde se encuentre el nodo
     * @param i
     * @return 
     */
    public T get(int i){
      if (i >length){
          return null;
      }else{
        int conteo = 0;
        NodoCir<T> actual = this.head;
        while(conteo < i){
            actual = actual.GetNext();
            conteo ++;
        }
        return  actual.GetDato();
      }
    }
    /**
     * Retorna el largo de la lista
     * @return 
     */
    public int Get_Length(){
        return length;
    }
    /**
     * funcion add
     * recibe el parametro n que se va a insertar en la lista
     * @param n 
     */
    public void Add(T n){
        NodoCir<T> nuevo = new NodoCir<T>(n);
        if(length == 0){//si la lista es vacia
          head = nuevo;
          tail = head;
          length +=1;
        }
        else{// si la lista contiene elementos
            tail.SetNext(nuevo); 
            tail = nuevo;
            length +=1;
        }
    }
    /**
     * funcion print
     * recorre la lista e imprime el dato
     */
    public void Print(){
        NodoCir<T> temp = head;
        System.out.println("contenido de la lista");
        while( temp != null){
            System.out.println("["+ temp.GetDato()+"]");
            temp = temp.GetNext() ;
        }
    }
    /**
     * Funcion Delete
     * Borra el ultimo elemento de la lista
     */
    public void Delete(){
        if (length > 1){
            NodoCir<T> temp = head;
            while( temp.GetNext().GetNext() != null){
                temp = temp.GetNext();
                
            }
            temp.SetNext(null);
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
    /**
     * Esta clase a buscar un elemento (buscado) y va a retornar el elemento
     * @param buscado
     * @return 
     */
    public NodoCir<T> Buscar(T buscado) {
        NodoCir<T> actual = this.head;
        while (actual != null) {
                if (actual.GetDato().compareTo(buscado) == 0) {
                        return actual;
                } else {
                        actual = actual.GetNext();
                }
        }
        return null;
	
    }
    /**
     * Esta clase va a buscar el elemento (buscado) que yo le pida y va a devolver True si existe y False si no existe
     * @param buscado 
     * @return 
     */
    public boolean Existe(T buscado){
      NodoCir<T> actual = this.head;
        while (actual != null) {
                if (actual.GetDato().compareTo(buscado) == 0) {
                        return true ;
                } else {
                        actual = actual.GetNext();
                }
        }
        return false;  
    }  
}

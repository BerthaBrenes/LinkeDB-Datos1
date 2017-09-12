/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author berta
 * @param <T>
 */
public class Lista<T extends Comparable<T>> implements Listas<T> {

    protected Nodo<T> head;
    protected Nodo<T> tail;    
    private int length;
    
    public Lista() {
        head = null;
        tail = null;
        length = 0;        
    }

    /**
     * Me retorna el valor de la posicion (i) donde se encuentre el nodo
     *
     * @param i
     * @return
     */
    public T Iterador(int i) {
        if (i > length) {
            return null;
        } else {
            int conteo = 0;
            Nodo<T> actual = this.head;
            while (conteo < i) {
                actual = actual.getNext();
                conteo++;
            }
            return actual.getDato();
        }
    }

    /**
     * Retorna el largo de la lista
     *
     * @return
     */
    public int Largo() {
        return length;
    }

    /**
     * funcion add recibe el parametro n que se va a insertar en la lista
     *
     * @param n
     */
    public void Insertar(T n) {
        Nodo<T> nuevo = new Nodo<T>(n);
        if (length == 0) {//si la lista es vacia
            head = nuevo;
            tail = head;
            length += 1;
        } else {// si la lista contiene elementos
            tail.setNext(nuevo);            
            tail = nuevo;
            length += 1;
        }
    }

    /**
     * funcion print recorre la lista e imprime el dato
     */
    public void Imprimir() {
        Nodo<T> temp = head;
        System.out.println("contenido de la lista");
        while (temp != null) {
            System.out.println("[" + temp.getDato() + "]");
            temp = temp.getNext();
        }
    }

    /**
     * Funcion Delete Borra el ultimo elemento de la lista
     */
    public void EliminarDato() {
        if (length > 1) {
            Nodo<T> temp = head;
            while (temp.getNext().getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(null);
            tail = temp;
            length -= 1;
        } else if (length == 1) {
            head = null;
            tail = head;
            length -= 1;
        } else {
            System.out.println("no hay nada que borrar");            
        }
    }

    public void Eliminar(T buscado) {
        Nodo<T> temp = this.head;
        if (this.head.getDato().compareTo(buscado) == 0) {
            this.head = this.head.getNext();
            System.out.println("hey soy el primero");
        } else {
            while (temp.getNext() != null) {
                if (temp.getNext() == this.tail) {
                    System.out.println("primer if");
                    if (this.tail.getDato().compareTo(buscado) == 0) {
                        EliminarDato();
                        break;
                    } else {
                        temp = temp.getNext();
                    }                    
                } 
                else if (temp.getNext().getDato().compareTo(buscado) == 0) {
                    temp.setNext(temp.getNext().getNext());
                    System.out.println("heyyy estoy en el medio");
                    this.length -=1;
                    break;
                } else {
                    temp = temp.getNext();
                }
            }
            
        }
    }

    /**
     * Esta clase a buscar un elemento (buscado) y va a retornar el elemento
     *
     * @param buscado
     * @return
     */
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

    /**
     * Esta clase va a buscar el elemento (buscado) que yo le pida y va a
     * devolver True si existe y False si no existe
     *
     * @param buscado
     * @return
     */
    public boolean Existe(T buscado) {
        Nodo<T> actual = this.head;
        while (actual != null) {
            if (actual.getDato().compareTo(buscado) == 0) {
                return true;
            } else {
                actual = actual.getNext();
            }
        }
        return false;        
    }
}

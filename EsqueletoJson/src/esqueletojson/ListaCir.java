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
public class ListaCir<T extends Comparable<T>> {

    protected NodoCir<T> head;
    private int length;

    public ListaCir() {
        this.head = null;
        this.length = 0;
    }

    public void Add(T n) {
        NodoCir<T> nuevo = new NodoCir<T>(n);
        if (length == 0) {//si la lista es vacia
            this.head = nuevo;
            nuevo.SetNext(head);
            nuevo.SetPrev(head);
            length += 1;
        } else {// si la lista contiene elementos
            length += 1;
            NodoCir<T> temp = this.head;
            while (temp.GetNext() != head) {
                temp = temp.GetNext();
            }
            temp.SetNext(nuevo);
            this.head.SetPrev(nuevo);
            nuevo.SetPrev(temp);
            nuevo.SetNext(head);
        }
    }

    public int GetLength() {
        return this.length;
    }

    public NodoCir<T> Buscar(T buscado) {
        NodoCir<T> actual = this.head;
        if (this.length != 0) {
            while (actual.GetNext() != head) {
                if (actual.GetDato().compareTo(buscado) == 0) {
                    return actual;

                } else {
                    actual = actual.GetNext();
                }
            }
            if (actual.GetDato().compareTo(buscado) == 0) {

                return actual;
            }
            return null;
        } else {
            System.out.print("no hay elementos que buscar");
            return null;
        }
    }

    public NodoCir<T> Eliminar(T buscado) {
        NodoCir<T> actual = this.head;
        if (this.length > 1) {
            while (actual.GetNext() != head) {
                if (actual.GetDato().compareTo(buscado) == 0) {
                    this.length -= 1;
                    actual.GetPrev().SetNext(actual.GetNext());
                    actual.GetNext().SetPrev(actual.GetPrev());

                } else {
                    actual = actual.GetNext();
                }
            }
            if (actual.GetDato().compareTo(buscado) == 0) {
                this.length -= 1;
                actual.GetPrev().SetNext(actual.GetNext());
                actual.GetNext().SetPrev(actual.GetPrev());

            }
        } else if (this.length == 1) {
            this.head = null;
            this.length -= 1;

        } else {
            System.out.print("no hay elementos que buscar");

        }
        return null;
    }

    public void Print() {
        NodoCir<T> temp = head;
        System.out.println("contenido de la lista");
        while (temp.GetNext() != head) {
            System.out.println("[" + temp.GetDato() + "]");
            temp = temp.GetNext();
        }
        System.out.println("[" + temp.GetDato() + "]");
    }
}

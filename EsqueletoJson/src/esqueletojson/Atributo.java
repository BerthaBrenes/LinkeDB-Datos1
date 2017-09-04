/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

/**
 *
 * @author berta
 */
public class Atributo implements Comparable<Atributo> {
  public String nombre;
  public String valor;
  public Tipo tipo;
  public Llave llave;
  public boolean requerido;

    @Override
    public int compareTo(Atributo o) {
        return this.nombre.compareTo(o.nombre);
    }
  
}

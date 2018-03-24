/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author karol
 */
public class MethodDigital implements Serializable {

    private ArrayList<Object> lista = new ArrayList<Object>();///variable arrayasListe

    public MethodDigital() {
    }//constructor vacio

    public MethodDigital(ArrayList<Object> a) {//metodo con parametros
        this.lista = a;
    }

    /*Este metodo añade a la lista nuestro Objeto beans*/
    public void addRecord(BeansDigital beansDigital) {
        this.lista.add(beansDigital);
    }

    /*Este metodo lo que hace es modificar nuestra lista, entre parametros recibe un entero, y un objeto
    entonces modifica la posicion del objeto  de nuestra lista
     */
    public void modifyRecord(int i, BeansDigital beansDigital) {
        this.lista.set(i, beansDigital);
    }

    /*Este metodo lo que hace es eliminar la posicion del objeto en nuestra lista*/
    public void deleteRecord(int i) {
        this.lista.remove(i);
    }

    /*La funcion de este metodo es obtener un registro de la lista, entre parametro tenemos una variable de tipo entero
      y nos retorna la posicion del objeto que deseamos obtener, por eso esta casting
     */
    public BeansDigital getRecord(int i) {
        return (BeansDigital) lista.get(i);
    }

    /*Este metodo nos devuelve la cantidad de libros registrados en la lista, gracias al metodo .size, nos devuelve el tamano nde toda la lista*/
    public int quantifyRecord() {
        return this.lista.size();
    }

    /*Este metodo, lo que hace es buscar si hay enumeraciones repetida, entre parametros tenemos una variable de tipo entera
    esta variable contendra la informacion  que hay en nuestro campo de texto de la enumeracion, compara la enumeracion con nuestra lista
    si las enumeraiones fuesen repetidas, me devuelve la posicion, sino si fuesen distintas me devuelve un -1
     */
    public String searchCode(String code) {
        for (int i = 0; i < quantifyRecord(); i++) {
            if (code.equals(getRecord(i).getCode())) {
                return code;
            }
        }
        return null;
    }
}

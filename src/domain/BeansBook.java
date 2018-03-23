/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Interface.RegisterBookInterface;
import java.io.Serializable;

/**
 *
 * @author karol
 */
public class BeansBook  implements Serializable{
    private String year;
    private String author;
    private Object description;
    //private int enumeration;
    private String tipoLibro;

    public BeansBook() {
    }

    public BeansBook(String year, String author, Object description, String tipoLibro) {
        this.year = year;
        this.author = author;
        this.description = description;
        //this.enumeration = enumeration;
        this.tipoLibro = tipoLibro;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

//    public int getEnumeration() {
//        return enumeration;
//    }
//
//    public void setEnumeration(int enumeration) {
//        this.enumeration = enumeration;
//    }

    public String getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(String tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    @Override
    public String toString() {
        return "BeansBook{" + "year=" + year + ", author=" + author + ", description=" + description + ", tipoLibro=" + tipoLibro + '}';
    }

   
  
   

   
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author karol
 */
public class BeansPhysicists extends BeansBook implements Serializable {

    String nameBook;
    String codigoBook;
    int numberBookAvailable;
    byte[] photo;

    public BeansPhysicists() {
        super();

    }


    public BeansPhysicists(String nameBook, String codigoBook, int numberBookAvailable, byte[] photo, String year, String author, Object description, int enumeration, String tipoLibro) {
        super(year, author, description, enumeration, tipoLibro);
        this.nameBook = nameBook;
        this.codigoBook = codigoBook;
        this.numberBookAvailable = numberBookAvailable;
        this.photo = photo;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getCodigoBook() {
        return codigoBook;
    }

    public void setCodigoBook(String codigoBook) {
        this.codigoBook = codigoBook;
    }

    public int getNumberBookAvailable() {
        return numberBookAvailable;
    }

    public void setNumberBookAvailable(int numberBookAvailable) {
        this.numberBookAvailable = numberBookAvailable;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return  super.toString()+"BeansPhysicists{" + "nameBook=" + nameBook + ", codigoBook=" + codigoBook + ", numberBookAvailable=" + numberBookAvailable + ", photo=" + photo + '}';
    }




    
}

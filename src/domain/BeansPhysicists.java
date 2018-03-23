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
    int enumeration;
    String nameBook;
    String codeBook;
    int numberBookAvailable;
    byte[] photo;

    public BeansPhysicists() {
        super();

    }


    public BeansPhysicists(int enumeration, String nameBook, String codeBook, int numberBookAvailable, byte[] photo, String year, String author, Object description, String tipoLibro) {
        super(year, author, description, tipoLibro);
       this.enumeration=enumeration;
        this.nameBook = nameBook;
        this.codeBook = codeBook;
        this.numberBookAvailable = numberBookAvailable;
        this.photo = photo;
    }

    public int getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(int enumeration) {
        this.enumeration = enumeration;
    }

    
    
    
    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getCodeBook() {
        return codeBook;
    }

    public void setCodeBook(String codeBook) {
        this.codeBook = codeBook;
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
        return  super.toString()+"BeansPhysicists{" + "enumeration=" + enumeration + ", nameBook=" + nameBook + ", codeBook=" + codeBook + ", numberBookAvailable=" + numberBookAvailable + ", photo=" + photo + '}';
    }

    




    
}

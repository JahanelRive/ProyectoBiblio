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
public class BeansDigital extends BeansBook implements Serializable{
 

    private String name;
    private String code;
    byte[] photo;

 

    public BeansDigital(String name, String code, byte[] photo, String year, String author, Object description, String tipoLibro) {
        super(year, author, description, tipoLibro);
        this.name = name;
        this.code = code;
        this.photo = photo;
        
    }

    public Object getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return  super.toString()+ "BeansDigital{" + "name=" + name + ", code=" + code + ", photo=" + photo + '}';
    }



}
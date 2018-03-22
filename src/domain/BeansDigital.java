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
public class BeansDigital implements Serializable{
 
    private int numeration;
    private String name;
    private String code;
    Object description;
    byte[] photo;

    public BeansDigital(int numeration,  String code, String name, Object description, byte[] photo) {
        this.numeration = numeration;
        this.name = name;
        this.code = code;
        this.description = description;
        this.photo = photo;
    }

    public BeansDigital() {
    }

    public int getNumeration() {
        return numeration;
    }

    public void setNumeration(int numeration) {
        this.numeration = numeration;
    }

    public String getName() {
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "BeansD{" + "numeration=" + numeration + ", name=" + name + ", code=" + code + ", description=" + description + ", photo=" + photo + '}';
    }


 
}

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
public class BeansAudiovisual  extends BeansSuperAudioVisual implements Serializable{
    private int code;
    private String name;
    private int quantify;
    byte[] photo;

    public BeansAudiovisual() {
        super();
    }

    public BeansAudiovisual(String brand, String size, String projectorType, String color, int code, String name, int quantify, byte[] photo) {
        super(brand,size,projectorType, color);
        this.code = code;
        this.name = name;
        this.quantify = quantify;
        this.photo = photo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantify() {
        return quantify;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return super.toString()+ "BeansAudiovisual{"  + "code=" + code + ", name=" + name + ", quantify=" + quantify + ", photo=" + photo + '}';
    }

   

   
    

   
    }

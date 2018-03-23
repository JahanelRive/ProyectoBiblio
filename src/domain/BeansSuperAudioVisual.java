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
public class BeansSuperAudioVisual implements Serializable{
   String brand;
   String size;
   String projectorType;
   String color;

    public BeansSuperAudioVisual() {
    }

    public BeansSuperAudioVisual(String brand, String size, String projectorType, String color) {
        this.brand = brand;
        this.size = size;
        this.projectorType = projectorType;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProjectorType() {
        return projectorType;
    }

    public void setProjectorType(String projectorType) {
        this.projectorType = projectorType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "BeansSuperAudioVisual{" + "brand=" + brand + ", size=" + size + ", projectorType=" + projectorType + ", color=" + color + '}';
    }
   
    
    
    
   
   
   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.File;

/**
 *
 * @author karol
 */
public class Student {
//declaracion de variable

    public String name;
    public String lastName;
    public String id;
    public String carnet;
    public String career;
//constructores

    public Student() {
        this.name = " ";
        this.lastName = " ";
        this.id = " ";
        this.carnet = " ";
        this.career = " ";

    }

    public Student(String name, String lastName, String id, String carnet, String career) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.carnet = carnet;
        this.career = career;
    }

    //set y gets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String meat) {
        this.carnet = meat;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    //to String
    @Override
    public String toString() {
        return "Student: " + "name=" + this.getName() + ", lastName=" + this.getLastName() + ", id=" + this.getId() + ", Card=" + this.getCarnet() + ", career=" + this.getCareer() +"\n";
    }

    public int size() {
        //indica el tamño en byte de las variable internas
        //long; necesita dos bytes
        //String; 2 bytes de esxpacio
        //int 4
       // return this.getName().length() * 2 + 4 + 4 + 1 + 4;
        return this.getName().length()*2  + this.getLastName().length()*2 + this.getId().length()+2 + this.getCarnet().length()*2 +this.getCareer().length()*2;

    }
}//en class

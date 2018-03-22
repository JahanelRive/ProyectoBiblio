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
    public String meat;
    public String career;
//constructores

    public Student() {
        this.name = " ";
        this.lastName = " ";
        this.id = " ";
        this.meat = " ";
        this.career = " ";

    }

    public Student(String name, String lastName, String id, String meat, String career) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.meat = meat;
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

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
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
        return "Student" + "name=" + this.getName() + ", lastName=" + this.getLastName() + ", id=" + this.getId() + ", meat=" + this.getMeat() + ", career=" + this.getCareer();
    }

    public int size() {
        //indica el tamño en byte de las variable internas
        //long; necesita dos bytes
        //String; 2 bytes de esxpacio
        //int 4
        return this.getName().length() * 2 + 4 + 4 + 1 + 4;

    }
}//en class

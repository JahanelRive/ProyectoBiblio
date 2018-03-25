/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import GUI.RegisterStudent;
import domain.Student;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jahanel
 */
public class StudentFile {

    public RandomAccessFile randonAcessFile;
    public int regsQuantity;//cantidad del registro en el txt
    public int regSize;//tamno del regitro
    public String myFilePath;//rut
    //Constructors

   // public StudentFile(){}
    
    public StudentFile(File file) throws IOException {
        try {
            //almaceno la rut
            myFilePath = file.getPath();
            //indico el taman maximo
            this.regSize = 140;//tamño cada registro

            if (file.exists() && !file.isFile()) {
                throw new IOException(file.getName() + " is an invalid file");
            } else {
                randonAcessFile = new RandomAccessFile(file, "rw");//leer y escribir
                //se redondea hacia arriba siempre
                this.regsQuantity
                        = (int) Math.ceil((double) randonAcessFile.length() / (double) regSize);
            }
        } catch (IOException e) {
            System.out.println("Error 1, StudentFile" + e);
        }

    }

    public void close() throws IOException {
        try {
            randonAcessFile.close();
        } catch (IOException e) {

        }
    }

    public int fileSize() {
        return this.regsQuantity;

    }

    public boolean putValue(int position, Student Student) throws IOException {
        //try{ 
        if (position < 0 && position > this.regsQuantity) {

            return false;
        } else {
            if (Student.size() > this.regSize) {

                return false;

            } else {

                randonAcessFile.seek(position * this.regSize); //position*regSize
                randonAcessFile.writeUTF(Student.getName());
                randonAcessFile.writeUTF(Student.getLastName());
                randonAcessFile.writeUTF(Student.getId());
                randonAcessFile.writeUTF(Student.getCarnet());
                randonAcessFile.writeUTF(Student.getCareer());

                return true;
            }
        }
        //}catch(Exception e){ System.out.println("Error 3, CarFile");}

    }

    public boolean addEndRecord(Student Student) throws IOException {//escribe en el txt

        boolean sucess = putValue(this.regsQuantity, Student);
        try {
            if (sucess) {
                this.regsQuantity++;
            }
        } catch (Exception e) {

        }
        return sucess;
    }

    public Student getRead(int position) throws IOException {
        Student student = new Student();
        try {

            if (position >= 0 && position <= randonAcessFile.length()) {
                randonAcessFile.seek(position * this.regSize);
                student.setName(randonAcessFile.readUTF());
                student.setLastName(randonAcessFile.readUTF());
                student.setId(randonAcessFile.readUTF());
                student.setCarnet(randonAcessFile.readUTF());
                student.setCareer(randonAcessFile.readUTF());

            } else {

                return null;

            }
        } catch (IOException e) {

        }
        return student;

    }

    public ArrayList<Student> getAllStudents() throws IOException {
        ArrayList<Student> studentArray = new ArrayList<Student>();
        try {
            for (int i = 0; i < this.regsQuantity; i++) {
                Student student = this.getRead(i);//obteber el registro del estudiante o la posicion
                if (student != null) {
                    studentArray.add(student);
                }
            }
        } catch (IOException e) {

        }
        return studentArray;

    }

    public boolean search(String card) throws IOException {
        Student student;
        try {
            for (int i = 0; i < regsQuantity; i++) {
                student = this.getRead(i);
                if (card.equals(student.getCarnet())) {
                    JOptionPane.showMessageDialog(null, "Search results: " + " \n Name:" + student.getName() + "\n" + "Last Name:" + student.getLastName() + "\n" + "ID:" + student.getId() + "\n" + "meat:" + student.getCarnet());
                    return this.putValue(i, student);

                }

            }

            JOptionPane.showMessageDialog(null, "The student is not registered");
    

        } catch (HeadlessException | IOException e) {

        }
        return false;

    }
    
   
}

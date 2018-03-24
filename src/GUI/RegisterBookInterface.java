/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import domain.BeansPhysicists;
import domain.ImageTable;
import domain.Method;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author karol
 */
public class RegisterBookInterface extends javax.swing.JFrame {

    Method method;

    int clic_tabla;
    String path = "registerPhysicalBooks.bin";
    BeansPhysicists beansBooks;

    /**
     * Creates new form RegisterPhysicalBooks
     */
    public RegisterBookInterface() {
        initComponents();
        method = new Method();

        try {

            readSerializable();
            listarRegistro();
            fieldPhoto.setEditable(false);
            fieldEnumeration.setText("1");
        
        } catch (Exception e) {
            System.out.println("error 1 al leer fisico");
        }

    }
    
    public void m(){
  int contador=1;
  fieldEnumeration.setText(String.valueOf(contador));
  contador++;
    
    
    }

    /*Este metodo lo que hace es leer nuestro archivo, en la condicion, preguntados, si lo que hay en nuestro archivo es diferente de null
    entonces que nos lo escriba, le hacemmos un canstins*/
    public void readSerializable() {
        try {
            FileInputStream fis = new FileInputStream(path);///le mandamos la ruta para que lea nuestro archivo
            ObjectInputStream in = new ObjectInputStream(fis);//lee directamnete en el archivo
            if (in != null) {///si el archivo no esta vacio, que me lo lea
                method = (Method) in.readObject();//leemos el archuvos
                in.close();//cerramos el archivo
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al readSerializable read fisico");

        }
    }

    public void writeSerializable() {
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            fos = new FileOutputStream(path);   //guardar en el bin
            out = new ObjectOutputStream(fos);//escribe en el binario

            if (out != null) {  ///si el archivo no esta vacio, que me lo escriba

                out.writeObject(method);///escribimos el archivo
                out.close();//cerramos el archivo
            }//guardamos los datos
        } catch (IOException e) {
            System.out.println("error en writeBin read fisico");
        }
    }

    public void message(String sms) {//este metodo es para enviar un sms al usuario, en caso de un error
        JOptionPane.showMessageDialog(null, sms);
    }

    public void ingresarRegistro(File path) {
        try {
            if (readEnumeration() == -666) {
                message("Incorect date");
            }else if(nameNull().equals(null)){ 
                message("Incorrect date");
            }else if(codeNull().equals(null)){   
                message("Incorrect date");
            } else if(quantifyNull().equals(null)){
                message("Incorrect date");
                
            }else if(yearNull().equals(null)){ 
              message("Inocrrect date");  
            }else if(authorNull().equals(null)){
                message("Incorrect date");
            }else if(descrptionNull().equals(null)){
                message("Incorrect date");
            } else if(typeBookNull().equals(null)){
             message("Incorrect date");   
            }else    
            beansBooks = new BeansPhysicists(readEnumeration(), readName(), readCode(), readNumberBookAvailable(), readPhoto(path), readYear(), readAuthor(), readDescription(), readType());
            if (method.searchCode(beansBooks.getCodeBook()) != null) {
                //  if (method.searchEnumeration(beansBooks.getEnumeration()) != -1) {
                message("This code its repeat");
            } else {
                method.addRecord(beansBooks);
            }

            writeSerializable();
            listarRegistro();

            // }
        } catch (Exception e) {
           message("check if your data is correct");
        }
    }


    public void deleteRecordd() {
        try {
            if (readEnumeration() == -666) {
                message("Incorrect date");
            } else {
                int enumeration = method.searchEnumeration(readEnumeration());
                if (enumeration == -1) {
                    message("Existe ");
                } else {
                    int s = JOptionPane.showConfirmDialog(null, "Are you sore, yoy can delete that");
                    if (s == 0) {
                        method.deleteRecord(enumeration);

                        writeSerializable();
                        listarRegistro();
                    }
                }
            }

        } catch (HeadlessException e) {
        }

    }

//EStos metodos son: para cuando el usuario ingrese un dato incorrecto en cada campo de texto
//enviarles un sms de advertencia
    public String readYear() {
        try {
            String year = (fieldYear.getText());
            return year;
        } catch (Exception e) {
            return null;
        }
    }

    public String readType() {
        try {
            String type = fieldType.getText();
            return type;
        } catch (Exception e) {
            return null;
        }
    }

    public int readEnumeration() {
        try {
            int enumeration = Integer.parseInt(fieldEnumeration.getText());
            return enumeration;
        } catch (NumberFormatException e) {
            return -666;
        }
    }

    public String readCode() {
        try {
            String code = (fieldCode.getText());
            return code;
        } catch (Exception e) {
            return null;
        }
    }

    public String readName() {
        try {
            String name = (fieldName.getText());
            return name;
        } catch (Exception e) {
            return null;
        }
    }

    public int readNumberBookAvailable() {
        try {
            int numberBoAv = Integer.parseInt(fieldBooks.getText());
            return numberBoAv;
        } catch (NumberFormatException e) {
            return -666;
        }
    }

    public Object readDescription() {
        try {
            Object place = (fieldDescription.getText());
            return place;
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] readPhoto(File ruta) {
        try {
            byte[] icono = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(icono);
            return icono;
        } catch (IOException ex) {
            return null;
        }
    }

    public String readAuthor() {
        try {
            String author = fieldAuthor.getText();
            return author;
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] readPhoto2(int code) {
        beansBooks = method.getRecord(code);
        try {
            return beansBooks.getPhoto();
        } catch (Exception ex) {
            return null;
        }
    }

    public void listarRegistro() {
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // dt.addColumn("Enumeration");
        dt.addColumn("Enumeration");
        dt.addColumn("Name");
        dt.addColumn("Code");
        dt.addColumn("Number book Availabe");
//         dt.addColumn("Year");
//         dt.addColumn("Author");
//         dt.addColumn("Descripcion");
//dt.addColumn("Type book");
        dt.addColumn("Foto");

        table.setDefaultRenderer(Object.class, new ImageTable());

        Object row[] = new Object[dt.getColumnCount()];

        for (int i = 0; i < method.quantifyRecord(); i++) {

            beansBooks = method.getRecord(i);

            row[0] = beansBooks.getEnumeration();
            row[1] = beansBooks.getNameBook();
            row[2] = beansBooks.getCodeBook();
            row[3] = beansBooks.getNumberBookAvailable();
//            row[4]=beansBooks.getYear();
//            row[5] = beansBooks.getAuthor();
//            row[6] = beansBooks.getDescription();
//            row[7]=beansBooks.getTipoLibro();
//           

            try {
                byte[] bi = beansBooks.getPhoto();
                BufferedImage imagee = null;
                InputStream in = new ByteArrayInputStream(bi);
                imagee = ImageIO.read(in);
                ImageIcon img = new ImageIcon(imagee.getScaledInstance(60, 60, 0));
                row[4] = new JLabel(img);
            } catch (IOException e) {
                row[4] = "No imagen";
            }

            dt.addRow(row);
        }
        table.setModel(dt);
        table.setRowHeight(60);
    }
    
    public String quantifyNull() {
        try {
            String quantify = "";
            if (quantify.equals(fieldBooks.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
            } else {

                return quantify;
            }
        } catch (Exception e) {
            System.out.println("ERROR");

        }
        return null;
    }

    public String nameNull() {
        try {
            String name = "";
            if (name.equals(fieldName.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
            } else {
                return name;
            }
        } catch (Exception e) {
            System.out.println("ERROR");

        }
        return null;
    }

    public String codeNull() {
        try {
            String code = "";
            if (code.equals(fieldCode.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
            } else {
                return code;
            }
        } catch (Exception e) {
            System.out.println("ERROR");

        }
        return null;
    }

    public String authorNull() {
        try {
            String author = "";
            if (author.equals(fieldAuthor.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
            } else {
                return author;
            }
        } catch (HeadlessException e) {
            System.out.println("ERROR");

        }
        return null;
    }

    public String descrptionNull() {
        try {
            String description = "";
            if (description.equals(fieldDescription.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
            } else {
                return description;
            }
        } catch (Exception e) {
            System.out.println("ERROR");

        }
        return null;
    }
    public String typeBookNull() {
        try {
            String typeBook = "";
            if (typeBook.equals(fieldType.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
            } else {
                return typeBook;
            }
        } catch (Exception e) {
            System.out.println("ERROR");

        }
        return null;
    }
   
    
    
    
    public String yearNull() {
        try {
            String year = "";
            if (year.equals(fieldYear.getText())) {
                JOptionPane.showMessageDialog(null, "Incorrect date");
                return null;
                
                
            } else {
                return year;
            }
        } catch (Exception e) {
            System.out.println("Incorrect date");

        }
        return null;
    }

 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldCode = new javax.swing.JTextField();
        fieldName = new javax.swing.JTextField();
        fieldBooks = new javax.swing.JTextField();
        fieldDescription = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fieldPhoto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fieldEnumeration = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lPhoto = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fieldAuthor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        fieldYear = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fieldType = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Code:");

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Quantify:");

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Description:");

        fieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("Photo");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.white));
        jLabel6.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Numeration:");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Enumeration", "Name", "Code", "Foto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit_clear.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_contact-new_23203.png"))); // NOI18N
        lPhoto.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));

        jButton3.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/insert_image.png"))); // NOI18N
        jButton3.setText("Search photo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/arrow_undo.png"))); // NOI18N
        jButton4.setText("Return");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/library (1).png"))); // NOI18N
        jLabel5.setText("Register Book phisiques");

        jLabel8.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("Author:");

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(240, 240, 240));
        jLabel9.setText("Year of publication");

        jLabel10.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(240, 240, 240));
        jLabel10.setText("Type book:");

        fieldType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(fieldEnumeration, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanelLayout.createSequentialGroup()
                                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(jLabel10))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(fieldBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                                            .addComponent(fieldType))))
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel1))
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(fieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                                    .addComponent(fieldCode)
                                                    .addComponent(fieldAuthor)
                                                    .addComponent(fieldYear))))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(30, 30, 30)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(fieldEnumeration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fieldBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(fieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(fieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(fieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addComponent(fieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         int contador=1;
        try {
       
            File pathh = new File(fieldPhoto.getText());
              this.ingresarRegistro(pathh);
             if(evt.getSource()==jButton1){   
                 contador=contador+1;
                fieldEnumeration.setText(String.valueOf(contador ));
           
                      
             }
        } catch (Exception e) {
            message("Date incorrete jb");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        jf.setFileFilter(fil);
        jf.setCurrentDirectory(new File("Fotos"));
        int el = jf.showOpenDialog(this);
        if (el == JFileChooser.APPROVE_OPTION) {
            fieldPhoto.setText(jf.getSelectedFile().getAbsolutePath());
            lPhoto.setIcon(new ImageIcon(fieldPhoto.getText()));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DeleteoAllOfField();

    }//GEN-LAST:event_jButton2ActionPerformed

    public void DeleteoAllOfField() {

        fieldBooks.setText("");
        fieldCode.setText("");
        fieldDescription.setText("");
        fieldEnumeration.setText("");
        fieldName.setText("");
        fieldAuthor.setText("");
        fieldYear.setText("");
        fieldType.setText("");

    }
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void fieldTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTypeActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterBookInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterBookInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterBookInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterBookInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterBookInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField fieldAuthor;
    public javax.swing.JTextField fieldBooks;
    public javax.swing.JTextField fieldCode;
    public javax.swing.JTextField fieldDescription;
    public javax.swing.JTextField fieldEnumeration;
    public javax.swing.JTextField fieldName;
    public javax.swing.JTextField fieldPhoto;
    private javax.swing.JTextField fieldType;
    private javax.swing.JTextField fieldYear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lPhoto;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

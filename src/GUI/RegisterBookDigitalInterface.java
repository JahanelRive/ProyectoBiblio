/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import domain.BeansDigital;
import domain.ImageTable;
import domain.ImageTable2;
import domain.MethodDigital;
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
public class RegisterBookDigitalInterface extends javax.swing.JFrame {

    MethodDigital method;
    int clic_tabla;
    String path = "registerDigitalBooks.bin";
    BeansDigital beansDigital;

    /**
     * Creates new form RegisterBookDigitalInterface
     */
    public RegisterBookDigitalInterface() {
        initComponents();
        method = new MethodDigital();
       
        try{
            readSerializable();
            listarRegistro();
             fieldPhoto.setEditable(false);
        }catch (Exception e) {
            System.out.println("Error");
        }

    }

    ///READ
    /*Este metodo lo que hace es leer nuestro archivo, en la condicion, preguntamos, si lo que hay en nuestro archivo es diferente de null
    entonces que nos lo escriba, le hacemmos un canstins*/
    public void readSerializable() {
        try {
            FileInputStream fis = new FileInputStream(path);///le mandamos la ruta para que lea nuestro archivo
            ObjectInputStream in = new ObjectInputStream(fis);//lee directamnete en el archivo
            if (in != null) {///preguntamos si el archivo no esta vacio, que me lo lea
                method = (MethodDigital) in.readObject();//leemos el archuvos
                in.close();//cerramos el archivo
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al readSerializable read digital");

        }
    }

    /////////ESCRIBE EN EL ARCHIVO///////////////
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
            System.out.println("error en writeBin read digital");
        }
    } ////////////END WRITE//////////////////////  

public void message(String sms){//este metodo es para enviar un sms  de advertencia al usuario, en caso de un error
        JOptionPane.showMessageDialog(null, sms);
    }
 
    public void enterRecord(File path) {
        try {
           
      
              if (nameNull().equals(null)) message("Dato incorrecto ");
             else if (codeNull().equals(null))  message("Incorrect date");
             else if(descrptionNull().equals(null)) message("Incorrect date");
             else if(yearNull().equals(null)) message("Incorrect date");
             else if(authorNull().equals(null))message("Incorrect date");
             else if(typeBookNull().equals(null))message("Incorrect date");
              else {

                beansDigital = new BeansDigital(readName(),readCode(),readPhoto(path), readYear(), readAuthor(), readDescription(), readType());
                System.out.println("3");
                if(method.searchCode(beansDigital.getCode())!=null){
                    message("This code its repeat");
                } 
                else {
                    method.addRecord(beansDigital);
                }
                System.out.println("4");
                writeSerializable();//quitar
                System.out.println("5");
                listarRegistro();
                System.out.println("6");
            }
            System.out.println("7");
        } catch (Exception e) {
            System.out.println("INGRESASTE UN DATO ERRONEO" ); //message("erros");
        }
    } 
//    

    
    public String readYear(){
     try{   
    String year= fieldYear.getText();
    return year;
     }catch(Exception e){
          return null;   
             }
    }
    
       public String readAuthor(){
       try{
       String author= fieldAuthor.getText();
       return author;
       }catch(Exception e){
       return null;
       }
       
       }
       
       public String readType(){
       try{
       String typeBook= fieldBook.getText();
       return typeBook;
       }catch(Exception e){
       return null;
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
      
    public Object readDescription() {
        try {
            Object description = (fieldDescription.getText());
            return description;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public byte[] readPhoto(File path) {
        try {
            byte[] icono = new byte[(int) path.length()];
            InputStream input = new FileInputStream(path);
            input.read(icono);
            return icono;
        } catch (IOException ex) {
            return null;
        }
    }

 
 
public byte[] readPhoto2(int code){
            beansDigital = method.getRecord(code);
            try{
               return beansDigital.getPhoto();
            }catch(Exception ex){
               return null;
            }
        }

    ///method parte 2


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
            if (typeBook.equals(fieldBook.getText())) {
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


         public void listarRegistro(){
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

            // modelo.addColumn("Numeration");
           
             modelo.addColumn("Name ");
             modelo.addColumn("Code");
             modelo.addColumn("Photo ");

             table.setDefaultRenderer(Object.class, new ImageTable2());//para que la imagen se muestre en el JTable

             Object row[] = new Object[modelo.getColumnCount()];

             for (int i = 0; i < method.quantifyRecord(); i++) {
                 beansDigital = method.getRecord(i);

        
                
                 row[0] = beansDigital.getName();
                 row[1] = beansDigital.getCode();
                 
            

            try{
                byte[] bi = beansDigital.getPhoto();
                BufferedImage imagee = null;
                InputStream in = new ByteArrayInputStream(bi);
                imagee = ImageIO.read(in);
                ImageIcon img = new ImageIcon(imagee.getScaledInstance(60, 60, 0));
                row[2] = new JLabel(img);

            } catch (IOException e) {
                row[2] = "No imagen";
            }

            modelo.addRow(row);
        }
        table.setModel(modelo);
        table.setRowHeight(60);
    }


    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lPhoto = new javax.swing.JLabel();
        fieldCode = new javax.swing.JTextField();
        fieldName = new javax.swing.JTextField();
        fieldDescription = new javax.swing.JTextField();
        fieldPhoto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        aceept = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fieldYear = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fieldAuthor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        fieldBook = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Code:");

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Description:");

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("Photo:");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));

        lPhoto.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        lPhoto.setForeground(new java.awt.Color(255, 153, 153));
        lPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/if_contact-new_23203.png"))); // NOI18N
        lPhoto.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));

        fieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNameActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/insert_image.png"))); // NOI18N
        jButton1.setText("Search photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Code", "Photo"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        aceept.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        aceept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        aceept.setText("Save");
        aceept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceeptActionPerformed(evt);
            }
        });

        limpiar.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit_clear.png"))); // NOI18N
        limpiar.setText("Clean");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/arrow_undo.png"))); // NOI18N
        jButton2.setText("Return");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Handwriting", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file_pdf.png"))); // NOI18N
        jLabel6.setText("Register Book Digital");

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Publication");

        jLabel8.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("Author");

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(240, 240, 240));
        jLabel9.setText("Type of Book:");

        fieldBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(fieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(fieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fieldBook, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(jLabel7)
                                    .addGap(32, 32, 32)
                                    .addComponent(fieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lPhoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))))
                .addGap(205, 205, 205))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(aceept, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(fieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(fieldBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(fieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceept)
                    .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNameActionPerformed

    private void aceeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceeptActionPerformed
  File path = new File(fieldPhoto.getText());
    enterRecord(path);
    }//GEN-LAST:event_aceeptActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        jf.setFileFilter(fil);
        jf.setCurrentDirectory(new File("Fotos"));
        int el = jf.showOpenDialog(this);
        if(el == JFileChooser.APPROVE_OPTION){
            fieldPhoto.setText(jf.getSelectedFile().getAbsolutePath());
            lPhoto.setIcon(new ImageIcon(fieldPhoto.getText()));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
     fieldCode.setText("");
     fieldDescription.setText("");
     fieldName.setText("");
    
     
    }//GEN-LAST:event_limpiarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
    }//GEN-LAST:event_tableMouseClicked

    private void fieldBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldBookActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterBookDigitalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterBookDigitalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterBookDigitalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterBookDigitalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterBookDigitalInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceept;
    private javax.swing.JTextField fieldAuthor;
    private javax.swing.JTextField fieldBook;
    private javax.swing.JTextField fieldCode;
    private javax.swing.JTextField fieldDescription;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldPhoto;
    private javax.swing.JTextField fieldYear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lPhoto;
    private javax.swing.JButton limpiar;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}

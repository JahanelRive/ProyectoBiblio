/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import domain.BeansAudiovisual;
import domain.ImageTable;
import domain.MethodAudioVisual;
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
public class AudiovisualInterface extends javax.swing.JFrame {
    MethodAudioVisual method;
    int clic_tabla;
    String path = "registerAudioVisual.bin";
    BeansAudiovisual beansAudioVisual;

    public AudiovisualInterface() {
        initComponents();
        method = new MethodAudioVisual();
        beansAudioVisual= new BeansAudiovisual();
        try{
           readSerializable();
           listarRegistro();
            
        }catch(Exception e){System.out.println("Error");}
    }

    
     /*Este metodo lo que hace es leer nuestro archivo, en la condicion, preguntamos, si lo que hay en nuestro archivo es diferente de null
    entonces que nos lo escriba, le hacemmos un canstins*/
    public void readSerializable() {
        try {
            FileInputStream fis = new FileInputStream(path);///le mandamos la ruta para que lea nuestro archivo
            ObjectInputStream in = new ObjectInputStream(fis);//lee directamnete en el archivo
            if (in != null) {///preguntamos si el archivo no esta vacio, que me lo lea
                method = (MethodAudioVisual) in.readObject();//leemos el archuvos
                in.close();//cerramos el archivo
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al readSerializable");

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
            System.out.println("error en writeBin");
        }
    }
    
  ////////////END WRITE//////////////////////  
    
 public void message(String sms){//este metodo es para enviar un sms  de advertencia al usuario, en caso de un error
        JOptionPane.showMessageDialog(null, sms);
    }
 
    public void enterRecord(File path) {
        try {
           
             if (readCode() == -666) 
                message("Dato incorrecto C");
             else if (readName() == null) 
                message("Dato incorrecto N");
             else if (readQuantify() == -666) 
                message("Dato incorrecto Avalible");
            
            
              else {

                beansAudioVisual = new BeansAudiovisual(readCode(), readName(), readQuantify(),  readPhoto(path));
                System.out.println("3");
                if (method.searchEnumeration(beansAudioVisual.getCode()) != -1) {
                    message("This enumeration its repeat");
                } else {
                    method.addRecord(beansAudioVisual);
                }
                System.out.println("4");
                writeSerializable();//quitar
                System.out.println("5");
                listarRegistro();
                System.out.println("6");
            }
            System.out.println("7");
        } catch (Exception e) {
            System.out.println("Error en enterToRegister" + e);
        }
    } 
    
       public void ModyfyRecordd(File path) {
        try {
          
              if (readCode() == -666) 
                message("Incorrect date");
             else if (readName() == null) 
                message("Incorrect date");
             else if (readQuantify() == -666) 
                message("Incorrect date");
            
         
             else {
                int code = method.searchEnumeration(readCode());
                if (fieldPhoto.getText().equalsIgnoreCase(""))//en caso al reg la ruta f este vacia
                
          beansAudioVisual = new BeansAudiovisual(readCode(), readName(), readQuantify(),  readPhoto2(code));
                 else 
                    beansAudioVisual = new BeansAudiovisual(readCode(), readName(), readQuantify(), readPhoto(path));
                
                if (code == -1)  method.addRecord(beansAudioVisual);
                 else 
                    method.modifyRecord(code, beansAudioVisual);
                

                writeSerializable();
                listarRegistro();
            }
        } catch (Exception e) {
            System.out.println("error listar registro");
        }
    }

       public void deleteRecordd() {
        try {
            if (readCode() == -666) 
                message("date Inco");
             else {
                int code = method.searchEnumeration(readCode());
                if (code == -1) 
                    message("Enumeration exis");
                 else {
                    int s = JOptionPane.showConfirmDialog(null, "delete");
                    if (s == 0) {
                        method.deleteRecord(code);

                        writeSerializable();
                        listarRegistro();
                    }
                }
            }
        } catch (HeadlessException e) {
        }

    }
    
    
       public int readCode() {
        try {
            int code = Integer.parseInt(fieldCode.getText());
            return code;
        } catch (NumberFormatException e) {
            return -666;
        }
    }
 
    
    
       public int readQuantify() {
        try {
            int quantify = Integer.parseInt(fieldQuantify.getText());
            return quantify;
        } catch (NumberFormatException e) {
            return -666;
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
            beansAudioVisual = method.getRecord(code);
            try{
               return beansAudioVisual.getPhoto();
            }catch(Exception ex){
               return null;
            }
        }

    
    
     public void listarRegistro(){
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

     modelo.addColumn("Code");
    modelo.addColumn("Name");
     modelo.addColumn("Quanitfy ");
   
    
     modelo.addColumn("Foto");
     table.setDefaultRenderer(Object.class, new ImageTable());
  
        Object row[] = new Object[modelo.getColumnCount()];
     
        for(int i = 0; i < method.quantifyRecord(); i++){
         beansAudioVisual = method.getRecord(i);
          
            row[0] = beansAudioVisual.getCode();
            row[1] = beansAudioVisual.getName();
            row[2] = beansAudioVisual.getQuantify();
            
          try{
                
            byte[] bi =beansAudioVisual.getPhoto();
            BufferedImage imagee=null;
            InputStream in= new ByteArrayInputStream(bi);
           imagee=ImageIO.read(in);
           ImageIcon img= new ImageIcon(imagee.getScaledInstance(60,60,0));
            row[3]= new JLabel(img);
            
            }catch(IOException e){
             row[3]= "No imagen";
            }
          
            modelo.addRow(row);
        }
        table.setModel(modelo);
        table.setRowHeight(60);
    }


    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldCode = new javax.swing.JTextField();
        fieldName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldQuantify = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fieldPhoto = new javax.swing.JTextField();
        lPhoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Code:");

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity:");

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 204));
        jLabel5.setText("Photo:");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icontexto_user_web20_netvibes.png"))); // NOI18N
        lPhoto.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));

        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/insert_image.png"))); // NOI18N
        jButton1.setText("Search photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jButton2.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1492533564-arrow-43_83281.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Handwriting", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action_exit_close_remove_13915.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/arrow_undo.png"))); // NOI18N
        jButton5.setText("Return");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit_clear.png"))); // NOI18N
        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/audio_volume_high (2).png"))); // NOI18N
        jLabel3.setText("AudioVisual Record");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(62, 62, 62)
                                            .addComponent(fieldQuantify, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel2))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(53, 53, 53)
                                .addComponent(jButton3)
                                .addGap(48, 48, 48)
                                .addComponent(jButton4)
                                .addGap(34, 34, 34)
                                .addComponent(jButton6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldPhoto)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldQuantify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2))
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(fieldPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    File path= new File(fieldPhoto.getText());
    enterRecord(path);
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    fieldCode.setText("");
    fieldName.setText("");
    fieldQuantify.setText("");
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      File path= new File(fieldPhoto.getText());
        ModyfyRecordd(path);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      deleteRecordd();
    }//GEN-LAST:event_jButton4ActionPerformed

    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(AudiovisualInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AudiovisualInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AudiovisualInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AudiovisualInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AudiovisualInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fieldCode;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldPhoto;
    private javax.swing.JTextField fieldQuantify;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lPhoto;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

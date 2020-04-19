/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apps;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Gui extends javax.swing.JFrame {

    private ServerSocket ss;
    private PrintWriter pw;
    private String oldMsg = "";
    private HttpURLConnection httpCon;
    private URL url;
    private String IP;

    /**
     * Creates new form Gui
     */
    public Gui() {
        try {
            initComponents();
            IP = JOptionPane.showInputDialog(this, "Indique IP del Servidor", "Board NodeMcu v3", JOptionPane.INFORMATION_MESSAGE);
            if (IP.length() == 0) {
                IP = "192.168.1.131";

            }
            jTextFieldIP.setText(IP);
            url = new URL("http://" + IP);
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setDoOutput(true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelControles = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonControlesUp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonControlesLeft = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jButtonControlesRight = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonControlesDown = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanelTop = new javax.swing.JPanel();
        jToggleButtonLuz = new javax.swing.JToggleButton();
        jButtonClaxon = new javax.swing.JButton();
        jPanelDown = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Android Conectado!!!");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelControles.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));
        jPanelControles.setLayout(new java.awt.GridLayout(3, 3));
        jPanelControles.add(jLabel1);

        jButtonControlesUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/up_arrow.png"))); // NOI18N
        jButtonControlesUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesUp);
        jPanelControles.add(jLabel2);

        jButtonControlesLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/left_arrow.png"))); // NOI18N
        jButtonControlesLeft.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesLeft);

        jButtonStop.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButtonStop.setText("STOP");
        jButtonStop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonStop);

        jButtonControlesRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/right_arrow.png"))); // NOI18N
        jButtonControlesRight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesRight);
        jPanelControles.add(jLabel3);

        jButtonControlesDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/down_arrow.png"))); // NOI18N
        jButtonControlesDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonPulsado(evt);
            }
        });
        jPanelControles.add(jButtonControlesDown);
        jPanelControles.add(jLabel4);

        getContentPane().add(jPanelControles, java.awt.BorderLayout.CENTER);

        jPanelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));
        jPanelTop.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jToggleButtonLuz.setText("Luces");
        jToggleButtonLuz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLuzActionPerformed(evt);
            }
        });
        jPanelTop.add(jToggleButtonLuz);

        jButtonClaxon.setText("Claxon");
        jButtonClaxon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClaxonActionPerformed(evt);
            }
        });
        jPanelTop.add(jButtonClaxon);

        getContentPane().add(jPanelTop, java.awt.BorderLayout.SOUTH);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("IP Server");
        jPanelDown.add(jLabel5);

        jTextFieldIP.setText("Indique la IP del servidor antes de manipularlo");
        jPanelDown.add(jTextFieldIP);

        getContentPane().add(jPanelDown, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarDatos(String comando, String accion) {
        try {
            //url = new URL("http://" + jTextFieldIP.getText() + "/?comando=" + comando + "&accion=" + accion);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("comando", comando);
            parameters.put("accion", accion);

            DataOutputStream out = new DataOutputStream(httpCon.getOutputStream());
            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("" + url);
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getParamsString(Map<String, String> params) {            
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }


    private void botonPulsado(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonPulsado
        String s = null;

        switch (evt.getKeyChar()) {
            case '8':
                s = "AD"; //ADELANTE";
                break;
            case '4':
                s = "IZ"; //IZQUIERDA";
                break;
            case '6':
                s = "DE"; //DERECHA";
                break;
            case '5':
                s = "ST"; //STOP";
                break;
            case '2':
                s = "AT"; // ATRAS
                break;
        }

        if (!oldMsg.equals(s)) { // Solo enviamos los cambios
            enviarDatos(s, "void");

        }
        oldMsg = s;


    }//GEN-LAST:event_botonPulsado

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            pw.close();
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButtonClaxonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClaxonActionPerformed
        enviarDatos("CL", "void");
        oldMsg = "CL";
    }//GEN-LAST:event_jButtonClaxonActionPerformed

    private void jToggleButtonLuzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLuzActionPerformed
        if (jToggleButtonLuz.isSelected()) {
            enviarDatos("LU", "1");
            oldMsg = "LU1";
        } else {
            enviarDatos("LU", "0");
            oldMsg = "LU0";
        }
    }//GEN-LAST:event_jToggleButtonLuzActionPerformed

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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClaxon;
    private javax.swing.JButton jButtonControlesDown;
    private javax.swing.JButton jButtonControlesLeft;
    private javax.swing.JButton jButtonControlesRight;
    private javax.swing.JButton jButtonControlesUp;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelDown;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JTextField jTextFieldIP;
    private javax.swing.JToggleButton jToggleButtonLuz;
    // End of variables declaration//GEN-END:variables
}

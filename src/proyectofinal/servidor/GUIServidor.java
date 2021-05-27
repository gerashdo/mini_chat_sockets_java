/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.servidor;

import proyectofinal.servidor.tcp.ServidorTCP;
import proyectofinal.servidor.udp.AudioServerUDP;
import proyectofinal.servidor.udp.ServidorUDPMensaje;
import proyectofinal.servidor.udp.WebcamServerUDP;

/**
 *
 * @author Gerardo Jimenez Arguelles
 * @author José Jimenez Arguelles
 */
public class GUIServidor extends javax.swing.JFrame {
    protected ServidorUDPMensaje servidorMensajes;
    protected ServidorTCP servidorTCP;

    /**
     * Creates new form NewJFrame
     */
    public GUIServidor(ServidorUDPMensaje servidorUDP) throws Exception{
        initComponents();
        servidorMensajes = servidorUDP;
        servidorMensajes.inicia(mensajesTextArea);
        servidorTCP = new ServidorTCP(50001);
        servidorTCP.setMensajesTextArea(mensajesTextArea);
        servidorTCP.inicia();
        new WebcamServerUDP(videoLabel);
        new AudioServerUDP().iniciar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mensajesScrollP = new javax.swing.JScrollPane();
        mensajesTextArea = new javax.swing.JTextArea();
        videoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mensajesTextArea.setEditable(false);
        mensajesTextArea.setColumns(20);
        mensajesTextArea.setRows(5);
        mensajesScrollP.setViewportView(mensajesTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensajesScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(videoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mensajesScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(videoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane mensajesScrollP;
    private javax.swing.JTextArea mensajesTextArea;
    private javax.swing.JLabel videoLabel;
    // End of variables declaration//GEN-END:variables
}

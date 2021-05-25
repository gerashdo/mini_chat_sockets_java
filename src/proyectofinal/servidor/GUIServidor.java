/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.servidor;

import proyectofinal.servidor.tcp.ServidorTCP;
import proyectofinal.servidor.udp.ServidorUDP;
import proyectofinal.servidor.udp.ServidorUDPMensaje;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mensajesTextArea.setEditable(false);
        mensajesTextArea.setColumns(20);
        mensajesTextArea.setRows(5);
        mensajesScrollP.setViewportView(mensajesTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensajesScrollP, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mensajesScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane mensajesScrollP;
    private javax.swing.JTextArea mensajesTextArea;
    // End of variables declaration//GEN-END:variables
}

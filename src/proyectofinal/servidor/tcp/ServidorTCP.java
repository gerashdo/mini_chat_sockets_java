package proyectofinal.servidor.tcp;

import javax.swing.*;

public class ServidorTCP{
    protected final int PUERTO_SERVER;
    private javax.swing.JTextArea mensajesTextArea;
    
    public ServidorTCP(int puertoS){
        PUERTO_SERVER=puertoS;
    }
    
    public void inicia()throws Exception{
        ServidorEscuchaTCP servidorTCP=new ServidorEscuchaTCP(PUERTO_SERVER);
        servidorTCP.setMensajesTextArea(mensajesTextArea);
        servidorTCP.start();
    }

    public void setMensajesTextArea(JTextArea mensajesTextArea) {
        this.mensajesTextArea = mensajesTextArea;
    }
}

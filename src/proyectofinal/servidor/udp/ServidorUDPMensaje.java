package proyectofinal.servidor.udp;

import javax.swing.*;

public class ServidorUDPMensaje extends ServidorUDP{
    protected JTextArea areaGUI;
    private JLabel label;

    public ServidorUDPMensaje(int puertoS) {
        super(puertoS);
    }

    public void inicia(JTextArea areaMensajes, JLabel label) throws Exception {
        this.label = label;
        areaGUI = areaMensajes;
        ServidorEscuchaUDPMensaje servidorUDP=new ServidorEscuchaUDPMensaje(PUERTO_SERVER, areaGUI, label);
        servidorUDP.start();


    }
}

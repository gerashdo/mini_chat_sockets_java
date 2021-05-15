package proyectofinal.servidor.udp;

import javax.swing.*;

public class ServidorUDPMensaje extends ServidorUDP{
    protected JTextArea areaGUI;

    public ServidorUDPMensaje(int puertoS) {
        super(puertoS);
    }

    public void inicia(JTextArea areaMensajes) throws Exception {
        areaGUI = areaMensajes;
        ServidorEscuchaUDPMensaje servidorUDP=new ServidorEscuchaUDPMensaje(PUERTO_SERVER, areaGUI);
        servidorUDP.start();


    }
}

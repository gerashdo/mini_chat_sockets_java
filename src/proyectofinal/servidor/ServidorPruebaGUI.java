package proyectofinal.servidor;

import proyectofinal.servidor.udp.ServidorUDPMensaje;

public class ServidorPruebaGUI {
    public static void main(String[] args) throws Exception{
        ServidorUDPMensaje servidorUDP=new ServidorUDPMensaje(50000);

        // Creamos una instancia de la GUI del servidor
        GUIServidor gui = new GUIServidor(servidorUDP);
        gui.setVisible(true);
    }
}

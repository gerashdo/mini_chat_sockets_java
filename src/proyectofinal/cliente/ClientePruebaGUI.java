package proyectofinal.cliente;

import proyectofinal.cliente.tcp.ClienteTCP;
import proyectofinal.cliente.udp.ClienteUDPMensaje;

public class ClientePruebaGUI {
    public static void main(String[] args) throws Exception{
        //Crear Socket de clienteUDP para mensajes
        ClienteUDPMensaje clienteUDPMensajes =new ClienteUDPMensaje("192.168.0.6",50000);

        //Muestra la gui del cliente
        GUICliente gui = new GUICliente(clienteUDPMensajes, "192.168.0.6");
        gui.setVisible(true);
    }
}

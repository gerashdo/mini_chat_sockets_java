package proyectofinal.cliente;

import proyectofinal.cliente.tcp.ClienteTCP;
import proyectofinal.cliente.udp.ClienteUDPMensaje;

public class ClientePruebaGUI {
    public static void main(String[] args) throws Exception{
        //Crear Socket de clienteUDP para mensajes
        ClienteUDPMensaje clienteUDPMensajes =new ClienteUDPMensaje("192.168.0.7",50000);
        ClienteTCP clienteTCP = new ClienteTCP("192.168.0.7",50001);

        //Muestra la gui del cliente
        GUICliente gui = new GUICliente(clienteUDPMensajes, clienteTCP);
        gui.setVisible(true);
    }
}

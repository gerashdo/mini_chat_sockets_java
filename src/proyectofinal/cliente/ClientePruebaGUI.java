package proyectofinal.cliente;

import proyectofinal.cliente.udp.ClienteUDPMensaje;

public class ClientePruebaGUI {
    public static void main(String[] args) throws Exception{
        //Crear Socket de clienteUDP para mensajes
        ClienteUDPMensaje clienteUDPMensajes =new ClienteUDPMensaje("192.168.0.19",50000);

        //Muestra la gui del cliente
        GUICliente gui = new GUICliente(clienteUDPMensajes, "192.168.0.19");
        gui.setVisible(true);
    }
}

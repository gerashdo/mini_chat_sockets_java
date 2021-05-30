package proyectofinal.cliente;

import proyectofinal.cliente.udp.ClienteUDPMensaje;

public class ClientePruebaGUI {
    public static void main(String[] args) throws Exception{
        //Crear Socket de clienteUDP para mensajes


        //Muestra la gui del cliente
        GUICliente gui = new GUICliente("192.168.0.6");
        gui.setVisible(true);
    }
}

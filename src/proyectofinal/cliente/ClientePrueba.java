package proyectofinal.cliente;

import proyectofinal.cliente.udp.ClienteUDP;

public class ClientePrueba {
    public static void main(String[] args) throws Exception{
        //Crear Socket de clienteUDP para mensajes
        ClienteUDP clienteUDPMensajes =new ClienteUDP("192.168.0.7",50000);



        //Muestra la gui del cliente
        GUICliente gui = new GUICliente(clienteUDPMensajes);
        gui.setVisible(true);
    }
}

package proyectofinal.cliente.udp;

import javax.swing.*;
import java.net.DatagramSocket;

public class ClienteUDPMensaje extends ClienteUDP{
    protected ClienteEnviaMensajeUDP clienteEnvUDP;
    protected ClienteEscuchaMensajeUDP clienteEscUDP;
    protected JTextArea textAreaGUICliente;

    public ClienteUDPMensaje(String servidor, int puertoS) {
        super(servidor, puertoS);

    }

    public void inicia(JTextArea jTextArea) throws Exception {
        textAreaGUICliente = jTextArea;
        DatagramSocket socket=new DatagramSocket();

        clienteEscUDP=new ClienteEscuchaMensajeUDP(socket, textAreaGUICliente);
        clienteEnvUDP=new ClienteEnviaMensajeUDP(socket, SERVER, PUERTO_SERVER);

        clienteEnvUDP.start();
        clienteEscUDP.start();
    }

    public void leerMensaje(String mensaje) {
        clienteEnvUDP.setMensaje(mensaje);
        clienteEnvUDP.run();
    }
}

package proyectofinal.cliente.udp;

import proyectofinal.cliente.tcp.ClienteEnviaMensajeUDP;

import java.net.*;
import java.io.*;
 
//declaramos la clase udp
public class ClienteUDP{
    protected final int PUERTO_SERVER;
    protected final String SERVER;
    protected ClienteEscuchaUDP clienteEscUDP;
    protected ClienteEnviaMensajeUDP clienteEnvUDP;
    
    public ClienteUDP(String servidor, int puertoS){
        PUERTO_SERVER=puertoS;
        SERVER=servidor;
    }
    
    public void inicia()throws Exception{
        DatagramSocket socket=new DatagramSocket();
        
        clienteEscUDP=new ClienteEscuchaUDP(socket);
        clienteEnvUDP=new ClienteEnviaMensajeUDP(socket, SERVER, PUERTO_SERVER);
        
        clienteEnvUDP.start();
        clienteEscUDP.start();
    }

    public void leerMensaje(String mensaje) {
        clienteEnvUDP.setMensaje(mensaje);
        clienteEnvUDP.run();
    }
}

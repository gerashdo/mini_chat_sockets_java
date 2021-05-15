package proyectofinal.cliente.udp;

import java.net.*;

//declaramos la clase udp
public class ClienteUDP{
    protected final int PUERTO_SERVER;
    protected final String SERVER;
    protected ClienteEscuchaUDP clienteEscUDP;
    protected ClienteEnviaUDP clienteEnvUDP;
    
    public ClienteUDP(String servidor, int puertoS){
        PUERTO_SERVER=puertoS;
        SERVER=servidor;
    }
    
    public void inicia()throws Exception{
        DatagramSocket socket=new DatagramSocket();
        
        clienteEscUDP=new ClienteEscuchaUDP(socket);
        clienteEnvUDP=new ClienteEnviaUDP(socket, SERVER, PUERTO_SERVER);
        
        clienteEnvUDP.start();
        clienteEscUDP.start();
    }
}

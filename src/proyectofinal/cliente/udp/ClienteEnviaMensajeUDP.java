package proyectofinal.cliente.udp;

import proyectofinal.cliente.udp.ClienteEnviaUDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteEnviaMensajeUDP extends ClienteEnviaUDP {
    protected String mensaje;

    public ClienteEnviaMensajeUDP(DatagramSocket nuevoSocket, String servidor, int puertoServidor) {

        super(nuevoSocket, servidor, puertoServidor);
        mensaje = "";
    }

    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public void run() {


        byte[] mensaje_bytes;
        mensaje_bytes=mensaje.getBytes();

        String cadenaMensaje="";

        byte[] RecogerServidor_bytes;

        try {
            address= InetAddress.getByName(SERVER);

            mensaje_bytes=new byte[mensaje.length()];
            mensaje_bytes = mensaje.getBytes();
            paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,PUERTO_SERVER);
            socket.send(paquete);

            String mensajeMandado=new String(paquete.getData(),0,paquete.getLength()).trim();
            System.out.println("Mensaje \""+ mensajeMandado +
                    "\" enviado a "+paquete.getAddress() + "#"+paquete.getPort());
        }
        catch (Exception e) {
            System.err.println("Exception "+e.getMessage());
            System.exit(1);
        }
    }
}

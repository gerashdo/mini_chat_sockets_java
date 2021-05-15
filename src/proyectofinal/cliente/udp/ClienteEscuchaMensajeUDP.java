package proyectofinal.cliente.udp;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClienteEscuchaMensajeUDP extends ClienteEscuchaUDP{
    protected JTextArea textAreaGUICliente;

    public ClienteEscuchaMensajeUDP(DatagramSocket socketNuevo, JTextArea textArea) {
        super(socketNuevo);
        textAreaGUICliente = textArea;
    }

    @Override
    public void run() {
        byte[] mensaje_bytes;
        String mensaje="";
        mensaje_bytes=mensaje.getBytes();

        String cadenaMensaje="";

        byte[] recogerServidor_bytes;

        try {
            do {
                recogerServidor_bytes = new byte[MAX_BUFFER];

                //Esperamos a recibir un paquete
                servPaquete = new DatagramPacket(recogerServidor_bytes,MAX_BUFFER);
                socket.receive(servPaquete);

                //Convertimos el mensaje recibido en un string
                cadenaMensaje = new String(recogerServidor_bytes).trim();

                //Imprimimos el paquete recibido
                System.out.println("Mensaje recibido \""+cadenaMensaje +"\" de "+
                        servPaquete.getAddress()+"#"+servPaquete.getPort());

                //Agregamos el texto al text area de la GUI del cliente para que muestre el mensaje recibido.
                textAreaGUICliente.append("Contacto: "+cadenaMensaje +"\n");

            } while (!cadenaMensaje.startsWith("fin"));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Excepcion C: "+e.getMessage());
            System.exit(1);
        }
    }
}

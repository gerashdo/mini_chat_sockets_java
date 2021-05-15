package proyectofinal.servidor.udp;

import javax.swing.*;
import java.net.DatagramPacket;

public class ServidorEscuchaUDPMensaje extends ServidorEscuchaUDP{
    protected JTextArea areaGUI;

    public ServidorEscuchaUDPMensaje(int puertoS, JTextArea areaGUI) throws Exception {
        super(puertoS);
        this.areaGUI = areaGUI;
    }

    @Override
    public void run() {
        try {

            String mensaje ="";
            String mensajeComp ="";

            //Iniciamos el bucle
            do {
                // Recibimos el paquete
                mensaje_bytes=new byte[MAX_BUFFER];
                paquete = new DatagramPacket(mensaje_bytes,MAX_BUFFER);
                socket.receive(paquete);

                // Lo formateamos
                mensaje_bytes=new byte[paquete.getLength()];
                mensaje_bytes=paquete.getData();
                mensaje = new String(mensaje_bytes,0,paquete.getLength()).trim();

                // Lo mostramos por pantalla
                System.out.println("Mensaje recibido \""+mensaje+"\" del cliente "+
                        paquete.getAddress()+"#"+paquete.getPort());

                // Agregamos el mensaje en la GUI del servidor.
                areaGUI.append("Mensaje recibido \""+mensaje+"\" del cliente "+
                        paquete.getAddress()+"#"+paquete.getPort()+"\n");

                //Obtenemos IP Y PUERTO
                puertoCliente = paquete.getPort();
                addressCliente = paquete.getAddress();

                if (mensaje.startsWith("hola")) {
                    mensajeComp="¿Cómo estas?";

                    //formateamos el mensaje de salida
                    enviaMensaje(mensajeComp);
                }
                else if (mensaje.startsWith("bien")) {
                    mensajeComp="También estoy bien, gracias";

                    //formateamos el mensaje de salida
                    enviaMensaje(mensajeComp);
                }
                else{
                    mensajeComp="...";
                }

            } while (true);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}

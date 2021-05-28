package proyectofinal.servidor.udp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WebcamServerUDP {
    public final int PUERTO = 50003;
    private JLabel label;

    public WebcamServerUDP(JLabel label) throws Exception{
        this.label = label;
        WebcamServidorEscuchaUDP servidor = new WebcamServidorEscuchaUDP();
        servidor.start();
    }

    class WebcamServidorEscuchaUDP extends Thread{
        protected DatagramSocket socket;

        public WebcamServidorEscuchaUDP() throws Exception {
            socket = new DatagramSocket(PUERTO);

        }

        @Override
        public void run() {
            try {
                while(true){
                    byte[] buffer = new byte[64000];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                    socket.receive(packet);
                    System.out.println("recibido");
                    byte[] buff = packet.getData();
                    ByteArrayInputStream bain = new ByteArrayInputStream(buff);
                    BufferedImage bufIma = ImageIO.read(bain);
                    ImageIcon icon = new ImageIcon(bufIma);
                    label.setIcon(icon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

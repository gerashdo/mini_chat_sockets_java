package proyectofinal.servidor.udp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WebcamServerUDP {
    public final int PUERTO = 50003;

    private JFrame frame;
    private JLabel label;

    public WebcamServerUDP(JLabel label) throws Exception{
        this.label = label;
//        frame = new JFrame("Servidor Camara");
//        frame.setSize(640, 480);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        label = new JLabel();
//        label.setSize(640, 480);
//        label.setVisible(true);
//
//        frame.add(label);
//        frame.setVisible(true);
        WebcamServidorEscuchaUDP servidor = new WebcamServidorEscuchaUDP(PUERTO, label);
        servidor.start();
    }


    class WebcamServidorEscuchaUDP extends Thread{
        protected DatagramSocket socket;
        protected int puerto;
        private JLabel label;

        public WebcamServidorEscuchaUDP(int puerto, JLabel label) throws Exception{
            this.puerto = puerto;
            this.label = label;
            socket = new DatagramSocket(puerto);

        }

        @Override
        public void run() {
            try {
                while(true){
                    byte[] buffer = new byte[64000];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    System.out.println("received");
                    byte[] buff = packet.getData();
//                    byte[] descomp = decompress(buff);
                    ByteArrayInputStream bain = new ByteArrayInputStream(buff);
                    BufferedImage bufIma = ImageIO.read(bain);
                    ImageIcon icon = new ImageIcon(bufIma);
                    label.setIcon(icon);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args)throws Exception {
//        new WebcamServerUDP();
//    }

}

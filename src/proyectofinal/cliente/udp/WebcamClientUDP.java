package proyectofinal.cliente.udp;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.zip.DeflaterOutputStream;

public class WebcamClientUDP {
    protected String IP;
    protected int PORT;

    protected Socket socket;
    protected ObjectInputStream ois;
    protected JFrame frame;
    protected JLabel label;
    protected Webcam camara;
    protected InetAddress address;

    public WebcamClientUDP(String IP, int puerto, JLabel label) {
        this.IP = IP;
        PORT = puerto;
        this.label = label;
    }

    public void inicia() throws Exception {
        DatagramSocket socket = new DatagramSocket();
        WebcamClienteEnviaUDP clienteEnvia = new WebcamClienteEnviaUDP(socket);
        address = InetAddress.getByName(IP);
        clienteEnvia.start();
    }

    class WebcamClienteEnviaUDP extends Thread{

        protected DatagramSocket socket;

        public WebcamClienteEnviaUDP(DatagramSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] imagenByte;
            camara = Webcam.getDefault();
            camara.setViewSize(new Dimension(640, 480));
            camara.open();

//            frame = new JFrame("Cliente camara");
//            frame.setSize(640, 480);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            label = new JLabel();
//            label.setSize(640, 480);
//            label.setVisible(true);
//
//            frame.add(label);
//            frame.setVisible(true);




            try {
                while (true) {
                    BufferedImage bufferImg = camara.getImage();
                    ImageIcon img= new ImageIcon(bufferImg);
                    label.setIcon(img);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(camara.getImage(), "jpg", baos);
//                    baos.flush();
//                    byte[] imagenByte1 = baos.toByteArray();
                    imagenByte = baos.toByteArray();
                    System.out.println(imagenByte.length);
                    DatagramPacket packet = new DatagramPacket(imagenByte, imagenByte.length, address, PORT);
                    socket.send(packet);

                }
            } catch (Exception e){
                System.out.println("Someone Disconnected");

            }
        }

        public byte[] compress(byte[] in) {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                DeflaterOutputStream defl = new DeflaterOutputStream(out);
                defl.write(in);
                defl.flush();
                defl.close();
                return out.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(150);
                return null;
            }
        }
    }

//    public static void main(String[] args) throws Exception{
//        new WebcamClientUDP("192.168.0.7", 50003).inicia();
//    }
}



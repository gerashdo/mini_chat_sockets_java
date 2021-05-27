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

public class WebcamClientUDP {
    protected String IP;
    protected int PORT;
    protected boolean enProceso = true;

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

    public void detener(){
        enProceso = false;
        camara.close();
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

            try {
                while (enProceso) {
                    BufferedImage bufferImg = camara.getImage();
                    ImageIcon img= new ImageIcon(bufferImg);
                    label.setIcon(img);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(camara.getImage(), "jpg", baos);
                    imagenByte = baos.toByteArray();
//                    System.out.println(imagenByte.length);
                    DatagramPacket packet = new DatagramPacket(imagenByte, imagenByte.length, address, PORT);
                    socket.send(packet);
                }
                label.setIcon(null);
            } catch (Exception e){
                System.out.println("Someone Disconnected");

            }
        }
    }
}



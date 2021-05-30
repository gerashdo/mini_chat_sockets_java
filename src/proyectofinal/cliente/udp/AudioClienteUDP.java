package proyectofinal.cliente.udp;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AudioClienteUDP {

    protected String IP;
    protected final int PUERTO = 50005;
    protected boolean enProceso = true;
    protected TargetDataLine microphone;

    public AudioClienteUDP(String IP){
        this.IP = IP;
    }

    public void iniciar(){
        AudioClienteEnviaUDP audio = new AudioClienteEnviaUDP();
        audio.start();
    }

    public void detener(){
        enProceso = false;
        microphone.stop();
        microphone.close();
    }

    class AudioClienteEnviaUDP extends Thread{

        @Override
        public void run() {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
            try {
                microphone = AudioSystem.getTargetDataLine(format);

                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                microphone = (TargetDataLine) AudioSystem.getLine(info);
                microphone.open(format);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int numBytesRead;
                int CHUNK_SIZE = 1024;
                byte[] data = new byte[microphone.getBufferSize() / 5];
                microphone.start();

                InetAddress address = InetAddress.getByName(IP);
                DatagramSocket socket = new DatagramSocket();
                while(enProceso) {
                    numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                    out.write(data, 0, numBytesRead);
                    DatagramPacket request = new DatagramPacket(data,numBytesRead, address, PUERTO);
                    socket.send(request);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package proyectofinal.cliente.udp;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AudioClienteUDP {

    protected String IP;
    protected final int PUERTO = 50005;

    public AudioClienteUDP(String IP){
        this.IP = IP;
    }

    public void iniciar(){
        AudioClienteEnviaUDP audio = new AudioClienteEnviaUDP();
        audio.start();
    }

//    public static void main(String[] args) throws IOException {
//        AudioClienteUDP audio = new AudioClienteUDP("192.168.0.7");
//        audio.iniciar();
//    }

    class AudioClienteEnviaUDP extends Thread{

        @Override
        public void run() {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
            TargetDataLine microphone;
            SourceDataLine speakers;
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


                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                speakers.open(format);
                speakers.start();


                // Configure the ip and port

                InetAddress address = InetAddress.getByName(IP);
                DatagramSocket socket = new DatagramSocket();
                byte[] buffer = new byte[1024];
                while(true) {
                    numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                    //  bytesRead += numBytesRead;
                    // write the mic data to a stream for use later
                    out.write(data, 0, numBytesRead);
                    // write mic data to stream for immediate playback
//                    speakers.write(data, 0, numBytesRead);
                    DatagramPacket request = new DatagramPacket(data,numBytesRead, address, PUERTO);
                    socket.send(request);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

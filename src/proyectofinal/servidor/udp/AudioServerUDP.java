package proyectofinal.servidor.udp;

import javax.sound.sampled.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AudioServerUDP {

    protected final int PUERTO = 50005;

    public void iniciar() throws Exception {
        AudioServerEscuchaUDP audio = new AudioServerEscuchaUDP();
        audio.start();
    }

    class AudioServerEscuchaUDP extends Thread{

        @Override
        public void run() {
            try {
                AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
                SourceDataLine speakers;

                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                speakers.open(format);
                speakers.start();

                DatagramSocket serverSocket = new DatagramSocket(PUERTO);

                while (true) {

                    byte[] buffer = new byte[1024];
                    DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                    serverSocket.receive(response);
                    speakers.write(response.getData(), 0, response.getData().length);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

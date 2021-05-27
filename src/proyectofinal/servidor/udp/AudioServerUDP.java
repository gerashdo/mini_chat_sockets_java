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

//    public static void main(String[] args) throws Exception{
//        AudioServerUDP audio = new AudioServerUDP();
//        audio.iniciar();
//    }

    class AudioServerEscuchaUDP extends Thread{

        @Override
        public void run() {
            try {
                AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
//                TargetDataLine microphone;
                SourceDataLine speakers;
//                microphone = AudioSystem.getTargetDataLine(format);

                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//                microphone = (TargetDataLine) AudioSystem.getLine(info);
//                microphone.open(format);

//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                byte[] data = new byte[microphone.getBufferSize() / 5];
//                microphone.start();
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                speakers.open(format);
                speakers.start();

                DatagramSocket serverSocket = new DatagramSocket(PUERTO);

                while (true) {

                    byte[] buffer = new byte[1024];
                    DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                    serverSocket.receive(response);

//                    out.write(response.getData(), 0, response.getData().length);
                    speakers.write(response.getData(), 0, response.getData().length);
                    String quote = new String(buffer, 0, response.getLength());

                    System.out.println(quote);
                    System.out.println();

                    //Thread.sleep(10000);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

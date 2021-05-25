package proyectofinal.test;

import java.io.*;
import java.net.Socket;

/**
 * @author ClientTCP
 * @date 2020/4/25 10:58 morning
 */
public class ClientTCP {
    public static void main(String[] args) throws IOException {
        File archivo = new File("p2.vob");
        //Create input stream
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(archivo.getName()));
        //Create Socket
        Socket client = new Socket("192.168.0.7", 50001);
        //Output stream
        BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());

        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        dos.writeUTF(archivo.getName());


        //Write data
        byte[] b = new byte[1024];
        int len;

        int filesize = Integer.parseInt(""+archivo.length()); // Send file size in separate msg
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        while((read = bis.read(b, 0, Math.min(b.length, remaining))) > 0) {
            totalRead += read;
            remaining -= read;
            System.out.println("read " + totalRead + " bytes.");
            System.out.println("remaining " + remaining + " bytes.");
            bos.write(b, 0, read);
        }



//        while ((len = bis.read(b)) != -1) {
//            bos.write(b, 0, len);
//            bos.flush();
//        }
//        System.out.println("File uploaded");

        //close resource
        bos.close();
        client.close();
        bis.close();
        System.out.println("File upload completed");
    }
}
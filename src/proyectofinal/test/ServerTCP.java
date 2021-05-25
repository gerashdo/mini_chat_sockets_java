package proyectofinal.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ServerTCP
 * @date 2020/4/25 10:51 morning
 */
public class ServerTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("Service started, waiting for connection");
        //Create ServerSocket object, bind port, start waiting for connection
        ServerSocket ss = new ServerSocket(50001);
        while(true) {

            //accept method, return socket object
            Socket server = ss.accept();
            //Get input object, read file
            BufferedInputStream bis = new BufferedInputStream(server.getInputStream());

            DataInputStream dis=new DataInputStream(server.getInputStream());
            String fileName = dis.readUTF();
            fileName = fileName.substring(fileName.indexOf('\\')+1,fileName.length());

            //Save to local
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("recibido_"+fileName));

            //Create byte array
            byte[] b = new byte[1024];
            //Read character array
            int len;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }

            //close resource
            bos.close();
            bis.close();
            server.close();
            System.out.println("Upload succeeded");
        }
    }
}

package proyectofinal.servidor.tcp;

import javax.swing.*;
import java.net.*;
//importar la libreria java.net
 
import java.io.*;
//importar la libreria java.io
// declaramos la clase servidortcp
 
public class ServidorEscuchaTCP extends Thread {
    // declaramos un objeto ServerSocket para realizar la comunicación
    protected ServerSocket socket;
    protected DataInputStream in;
    protected BufferedInputStream bis;
    protected Socket socket_cli;
    protected final int PUERTO_SERVER;
    protected BufferedOutputStream bos;
    private javax.swing.JTextArea mensajesTextArea;
    
    public ServidorEscuchaTCP(int puertoS)throws Exception{
        PUERTO_SERVER=puertoS;
        // Instanciamos un ServerSocket con la dirección del destino y el
        // puerto que vamos a utilizar para la comunicación
        socket = new ServerSocket(PUERTO_SERVER);
    }

    public void setMensajesTextArea(JTextArea mensajesTextArea) {
        this.mensajesTextArea = mensajesTextArea;
    }

    // método principal main de la clase
    public void run() {
        // Declaramos un bloque try y catch para controlar la ejecución del subprograma
        try {
            while (true){
                socket_cli = socket.accept();
                bis = new BufferedInputStream(socket_cli.getInputStream());
                in = new DataInputStream(socket_cli.getInputStream());
                String fileName = in.readUTF();
                fileName = fileName.substring(fileName.indexOf('\\')+1,fileName.length());

                bos = new BufferedOutputStream(new FileOutputStream("recibido_"+fileName));

                //Se crea el arreglo de bits
                byte[] b = new byte[1024];
                //Read character array
                int len;
                while ((len = bis.read(b)) != -1) {
                    bos.write(b, 0, len);
                }

                //close resource
                bos.close();
                bis.close();
                socket_cli.close();
                mensajesTextArea.append("Se recibió archivo llamado: " + fileName + "\n");
                System.out.println("Upload succeeded");
            }
        }
        // utilizamos el catch para capturar los errores que puedan surgir
        catch (Exception e) {

            // si existen errores los mostrará en la consola y después saldrá del
            // programa
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}

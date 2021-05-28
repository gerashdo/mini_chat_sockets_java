package proyectofinal.cliente.tcp;
import proyectofinal.test.Velocidad;

import java.net.*;
// importar la libreria java.net
import java.io.*;
// importar la libreria java.io
 
// declararamos la clase clientetcp
public class ClienteEnviaTCP extends Thread{
    protected BufferedReader in;
    // declaramos un objeto socket para realizar la comunicación
    protected Socket socket;
    protected final int PUERTO_SERVER;
    protected final String SERVER;
    protected DataOutputStream out;
    protected BufferedInputStream bis;
    BufferedOutputStream bos;
    protected File archivo;
    
    public ClienteEnviaTCP(String servidor, int puertoS) throws Exception{
        PUERTO_SERVER=puertoS;
        SERVER=servidor;

        // Creamos una instancia BuffererReader en la
        // que guardamos los datos introducido por el usuario
        in = new BufferedReader(new InputStreamReader(System.in));
        // Instanciamos un socket con la dirección del destino y el
        // puerto que vamos a utilizar para la comunicación
        socket = new Socket(SERVER,PUERTO_SERVER);

        //*
        // Declaramos un bufer output para enviar los bytes de los archivos.
        bos = new BufferedOutputStream(socket.getOutputStream());

        // Declaramos e instanciamos el objeto DataOutputStream
        // que nos valdrá para enviar datos al servidor destino
        out =new DataOutputStream(socket.getOutputStream());
    }
    
    public void run () {
        // declaramos una variable de tipo string
//        String mensaje="";


        // Declaramos un bloque try y catch para controlar la ejecución del subprograma
        try {
            out.writeUTF(archivo.getName());
            //Escritura de datos
            byte[] b = new byte[8192];
            int len;

            int filesize = Integer.parseInt(""+archivo.length()); // Envia archivos
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;

            ////////////////////
            Velocidad velocidad = new Velocidad(filesize*8);
            velocidad.iniciar();


            while((read = bis.read(b, 0, Math.min(b.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                ///////
                System.out.println("Tasa de transferencia: "+(velocidad.getTasaTransferencia(totalRead*8)/1000000));
                System.out.println("Tiempo Total: "+velocidad.getTiempoTotal());
                System.out.println("Tiempo Transcurrido: "+velocidad.getTiempoTranscurrido());
                System.out.println("Tiempo Restante: "+velocidad.getTiempoRestante(remaining*8));

                ///////
                System.out.println("read " + totalRead + " bytes.");
                System.out.println("remaining " + remaining + " bytes.");
                bos.write(b, 0, read);
            }

            bos.close();
            socket.close();
            bis.close();
            System.out.println("File upload completed");

        }
        // utilizamos el catch para capturar los errores que puedan surgir
        catch (Exception e) {
            // si existen errores los mostrará en la consola y después saldrá del
            // programa
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    public void setFile(File file) throws Exception{
        archivo = file;
        // Declaramos un Bufer de input para los archivos.
        bis = new BufferedInputStream(new FileInputStream(archivo));
    }
}

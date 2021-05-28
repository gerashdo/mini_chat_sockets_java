package proyectofinal.cliente.tcp;
import proyectofinal.velocidades.Velocidad;

import javax.swing.*;
import java.net.*;
import java.io.*;
 
// declararamos la clase clientetcp
public class ClienteEnviaTCP extends Thread{
    protected BufferedReader in;
    // declaramos un objeto socket para realizar la comunicación
    protected Socket socket;
    protected final int PUERTO_SERVER;
    protected final String SERVER;
    protected DataOutputStream out;
    protected BufferedInputStream bis;
    protected BufferedOutputStream bos;
    protected File archivo;
    protected final int tamBuffer = 8192;

    protected JLabel tasaLabel;
    protected JLabel totalLabel;
    protected JLabel restanteLabel;
    protected JLabel trasncurridoLabel;
    
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

        // Declaramos un bloque try y catch para controlar la ejecución del subprograma
        try {
            out.writeUTF(archivo.getName());
            //Escritura de datos
            byte[] b = new byte[tamBuffer];
            int len;

            long filesize = Long.parseLong(""+archivo.length()); // Envia archivos
            System.out.println(filesize);
            long read = 0;
            long totalRead = 0;
            long remaining = filesize;

            Velocidad velocidad = new Velocidad(filesize*8);
            velocidad.iniciar();

            while((read = bis.read(b, 0, menor(b.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                tasaLabel.setText(String.format("%.2f Mbps",(velocidad.getTasaTransferencia(totalRead*8)/1000000)));
                totalLabel.setText(String.format("%.1fs",velocidad.getTiempoTotal()));
                trasncurridoLabel.setText(String.format("%.0fs",velocidad.getTiempoTranscurrido()));
                restanteLabel.setText(String.format("%.0fs",velocidad.getTiempoRestante(remaining*8)));

                bos.write(b, 0, (int)read);
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
            e.printStackTrace();
            System.out.println("Es aqui");
            System.exit(1);
        }

    }

    public void setFile(File file) throws Exception{
        archivo = file;
        // Declaramos un Bufer de input para los archivos.
        bis = new BufferedInputStream(new FileInputStream(archivo));
    }

    public void setLabels(JLabel tasa, JLabel total, JLabel restante, JLabel transcurrido){
        totalLabel = total;
        tasaLabel = tasa;
        restanteLabel = restante;
        trasncurridoLabel= transcurrido;
    }

    public int menor(int num1, long num2){
        if(num1<num2){
            return num1;
        }else{
            return (int)num2;
        }
    }
}

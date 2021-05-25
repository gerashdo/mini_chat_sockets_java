package proyectofinal.cliente.tcp;

import java.io.File;

public  class ClienteTCP{
    protected final String SERVER;
    protected final int PUERTO_SERVER;
    protected ClienteEnviaTCP clienteTCP;
    
    public ClienteTCP(String servidor,int puertoS) throws Exception{
        SERVER=servidor;
        PUERTO_SERVER=puertoS;
        clienteTCP= new ClienteEnviaTCP(SERVER,PUERTO_SERVER);
    }
    public void inicia(){


        clienteTCP.start();
    }

    public void leerArchivo(File file) throws Exception{
        clienteTCP.setFile(file);
    }

    public void enviar(){
        clienteTCP.run();
    }
}

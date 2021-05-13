package proyectofinal.servidor.tcp;

public class ServidorTCP{
    protected final int PUERTO_SERVER;
    
    public ServidorTCP(int puertoS){
        PUERTO_SERVER=puertoS;
    }
    
    public void inicia()throws Exception{
        ServidorEscuchaTCP servidorTCP=new ServidorEscuchaTCP(PUERTO_SERVER);
        
        servidorTCP.start();
    }
}

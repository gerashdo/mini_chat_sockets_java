package proyectofinal.test;

public class PruebaVelocidad {
    public static void main(String[] args) throws Exception{
        Velocidad velocidad = new Velocidad(10000);
        int num = 0;
        velocidad.iniciar();
//        Thread.sleep(1000);
//        System.out.println(velocidad.getTiempoTranscurrido());
//        Thread.sleep(1000);
//        System.out.println(velocidad.getTiempoTranscurrido());
//        Thread.sleep(1000);
//        System.out.println(velocidad.getTiempoTranscurrido());
//        Thread.sleep(1000);
//        System.out.println(velocidad.getTiempoTranscurrido());
//        Thread.sleep(1000);
//        System.out.println(velocidad.getTiempoTranscurrido());
//        Thread.sleep(1000);
//        System.out.println(velocidad.getTiempoTranscurrido());


        while(num<=100000){
            num+=1;
//            Thread.sleep(1000);
            System.out.println("Tasa de transferencia: "+velocidad.getTasaTransferencia(num));
            System.out.println("Tiempo Total: "+velocidad.getTiempoTotal());
            System.out.println("Tiempo Transcurrido: "+velocidad.getTiempoTranscurrido());
            System.out.println("Tiempo Restante: "+velocidad.getTiempoRestante(num));
        }
        velocidad.detener();
    }
}

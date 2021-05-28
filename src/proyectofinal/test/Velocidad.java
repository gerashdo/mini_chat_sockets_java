package proyectofinal.test;



public class Velocidad {
    protected long tamanioTotal;
    protected StopWatch cronometro;
    protected double tasaTransferencia;

    public Velocidad(long tamanioTotal){
        this.tamanioTotal = tamanioTotal;
        cronometro = new StopWatch();
    }

    public void iniciar(){
        cronometro.start();
    }

    public double getTasaTransferencia(long transferidos){
        tasaTransferencia = transferidos/getTiempoTranscurrido();
        return tasaTransferencia;
    }

    public double getTiempoTranscurrido(){
        return cronometro.getElapsedTimeSecs();
    }

    public double getTiempoTotal(){
        return tamanioTotal/tasaTransferencia;
    }

    public double getTiempoRestante(long restante){
        return restante/tasaTransferencia;
    }

    public void detener(){
        cronometro.parar();
    }

//    public static void main(String[] args) throws Exception{
//        StopWatch crono = new StopWatch();
//        int num = 0;
//        crono.start();
//        System.out.println(crono.getElapsedTimeSecs());
//        Thread.sleep(1000);
//        System.out.println(crono.getElapsedTimeSecs());
//        Thread.sleep(1000);
//        System.out.println(crono.getElapsedTimeSecs());
//        Thread.sleep(1000);
//        System.out.println(crono.getElapsedTimeSecs());
//        Thread.sleep(1000);
//        System.out.println(crono.getElapsedTimeSecs());
//
//        crono.parar();
//        System.out.println("Segundos "+crono.getElapsedTimeSecs());
//        System.out.println("Milisegundos "+crono.getElapsedTime());
//    }
}

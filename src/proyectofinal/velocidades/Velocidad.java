package proyectofinal.velocidades;

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

}

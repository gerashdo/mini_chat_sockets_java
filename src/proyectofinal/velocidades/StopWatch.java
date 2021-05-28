package proyectofinal.velocidades;

public class StopWatch extends Thread{

    private long startTime;
    private long stopTime;

    public StopWatch() {
        startTime = 0;
        stopTime = 0;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
    }

    public void parar() {
        stopTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return stopTime - startTime;
    }

    public double getElapsedTimeSecs() {
        double elapsed;
        elapsed = ((double)(System.currentTimeMillis() - startTime)) / 1000;
        return elapsed;
    }
}

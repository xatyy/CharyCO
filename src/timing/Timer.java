package timing;

public class Timer implements ITimer {

    private long timerStart;
    private long timerStop;
    private long totalTime;
    private long elapsedTime;

    @Override
    public void start() {
        timerStart = System.nanoTime();
        totalTime = 0;
    }

    @Override
    public long stop() {
        timerStop = System.nanoTime();
        elapsedTime = timerStop - timerStart;
        totalTime = totalTime + (timerStop - timerStart);
        return totalTime;
    }

    @Override
    public void resume() {
        timerStart = System.nanoTime();
    }

    @Override
    public long pause() {
        timerStop = System.nanoTime();
        elapsedTime = timerStop - timerStart;
        totalTime = totalTime + (timerStop - timerStart);
        return elapsedTime;
    }
}


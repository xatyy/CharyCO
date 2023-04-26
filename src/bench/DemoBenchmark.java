package bench;

import java.util.Random;

public class DemoBenchmark implements IBenchmark{
    public Integer n;
    private int[] array;

    public boolean running;

    @Override
    public void run() {
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(array[j] > array[j + 1 ]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        try{
            Thread.sleep(n);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(Object... params) {

    }

    @Override
    public void initialize(Object... params) {
        Random random = new Random();

        this.n = (Integer) params [0];
        this.array = new int[n];

        for(int i = 0; i < n; i++){
            array[i] = random.nextInt(1000);
        }
        running = true;

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {
        running = false;
    }

    @Override
    public void warmup() {


    }

    @Override
    public String getResult() {
        return null;
    }
}

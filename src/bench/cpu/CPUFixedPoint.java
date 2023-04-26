package bench.cpu;

import bench.IBenchmark;

public class CPUFixedPoint implements IBenchmark {
    private int[] Array;
    private int workload = 1000000000;


    @Override
    public void run() {
        int j = 0;
        for(int i = 0; i < workload; i++) {
            if (j == 1) {
                j = Array[2];
            } else {
                j = Array[3];
            }
            if (j > 2) {
                j = Array[0];
            } else {
                j = Array[1];
            }
            if (j < 1) {
                j = Array[1];
            } else {
                j = Array[0];
            }
        }
    }

    @Override
    public void run(Object... params) {
        int option = (int) params[0];
        switch (option){
            case 0:
                integerArithmeticTest();
                break;
            case 1:
                branchTest();
                break;
            case 2:
                arrayTest();
                break;
            default:
                throw new IllegalArgumentException("Unknown arguments.");
        }

    }

    private void arrayTest() {

    }

    private void branchTest() {

    }

    private void integerArithmeticTest() {

    }

    @Override
    public void initialize(Object... params) {

        this.Array = new int[params.length];
        for(int i = 0; i < params.length; i++){
            Array[i] = (int) params[i];
        }

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup() {

    }

    @Override
    public String getResult() {
        long value = 34 * (long)workload;
       return String.valueOf(value);
    }
}

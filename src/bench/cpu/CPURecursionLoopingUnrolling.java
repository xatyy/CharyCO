package bench.cpu;

import bench.IBenchmark;


public class CPURecursionLoopingUnrolling implements IBenchmark {

    private long workload;
    private String finalSum;

    @Deprecated
    public void run() {
    }

    @Override
    public void run(Object... params) {
        boolean rolling = (boolean) params[0];

        if(rolling){
            int unrollFactor = (int) params[1];
                long result = recursiveUnrolled(0, unrollFactor, workload, 0);
                this.finalSum = String.valueOf(result);
        }else {
            long result = recursive(2, workload, 0);
            this.finalSum = String.valueOf(result);
        }

    }

    private long recursive(long start, long size, int counter){

        long prime = getPrime(start);




        if (prime >= size)
            return 0;


        try{
            return prime + recursive(prime + 1, size, counter + 1);
        }catch (StackOverflowError e){
            StringBuilder sb = new StringBuilder()
                    .append("Reached nr ")
                    .append(prime)
                    .append("/")
                    .append(size)
                    .append(" after ")
                    .append(counter)
                    .append(" calls.");

            System.err.println(sb.toString());

            return 0;
        }
    }

    private long recursiveUnrolled(long start, int unrollFactor, long size, int counter){

        long prime = 0;
        long sum = 0;

            for(long i = start; i < size; i = i + unrollFactor){
                prime = getPrime(start + i);
               if(prime >= size){
                   return 0;
               }

               sum = sum + prime;
            }
            try{
            return sum + recursiveUnrolled(prime + 1, unrollFactor, size, counter + 1);
        }catch (StackOverflowError e){
            StringBuilder sb = new StringBuilder()
                    .append("Reached nr ")
                    .append(prime)
                    .append("/")
                    .append(size)
                    .append(" after ")
                    .append(counter)
                    .append(" calls.");

            System.err.println(sb.toString());

            return 0;
        }
    }

    long getPrime(long x){
        if(x <= 2){
            return x;
        }
        for(int i = 2; i <= Math.sqrt(x); i++){
            if( x % i == 0){
                return getPrime(x + 1);
            }
        }
        return x;
    }

    @Override
    public void initialize(Object... params) {
        this.workload = (Long)params[0];

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
        return finalSum;
    }
}

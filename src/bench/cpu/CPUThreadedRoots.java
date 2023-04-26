package bench.cpu;

import bench.IBenchmark;

public class CPUThreadedRoots implements IBenchmark , Runnable{

    private double result;
    private int size;
    private boolean running;

    @Override
    public void initialize(Object... params) {
       this.size = (int) params[0];
    }


    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... options) {
        // options[0] -> number of threads
        // ...

        int nThreads = (int)options[0];

        Thread[] threads = new Thread[nThreads];

        // e.g. 1 to 10,000 on 4 threads = 2500 jobs per thread
        final int jobPerThread = size / nThreads; /**/

        running = true; // flag used to stop all started threads
        // create a thread for each runnable (SquareRootTask) and start it
        for (int i = 0; i < nThreads; ++i) {

            threads[i] = new Thread(new SquareRootTask(i*jobPerThread + 1, (i+1)*jobPerThread));
            threads[i].start();


        }

        for (int i = 0; i < nThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void clean() {
        // only implement if needed
    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup() {
        // call run method: call run() once
        // detect number of cores: Runtime.....availableProcessors();
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    public synchronized void addResult(double result){
        this.result += result;
    }



    class SquareRootTask implements Runnable {

        private int from, to;
        private final double precision = 1e-4; // fixed
        private double result = 0.0;

        public SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            for(int i = from; i < to ; ++i){



                if(running){
                    result = result + getNewtonian(i);
                }



            }
            CPUThreadedRoots.this.addResult(result);
            // compute Newtonian square root on each number from i = 'from' to 'to', and also check 'running'
            // save (+=) each computed square root in the local 'result' variable
            // extra: send 'result' back to main thread and sum up with all results
        }

        private double getNewtonian(double x) {

            double s = x;

            while(Math.abs(s*s - x) > precision && running){
                s = (x / s + s) / 2;
            }



            return s;
        }

        // extra: compute sum, pass it back to wrapper class. Use synchronized
    }

}
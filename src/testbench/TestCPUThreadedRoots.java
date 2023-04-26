package testbench;

import bench.IBenchmark;
import bench.cpu.CPUThreadedRoots;
import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;



public class TestCPUThreadedRoots {

    public static void main(String[] args){
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();

        TimeUnit timeUnit = TimeUnit.Sec;

        IBenchmark bench = new CPUThreadedRoots();
        int workload = 100000000;
        bench.initialize(workload);
        bench.warmup();

        long totalTime = 0;

        for(int i = 1; i <= 64; i *= 2){
            timer.start();
            bench.run(i);
            long time = timer.stop();
            log.writeTime("[t="+i+"] finished in", time, timeUnit);
            totalTime += time;
            double score = (double) workload * i/( time * 10E-5);

            log.write(score);
            System.out.println();
        }

        log.writeTime("Total finished in ", totalTime, timeUnit);
        log.write("Result:", bench.getResult());
        bench.clean();
        log.close();
    }

}

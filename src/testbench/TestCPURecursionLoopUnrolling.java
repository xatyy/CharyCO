package testbench;

import bench.IBenchmark;
import bench.cpu.CPURecursionLoopingUnrolling;
import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;

public class TestCPURecursionLoopUnrolling {
    public static void main(String [] args){
        ILogger log = new ConsoleLogger();
        IBenchmark bench = new CPURecursionLoopingUnrolling();
        ITimer timer = new Timer();

        long workload = 1000000;

        bench.initialize(workload);

        timer.start();
        bench.run(true, 4);
        long time = timer.stop();

        double res = 1000000*(Double.parseDouble(bench.getResult()) / time / workload);

        System.out.println(res);

        log.writeTime("Finished in", time, TimeUnit.Milli);
    }
}

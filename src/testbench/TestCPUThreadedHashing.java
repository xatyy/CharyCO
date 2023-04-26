package testbench;

import bench.IBenchmark;
import bench.cpu.CPUThreadedHashing;
import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;

public class TestCPUThreadedHashing {
    public static void main(String [] args){
        IBenchmark bench = new CPUThreadedHashing();
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.Sec;
        int maxLength = 10;
        int nThreads = 8;
        int hashCode = 163849898;
        timer.start();
        bench.run(maxLength, nThreads, hashCode);
        long time = timer.stop();
        log.writeTime("Finished in", time, timeUnit);
        log.write("Result is", bench.getResult());
    }
}

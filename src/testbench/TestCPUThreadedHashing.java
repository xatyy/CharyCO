package testbench;

import bench.IBenchmark;
import bench.cpu.CPUThreadedHashing;
import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;

import java.io.IOException;

public class TestCPUThreadedHashing {
    public static void main(String [] args) throws IOException {
        IBenchmark bench = new CPUThreadedHashing();
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.Sec;
        int maxLength = 10;
        int nThreads = 8;
        int hashCode = 132368363;
        //int hashCode = 46053341;
        timer.start();
        ((CPUThreadedHashing) bench).run_file(maxLength, nThreads, hashCode);
        //bench.run(maxLength, nThreads, hashCode);
        long time = timer.stop();
        log.writeTime("Finished in", time, timeUnit);
        log.write("Result is", bench.getResult());
    }
}

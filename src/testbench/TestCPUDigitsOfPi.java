package testbench;

import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.Timer;

import static logging.TimeUnit.Sec;

public class TestCPUDigitsOfPi {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger logger = new ConsoleLogger();
        IBenchmark bench = new bench.cpu.CPUDigitsOfPi();

        bench.warmup();
        timer.start();
        bench.run(10000);
        long time = timer.stop();

        logger.writeTime("Finished in \n", time, Sec);

        logger.close();
    }
}

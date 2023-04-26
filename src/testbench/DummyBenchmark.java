package testbench;

import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import timing.ITimer;
import timing.Timer;

public class DummyBenchmark {

    public static void main(String[] args){
        ITimer timer = new Timer();
        ILogger logger =  new FileLogger("out.txt"); // new ConsoleLogger();
        IBenchmark bench = new bench.DummyBenchmark();

        bench.initialize(10000);
        timer.start();
        bench.run();
        logger.write("Finished in ", timer.stop(), "s");

        logger.close();
    }

}

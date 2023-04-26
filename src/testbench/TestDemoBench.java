package testbench;

import bench.DemoBenchmark;
import bench.IBenchmark;
import logging.ConsoleLogger;
import timing.ITimer;
import timing.Timer;
import logging.ILogger;

import static logging.TimeUnit.Sec;

public class TestDemoBench {

    public static void main(String[] args) {

        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        IBenchmark bench = new DemoBenchmark();

        int N = 1000;

        bench.initialize(N);
        timer.start();
        bench.run();
        bench.cancel();

        long time = timer.stop();
        log.writeTime("Finished in ", time, Sec);

        System.out.println("Measured time in nano: " + time);

        double expectedTime = N * Math.pow(10, 6);
        System.out.println("Approximated time in nano" + expectedTime);

        double offset = ((time - expectedTime)/expectedTime)*100;

        log.write("Offset is:", offset, "%\n");
        log.close();
        bench.clean();

        bench.initialize(N);
        for(int i = 0; i < 12; ++i){
            timer.resume();
            bench.run();
            long pauseTime = timer.pause();
            log.write("Run " + i + ": ");
            log.writeTime(pauseTime, Sec);
        }
        log.writeTime("Finished in: ", timer.stop(), Sec);
        log.close();
        bench.clean();
    }
}

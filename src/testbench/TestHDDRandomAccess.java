package testbench;

import bench.IBenchmark;
import bench.hdd.HDDRandomAccess;
import logging.ConsoleLogger;
import logging.ILogger;

public class TestHDDRandomAccess {

    public static void main(String [] args){
        IBenchmark bench = new HDDRandomAccess();
        ILogger log = new ConsoleLogger();

        bench.initialize((long)1024 * 1024 * 1024 * 3 );
        bench.run("r", "ft", 1024 * 1024);
        log.write(bench.getResult());
    }



}

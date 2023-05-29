package testbench;

import bench.IBenchmark;
import bench.ram.VirtualMemoryBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;

public class TestVirtualMemoryBenchmark {
    public static void main(String [] args){
        IBenchmark bench = new VirtualMemoryBenchmark();
        ILogger log = new ConsoleLogger();

        long fileSize = 512L * 1024 * 1024;
        int bufferSize = 4 * 1024;

        bench.run(fileSize, bufferSize);
        bench.clean();
        log.write(bench.getResult());
    }
}

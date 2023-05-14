package testbench;

import bench.IBenchmark;
import bench.hdd.HDDWriteSpeed;

public class TestHDDWriteSpeed {
    public static void main(String[] args){
        IBenchmark bench = new HDDWriteSpeed();
        bench.run("fs", true);
    }
}

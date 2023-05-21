package testbench;

import bench.IBenchmark;
import bench.hdd.HDDWriteSpeed;

public class TestHDDWriteSpeed {
    public static void main(String[] args){
        IBenchmark bench = new HDDWriteSpeed();
        for(int i = 0; i < 5; i++){
            bench.run("fb", true);
        }

    }
}

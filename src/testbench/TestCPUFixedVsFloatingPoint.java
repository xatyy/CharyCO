package testbench;

import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;
import bench.IBenchmark;
import bench.cpu.CPUFixedPoint;
import bench.cpu.CPUFixedVsFloatingPoint;
//import bench.cpu.CPUNumberRepresentation;
import bench.cpu.NumberRepresentation;

public class TestCPUFixedVsFloatingPoint {

	public static void main(String[] args) {
		ITimer timer = new Timer();
		ILogger log = /* new FileLogger("bench.log"); */new ConsoleLogger();
		TimeUnit timeUnit = TimeUnit.Sec;

		IBenchmark bench = new CPUFixedVsFloatingPoint();
		IBenchmark bench2 = new CPUFixedPoint();

		bench2.initialize(0, 1, 2, 3);
	//	bench.initialize(10000000);
		//	bench.warmup();

		timer.start();
//		bench.run(NumberRepresentation.FIXED);
//		bench.run(NumberRepresentation.FLOATING);
		bench2.run();
		long time = timer.stop();
		log.writeTime("Finished in", time, timeUnit);
		log.write("MOPS is", Long.valueOf(bench2.getResult() ) / TimeUnit.toTimeUnit(time,timeUnit) / 1e6);

		bench.clean();
		log.close();
	}
}

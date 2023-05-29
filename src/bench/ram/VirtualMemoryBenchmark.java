package bench.ram;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import timing.Timer;
import bench.IBenchmark;

/**
 * Maps a large file into RAM triggering the virtual memory mechanism. Performs
 * reads and writes to the respective file.<br>
 * The access speeds depend on the file size: if the file can fit the available
 * RAM, then we are measuring RAM speeds.<br>
 * Conversely, we are measuring the access speed of virtual memory, implying a
 * mixture of RAM and HDD access speeds (i.e., lower speeds).
 */
public class VirtualMemoryBenchmark implements IBenchmark {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	MemoryMapper core = null;
	private String result = "";

	@Override
	public void initialize(Object... params) {
		/* not today */
	}

	@Override
	public void warmup() {
		/* summer is coming anyway */
	}

	@Override
	public void run() {
		throw new UnsupportedOperationException("Use run(Object[]) instead");
	}

	@Override
	public void run(Object... options) {

		// expected: {fileSize, bufferSize}	
		Object[] params = (Object[]) options;
		long fileSize = Long.parseLong(params[0].toString()); // e.g. 2-16GB
		int bufferSize = Integer.parseInt(params[1].toString()); // e.g. 4+KB

		try {
			core = new MemoryMapper("/Users/chary/000_core", fileSize); // change path as needed
			byte[] buffer = new byte[bufferSize];
			Random rand = new Random();

			Timer timer = new Timer();

			// write to VM
			timer.start();
			for (long i = 0; i < fileSize; i += bufferSize) {
				rand.nextBytes(buffer);
				core.put(i, buffer);
				// 1. generate random content (see assignments 9,11)
				// 2. write to memory mapper
			}
			double time = (double) timer.stop() / 1000000000.0;
			double speed = (fileSize / 1024 / 1024L) / time; /* 3. fileSize/time [MB/s] */

			result = "\nWrote " + (fileSize / 1024 / 1024L)
					+ " MB to virtual memory at " + df.format(speed) + " MB/s";

			// read from VM
			timer.start();
			for (long i = 0; i < fileSize; i += bufferSize) {
				// 5. get from memory mapper
				core.get(i, bufferSize);
			}

			time = timer.stop() / 1000000000.0;
			speed = (fileSize / 1024 / 1024L) / time; /* 6. MB/s */

			// append to previous 'result' string
			result += "\nRead " + (fileSize / 1024 / 1024L)
					+ " MB from virtual memory at " + df.format(speed) + " MB/s";
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (core != null)
				core.purge();
		}
	}

	@Override
	public void clean() {
		if (core != null)
			core.purge();
	}

	@Override
	public void cancel() {

	}

	@Override
	public String getResult() {
		return result;
	}

}

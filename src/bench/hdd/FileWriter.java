package bench.hdd;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import logging.TimeUnit;

import timing.Timer;

public class FileWriter {

	private static final int MIN_BUFFER_SIZE = 1024 * 1; // KB
	private static final int MAX_BUFFER_SIZE = 1024 * 1024 * 64; // MB
	private static final long MIN_FILE_SIZE = 1024 * 1024 * 1; // MB
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 1024; // MB
	private Timer timer = new Timer();
	private Random rand = new Random();
	private double benchScore;

	/**
	 * Writes files on disk using a variable write buffer and fixed file size.
	 * 
	 * @param filePrefix
	 *            - Path and file name
	 * @param fileSuffix
	 *            - file extension
	 * @param minIndex
	 *            - start buffer size index
	 * @param maxIndex
	 *            - end buffer size index
	 * @param fileSize
	 *            - size of the benchmark file to be written in the disk
	 * @throws IOException
	 */
	public void streamWriteFixedFileSize(String filePrefix, String fileSuffix,
			int minIndex, int maxIndex, long fileSize, boolean clean)
			throws IOException {

		System.out.println("Stream write benchmark with fixed file size");
		int currentBufferSize = MIN_BUFFER_SIZE;
		String fileName;
		int fileIndex = 0;
		benchScore = 0;

		while (currentBufferSize <= MAX_BUFFER_SIZE
				&& fileIndex <= maxIndex - minIndex) {
			fileName = filePrefix + fileIndex + fileSuffix;
			writeFile(fileName, currentBufferSize, fileSize, clean);
			// update buffer size
			currentBufferSize *= 4;
			fileIndex++;
		}

		benchScore /= (maxIndex - minIndex + 1);
		//String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
		System.out.println("File write score on partition " + ": "
				+ String.format("%.2f", benchScore) + " MB/sec");
	}

	/**
	 * Writes files on disk using a variable file size and fixed buffer size.
	 * 
	 * @param filePrefix
	 *            - Path and file name
	 * @param fileSuffix
	 *            - file extension
	 * @param minIndex
	 *            - start file size index
	 * @param maxIndex
	 *            - end file size index
	 * @param fileSize
	 *            - size of the benchmark file to be written in the disk
	 */
	public void streamWriteFixedBufferSize(String filePrefix, String fileSuffix,
			int minIndex, int maxIndex, int bufferSize, boolean clean)
			throws IOException {

		System.out.println("Stream write benchmark with fixed buffer size");
		long currentFileSize = MIN_FILE_SIZE;
		int fileIndex = 0;
		
		while (currentFileSize <= MAX_FILE_SIZE
				&& fileIndex <= maxIndex - minIndex) {
			String fileName;
			fileName = filePrefix + fileIndex + fileSuffix;
			writeFile(fileName, bufferSize, currentFileSize, clean);
			currentFileSize *= 10;
			fileIndex++;
		}

		benchScore /= (maxIndex - minIndex + 1);
		//String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
		System.out.println("File write score on partition " + ": "
				+ String.format("%.2f", benchScore) + " MB/sec");
	}

	/**
	 * Writes a file with random binary content on the disk, using a given file
	 * path and buffer size.
	 */
	private void writeFile(String fileName, int bufferSize,
			long fileSize, boolean clean) throws IOException {

		File folderPath = new File(fileName.substring(0,
				fileName.lastIndexOf(File.separator)));

		// create folder path to benchmark output
		if (!folderPath.isDirectory())
			folderPath.mkdirs();

		final File file = new File(fileName);
		// create stream writer with given buffer size
		final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file), bufferSize); //<----

		byte[] buffer = new byte[bufferSize]; // <----------- [?]
		int i = 0;
		long toWrite = fileSize / bufferSize;

		timer.start();
		while (i < toWrite) {
			// generate random content to write
			rand.nextBytes(buffer);

			outputStream.write(buffer);
			i++;
		}		
		printStats(fileName, fileSize, bufferSize);

		outputStream.close();
		if(clean)
			file.deleteOnExit();
	}

	private void printStats(String fileName, long totalBytes, int bufferSize) {
		final long time = timer.stop();
		
		NumberFormat nf = new DecimalFormat("#.00");
		double seconds = TimeUnit.toTimeUnit(time, TimeUnit.Sec); // calculated from timer's 'time'
		double megabytes = totalBytes / (1024*1024); //
		double rate = megabytes/seconds; // calculated from the previous two variables
		System.out.println("Done writing " + totalBytes + " bytes to file: "
				+ fileName + " in " + nf.format(seconds) + " s ("
				+ nf.format(rate) + "MB/sec)" + " with a buffer size of "
				+ bufferSize / 1024 + " kB");

		// actual score is write speed (MB/s)
		benchScore += rate;
	}
}
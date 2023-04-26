package logging;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger {

    private String filename;
    private FileWriter writer;

    /**
     * Constructor to create file for Logger
     * */
    public FileLogger(String name){


        this.filename = name;
        File file = new File(filename);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(long param) {
        try {
            writer.write(Long.toString(param));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String param) {
        try {
            writer.write(param);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(Object... values) {
        try {
            for (Object o:values){
                writer.write(o.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTime(long value, TimeUnit unit) {
        try{
            writer.write((int) TimeUnit.toTimeUnit(value, unit));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void writeTime(String string, long value, TimeUnit unit) {
        try{
            writer.write(string + " " + TimeUnit.toTimeUnit(value, unit) + " " + unit);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

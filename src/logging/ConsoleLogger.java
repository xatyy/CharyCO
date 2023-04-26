package logging;

public class ConsoleLogger implements ILogger {

    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public void write(long value) {
        System.out.println(value);
    }

    @Override
    public void write(Object... values) {
        for (Object o : values)
            System.out.print(o.toString() + " ");
        System.out.println();
    }
    @Override
    public void writeTime(long value, TimeUnit unit) {
        System.out.println(TimeUnit.toTimeUnit(value, unit));
    }

    @Override
    public void writeTime(String string, long value, TimeUnit unit) {
        System.out.println(string + " " + TimeUnit.toTimeUnit(value, unit));
    }

    @Override
    public void close() {
    //EMPTY
    }
}

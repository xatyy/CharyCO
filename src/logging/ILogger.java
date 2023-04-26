package logging;

public interface ILogger {
    /** Method to write in Logger using long params */
     void write (long param);

    /** Method to write in Logger using String param */
     void write (String param);

    /** Method to write in Logger using multiple objects */
     void write (Object ...values);

    public void writeTime(long value, TimeUnit unit);

    public void writeTime(String string, long value, TimeUnit unit);

    /** Method to close Logger */
     void close();
}

package timing;

public interface ITimer {

    /** save elapsed time in long variable and resets any previously stored total time */
     void start();

    /** return elapsed time since the start of the timer*/
     long stop();

    /** save current elapsed time in a variable without resetting */
     void resume();

    /** return elapsed time */
     long pause();
}

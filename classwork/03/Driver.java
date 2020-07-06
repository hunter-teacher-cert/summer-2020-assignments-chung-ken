/**
 * Driver class for Time
 * Thursday, July 2, 2020
 */

import java.io.*;
import java.util.*;

public class Driver{
    public static void main(String[] args) {
	       Time t1 = new Time();
           Time t2 = new Time(11, 59, 59.9);

           // Math.PI;
           // Math.pow(2,3);

           System.out.println(t2);
           t2.setHour(5);
           Time.printTime(t2); //static methods
           t2.printTime(); //non-static method

           //Movie times
           Time startTime = new Time(18, 50, 0.0);
           Time runningTime = new Time(2, 16, 0.0);
           Time endTime = startTime.add(runningTime);
           System.out.println(endTime);
           endTime.increment(300);
    }
}

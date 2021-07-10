/**
 * Thursday, July 2, 2020
 * ThinkJava Chapters 11
 * Practice with Classes & Objects
 * Chapter link: https://books.trinket.io/thinkjava/chapter11.html
 */

import java.io.*;
import java.util.*;


public class Time{
    //instance variables
    private int hour;
    private int minute;
    private double second;

    //constructor
    public Time() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0.0;
    }

    //overloaded constructor
    public Time(int hour, int minute, double second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //getters
    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public double getSecond() {
        return this.second;
    }

    //setters
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    //functional methods
    public void printTime() {
        System.out.printf("%02d:%02d:%04.1f\n",
            this.hour, this.minute, this.second);
    }
    public static void printTime(Time t) {
        System.out.printf("%02d:%02d:%04.1f\n",
            t.hour, t.minute, t.second);
    }

    //override toString method to more than type and address of object
    public String toString() {
        return String.format("%02d:%02d:%04.1f\n",
            this.hour, this.minute, this.second);
    }

    // add is a "pure" method because it doesn't modify anything
    public Time add(Time that) {
        Time sum = new Time();
        sum.hour = this.hour + that.hour;
        sum.minute = this.minute + that.minute;
        sum.second = this.second + that.second;

        if (sum.second >= 60.0) {
            sum.second -= 60.0;
            sum.minute += 1;
        }
        if (sum.minute >= 60) {
            sum.minute -= 60;
            sum.hour += 1;
        }
        if (sum.hour >= 24) {
            sum.hour -= 24;
        }

        return sum;
    }

    // increment is a "modifier"
    public void increment(double seconds) {
        this.second += seconds;
        this.minute += this.second/60;
        this.second %= 60;
        this.hour += this.minute/60;
        this.minute %= 60;
        /*
        while (this.second >= 60.0) {
            this.second -= 60.0;
            this.minute += 1;
            while (this.minute >= 60) {
                this.minute -= 60;
                this.hour += 1;
            }
        }
        */
    }

    //override equals method to more than reference equality
    public boolean equals(Time that) {
        return this.hour == that.hour
            && this.minute == that.minute
            && this.second == that.second;
    }



}

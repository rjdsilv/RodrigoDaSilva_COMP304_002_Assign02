package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

import java.time.LocalTime;

public class MovieData {
    private static final MovieData INSTANCE = new MovieData();

    private String name;
    private LocalTime time;

    private MovieData() {
    }

    public static MovieData getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

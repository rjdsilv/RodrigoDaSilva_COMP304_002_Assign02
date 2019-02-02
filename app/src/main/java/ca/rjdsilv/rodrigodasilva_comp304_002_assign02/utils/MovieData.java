package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

import java.util.Locale;

/**
 * Singleton class that will store all the data selected by the user in order to show all the
 * information to the user in the end.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MovieData {
    private static final MovieData INSTANCE = new MovieData();

    private String firstName = StringUtils.EMPTY;
    private String lastName = StringUtils.EMPTY;
    private String movieName = StringUtils.EMPTY;
    private String time = StringUtils.EMPTY;
    private int adultQuantity = 0;
    private int childrenQuantity = 0;

    private MovieData() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format(Locale.CANADA, "%s %s", firstName, lastName);
    }

    public static MovieData getInstance() {
        return INSTANCE;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAdultQuantity() {
        return adultQuantity;
    }

    public void setAdultQuantity(int adultQuantity) {
        this.adultQuantity = adultQuantity;
    }

    public int getChildrenQuantity() {
        return childrenQuantity;
    }

    public void setChildrenQuantity(int childrenQuantity) {
        this.childrenQuantity = childrenQuantity;
    }

    public float total() {
        return adultQuantity * TicketUtils.ticketPrice(false) + childrenQuantity * TicketUtils.ticketPrice(true);
    }

    public boolean hasTicketSelected() {
        return adultQuantity > 0 || childrenQuantity > 0;
    }
}

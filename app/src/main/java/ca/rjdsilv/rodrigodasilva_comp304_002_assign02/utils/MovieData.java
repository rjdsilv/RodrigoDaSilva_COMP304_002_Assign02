package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

/**
 * Singleton class that will store all the data selected by the user in order to show all the
 * information to the user in the end.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MovieData {
    private static final MovieData INSTANCE = new MovieData();

    private String name;
    private String time;
    private int adultQuantity;
    private int childrenQuantity;

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

    public boolean hasTicketSelected() {
        return adultQuantity > 0 || childrenQuantity > 0;
    }
}

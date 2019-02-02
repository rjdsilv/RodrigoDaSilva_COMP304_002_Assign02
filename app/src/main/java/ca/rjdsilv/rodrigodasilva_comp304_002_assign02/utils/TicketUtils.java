package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

import java.util.Locale;

/**
 * Utility class to deal with tickets in the application.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class TicketUtils {
    /**
     * Retrieves the ticke price depending on the type.
     *
     * @param isChild Indicates if it's a child ticket.
     * @return The ticket price
     */
    public static float ticketPrice(boolean isChild) {
        if (isChild) {
            return 13.50f;
        }

        return 19.00f;
    }

    /**
     * Retrieves the data from the ticket information.
     *
     * @param data The data to have the data retrieved from.
     * @return The string containing the data from the tickets quantity.
     */
    public static String ticketsData(MovieData data) {
        String ticketsText = StringUtils.EMPTY;
        if (data.getAdultQuantity() > 0) {
            final String adultText = data.getAdultQuantity() == 1 ? "adult" : "adults";
            ticketsText += String.format(Locale.CANADA, "%d %s", data.getAdultQuantity(), adultText);
        }
        if (data.getChildrenQuantity() > 0) {
            final String childText = data.getChildrenQuantity() == 1 ? "child" : "children";
            ticketsText += ticketsText.length() > 0
                    ? String.format(Locale.CANADA, ", %d %s", data.getChildrenQuantity(), childText)
                    : String.format(Locale.CANADA, "%d %s", data.getChildrenQuantity(), childText);
        }
        return ticketsText;
    }
}

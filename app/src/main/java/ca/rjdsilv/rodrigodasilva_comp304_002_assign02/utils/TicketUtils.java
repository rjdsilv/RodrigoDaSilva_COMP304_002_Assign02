package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

/**
 * Utility class to deal with tickets in the application.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class TicketUtils {
    public static float ticketPrice(boolean isChild) {
        if (isChild) {
            return 13.50f;
        }

        return 19.00f;
    }
}

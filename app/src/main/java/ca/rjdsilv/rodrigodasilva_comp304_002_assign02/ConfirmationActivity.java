package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;
import java.util.UUID;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.TicketUtils;

/**
 * Class that will draw the confirmation activity.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class ConfirmationActivity extends AppCompatActivity {
    /**
     * Creates the activity.
     *
     * @param savedInstanceState The bundle to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        final MovieData data = MovieData.getInstance();

        // Sets the order id. Just generates a UUID and gets the last part.
        final TextView order = findViewById(R.id.lblOrderValue);
        final String uuid = UUID.randomUUID().toString();
        order.setText(uuid.substring(uuid.lastIndexOf('-') + 1).toUpperCase());

        // Sets the customer name.
        final TextView name = findViewById(R.id.lblNameValue);
        name.setText(data.getCustomerName());

        // Sets the name of the selected movie.
        final TextView movieName = findViewById(R.id.lblSelectedMovieValue);
        movieName.setText(data.getMovieName());

        // Sets the time of the selected movie.
        final TextView movieTime = findViewById(R.id.lblSelectedTimeValue);
        movieTime.setText(data.getTime());

        // Sets the tickets of the selected movie.
        final TextView movieTickets = findViewById(R.id.lblSelectedTicketsValue);
        movieTickets.setText(TicketUtils.ticketsData(data));

        // Sets the total value to pay.
        final TextView total = findViewById(R.id.lblTotalValue);
        total.setText(String.format(Locale.CANADA, "$%.2f", data.total()));
    }
}

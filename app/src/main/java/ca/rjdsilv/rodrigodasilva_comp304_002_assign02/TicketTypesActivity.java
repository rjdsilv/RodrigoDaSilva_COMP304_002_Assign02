package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.TicketUtils;

/**
 * Screen where the user will select the quantity of tickets to be purchased.
 */
public class TicketTypesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_types);

        // Sets the name of the selected movie.
        final MovieData data = MovieData.getInstance();
        final TextView movieName = findViewById(R.id.lblSelectedMovieValue);
        movieName.setText(data.getName());

        // Sets the name of the selected movie.
        final TextView movieTime = findViewById(R.id.lblSelectedTimeValue);
        movieTime.setText(data.getTime());

        // Sets the prices on the checkboxes.
        setTicketPrice((TextView) findViewById(R.id.lblAdultPrice), false);
        setTicketPrice((TextView)findViewById(R.id.lblChildPrice), true);
    }

    /**
     * Method that will handle the click event on the Activity checkboxes.
     *
     * @param view The view to have the event handled.
     */
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        final boolean checked = ((CheckBox) view).isChecked();

        // Gets the data instance.
        final MovieData data = MovieData.getInstance();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.chkAdult:
                if (checked) {
                    data.setAdultQuantity(
                            Integer.parseInt(getTextFromSpinner((Spinner) findViewById(R.id.spnAdult))));
                }
                break;
            case R.id.chkChildren:
                if (checked) {
                    data.setChildrenQuantity(
                            Integer.parseInt(
                                    getTextFromSpinner((Spinner) findViewById(R.id.spnChildren))));
                }
                break;
        }
    }

    /**
     * Event handler for the 'Checkout' button click.
     *
     * @param view The view to be used.
     */
    public void onCheckoutClick(View view) {
        final MovieData data = MovieData.getInstance();
        if (!data.hasTicketSelected()) {
            // Shows an error message.
            Toast.makeText(getApplicationContext(), "Please, select at least one ticket!", Toast.LENGTH_LONG).show();
        } else {
            // Go to the next screen.
            final Intent intent = new Intent(this, CheckoutActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Retrieves the selected text from the given spinner control.
     *
     * @param spinner The spinner to have the text retrieved.
     * @return The spinner's selected text.
     */
    private String getTextFromSpinner(Spinner spinner) {
        final TextView textView = ((TextView) spinner.getSelectedView());
        return textView.getText().toString();
    }

    /**
     * Sets the ticket price on the given text view.
     *
     * @param textView The text view to have the price set.
     */
    private void setTicketPrice(TextView textView, boolean isChild) {
        textView.setText(String.format(Locale.CANADA, "($%.2f) ", TicketUtils.ticketPrice(isChild)));
    }
}

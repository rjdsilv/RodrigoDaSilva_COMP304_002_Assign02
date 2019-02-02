package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.TicketUtils;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.ToastUtils;

/**
 * Screen where the user will select the quantity of tickets to be purchased.
 */
public class TicketTypesActivity extends AppCompatActivity {
    /**
     * Creates the activity.
     *
     * @param savedInstanceState The bundle to be used.
     */
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
        setTicketPrice((TextView) findViewById(R.id.lblChildPrice), true);

        // Sets the spinner events.
        final Spinner adultSpinner = findViewById(R.id.spnAdult);
        final Spinner childSpinner = findViewById(R.id.spnChildren);
        addItemSelectedListenerToSpinner(adultSpinner, data, false);
        addItemSelectedListenerToSpinner(childSpinner, data, true);
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
                    data.setAdultQuantity(Integer.parseInt(getTextFromSpinner((Spinner) findViewById(R.id.spnAdult))));
                } else {
                    data.setAdultQuantity(0);
                }
                break;
            case R.id.chkChildren:
                if (checked) {
                    data.setChildrenQuantity(Integer.parseInt(getTextFromSpinner((Spinner) findViewById(R.id.spnChildren))));
                } else {
                    data.setChildrenQuantity(0);
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
            ToastUtils.show(getApplicationContext(), "Please, select at least one ticket!");
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
     * Adds the click listener on the given spinner. The listener will sets the proper data on the
     * given movie data.
     *
     * @param spinner The spinner to have the text retrieved.
     * @param movieData The data to be set
     * @param isChild Flag indicating whether the ticket is for a child.
     */
    private void addItemSelectedListenerToSpinner(final Spinner spinner,
                                                  final MovieData movieData,
                                                  final boolean isChild) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final CheckBox adult = findViewById(R.id.chkAdult);
                final CheckBox child = findViewById(R.id.chkChildren);
                if (isChild) {
                    if (child.isChecked()) {
                        movieData.setChildrenQuantity(Integer.parseInt(getTextFromSpinner(spinner)));
                    } else {
                        movieData.setChildrenQuantity(0);
                    }
                } else {
                    if (adult.isChecked()) {
                        movieData.setAdultQuantity(Integer.parseInt(getTextFromSpinner(spinner)));
                    } else {
                        movieData.setAdultQuantity(0);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (isChild) {
                    movieData.setChildrenQuantity(0);
                } else {
                    movieData.setAdultQuantity(0);
                }
            }
        });
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

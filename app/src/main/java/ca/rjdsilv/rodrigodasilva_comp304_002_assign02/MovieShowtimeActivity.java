package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;

/**
 * Screen that will show the show times for the selected movie.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MovieShowtimeActivity extends AppCompatActivity {
    /**
     * Creates the activity showing the selected movie and the show times.
     *
     * @param savedInstanceState The bundle to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_showtime);

        // Sets the name of the selected movie.
        final TextView textView = findViewById(R.id.lblSelectedMovieValue);
        textView.setText(MovieData.getInstance().getName());

        // Sets the radio group event.
        final MovieShowtimeActivity currentActivity = this;
        final RadioGroup radioGroup = findViewById(R.id.rgpSelectTimes);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Gets the text from the selected item.
                final RadioButton checked = findViewById(checkedId);
                MovieData.getInstance().setTime(checked.getText().toString());

                // Select the intent.
                final Intent intent = new Intent(currentActivity, TicketTypesActivity.class);
                startActivity(intent);
            }
        });
    }
}

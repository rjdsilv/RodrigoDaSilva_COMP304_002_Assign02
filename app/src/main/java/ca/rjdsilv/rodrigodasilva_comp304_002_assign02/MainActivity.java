package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Main activity representing the application's main screen.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Creates the activity.
     *
     * @param savedInstanceState The saved instance to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Event handler for the 'Buy Your Ticket' button click.
     *
     * @param view The view to be used.
     */
    public void onBuyTicketsClick(View view) {
        startActivity(new Intent(this, MovieListActivity.class));
    }
}

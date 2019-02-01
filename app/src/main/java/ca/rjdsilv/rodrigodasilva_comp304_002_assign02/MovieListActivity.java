package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieUtils;

/**
 * Shows the list of movies in a options menu.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MovieListActivity extends AppCompatActivity {
    /**
     * Creates the activity.
     *
     * @param savedInstanceState The bundle to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
    }

    /**
     * Creates the options menu with icons.
     *
     * @param menu The menu to be inflated.
     * @return true for the menu creation.
     */
    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_options_menu, menu);

        if (menu instanceof MenuBuilder) {
            final MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    /**
     * Method to handle the click on a menu item.
     *
     * @param item The item clicked
     * @return true for the item clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent intent = new Intent(this, MovieShowtimeActivity.class);
        MovieData.getInstance().setName(MovieUtils.movieName(item.getItemId(), getResources()));
        startActivity(intent);
        return true;
    }
}

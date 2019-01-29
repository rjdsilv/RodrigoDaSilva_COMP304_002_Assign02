package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;

public class MovieShowtimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_showtime);
        Toast.makeText(getApplicationContext(), "Selected Movie: " + MovieData.getInstance().getName(), Toast.LENGTH_SHORT).show();
    }
}

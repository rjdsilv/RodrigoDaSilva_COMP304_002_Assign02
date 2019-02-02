package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

import android.content.res.Resources;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.R;

/**
 * Utility class that will handle the movies by their ids..
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MovieUtils {
    public static String movieName(int id, Resources res) {
        switch (id) {
            case R.id.movieGlass:
                return res.getString(R.string.lbl_glass);

            case R.id.movieBumblebee:
                return res.getString(R.string.lbl_bumblebee);

            case R.id.movieEscapeRoom:
                return res.getString(R.string.lbl_escape_room);

            case R.id.movieReplicas:
                return res.getString(R.string.lbl_replicas);

            case R.id.movieAquaman:
                return res.getString(R.string.lbl_aquaman);
        }

        return StringUtils.EMPTY;
    }
}

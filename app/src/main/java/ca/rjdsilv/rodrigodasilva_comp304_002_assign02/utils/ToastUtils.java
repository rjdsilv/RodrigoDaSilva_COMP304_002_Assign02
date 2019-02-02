package ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Class responsible for displaying a toast message on the screen.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class ToastUtils {
    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

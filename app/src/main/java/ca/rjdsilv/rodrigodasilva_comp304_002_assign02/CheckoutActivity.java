package ca.rjdsilv.rodrigodasilva_comp304_002_assign02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.MovieData;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.StringUtils;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.TicketUtils;
import ca.rjdsilv.rodrigodasilva_comp304_002_assign02.utils.ToastUtils;

/**
 * Class that will handle the checkout screen on the application.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class CheckoutActivity extends AppCompatActivity {
    /**
     * Credit card formatter class. This formatter code was gotten from stack overflow
     * https://stackoverflow.com/questions/11790102/format-credit-card-in-edit-text-in-android
     * from Randy Sugianto 'Yuku' answer.
     */
    private static class CreditCardTextWatcher implements TextWatcher {
        private static final char space = ' ';

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Remove all spacing char
            int pos = 0;
            while (pos < s.length()) {
                if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                    s.delete(pos, pos + 1);
                } else {
                    pos++;
                }
            }

            // Insert char where needed.
            pos = 4;
            while (pos < s.length()) {
                final char c = s.charAt(pos);
                // Only if its a digit where there should be a space we insert a space
                if ("0123456789".indexOf(c) >= 0) {
                    s.insert(pos, "" + space);
                }
                pos += 5;
            }
        }
    }

    /**
     * Creates the activity.
     *
     * @param savedInstanceState The bundle to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Sets the name of the selected movie.
        final MovieData data = MovieData.getInstance();
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

        // Phone number formatter
        final EditText phone = findViewById(R.id.txtPhone);
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        // Credit card formatter
        final EditText creditCard = findViewById(R.id.txtCreditCardNumber);
        creditCard.addTextChangedListener(new CreditCardTextWatcher());
    }

    /**
     * Event handler for the 'Checkout' button click.
     *
     * @param view The view to be used.
     */
    public void onConfirmTicketsClick(View view) {
        if (fieldsValid()) {
            final EditText txtFirstName = findViewById(R.id.txtFirstName);
            final EditText txtLastName = findViewById(R.id.txtLastName);
            MovieData.getInstance().setFirstName(txtFirstName.getText().toString());
            MovieData.getInstance().setLastName(txtLastName.getText().toString());
            startActivity(new Intent(this, ConfirmationActivity.class));
        }
    }

    /**
     * Method that will be used to perform the fields validation.
     *
     * @return <b>true</b> if all fields are valid. <b>false</b> otherwise
     */
    private boolean fieldsValid() {
        if (!firstNameValid()) {
            ToastUtils.show(getApplicationContext(), "The first name must be filled!");
            return false;
        }

        if (!lastNameValid()) {
            ToastUtils.show(getApplicationContext(), "The last name must be filled!");
            return false;
        }

        if (!emailValid()) {
            ToastUtils.show(getApplicationContext(), "Please, enter a valid e-mail address");
            return false;
        }

        if (!phoneValid()) {
            ToastUtils.show(getApplicationContext(), "Please, enter a valid phone number");
            return false;
        }

        if (!addressValid()) {
            ToastUtils.show(getApplicationContext(), "Please, enter a valid address");
            return false;
        }

        if (!creditCardValid()) {
            ToastUtils.show(getApplicationContext(), "Please, enter a valid credit card number");
            return false;
        }

        if (!cvvValid()) {
            ToastUtils.show(getApplicationContext(), "Please, enter a valid cvv");
            return false;
        }

        if (!monthValid()) {
            ToastUtils.show(getApplicationContext(), "Please, enter a valid month");
            return false;
        }

        if (!yearValid()) {
            final int year = Calendar.getInstance().get(Calendar.YEAR);
            ToastUtils.show(getApplicationContext(),
                    String.format(
                            Locale.CANADA,
                            "Please, enter a year between %d, %d",
                            year, year + 10));
            return false;
        }

        return true;
    }

    /**
     * Validates the first name field.
     *
     * @return <b>true</b> if the first name is valid. <b>false</b> otherwise
     */
    private boolean firstNameValid() {
        final EditText txtFirstName = findViewById(R.id.txtFirstName);
        return StringUtils.isNotBlank(txtFirstName.getText().toString());
    }

    /**
     * Validates the last name field.
     *
     * @return <b>true</b> if the last name is valid. <b>false</b> otherwise
     */
    private boolean lastNameValid() {
        final EditText txtLastName = findViewById(R.id.txtLastName);
        return StringUtils.isNotBlank(txtLastName.getText().toString());
    }

    /**
     * Validates the email field.
     *
     * @return <b>true</b> if the email is valid. <b>false</b> otherwise
     */
    private boolean emailValid() {
        final EditText txtEmail = findViewById(R.id.txtEmail);
        return StringUtils.isValidEmail(txtEmail.getText().toString());
    }

    /**
     * Validates the phone field.
     *
     * @return <b>true</b> if the phone is valid. <b>false</b> otherwise
     */
    private boolean phoneValid() {
        final EditText txtPhone = findViewById(R.id.txtPhone);
        return StringUtils.isValidPhone(txtPhone.getText().toString());
    }

    /**
     * Validates the address field.
     *
     * @return <b>true</b> if the address is valid. <b>false</b> otherwise
     */
    private boolean addressValid() {
        final EditText txtAddress = findViewById(R.id.txtAddressLine);
        return StringUtils.isNotBlank(txtAddress.getText().toString()) && txtAddress.length() >= 5;
    }

    /**
     * Validates the credit card field.
     *
     * @return <b>true</b> if the credit card is valid. <b>false</b> otherwise
     */
    private boolean creditCardValid() {
        final EditText txtCreditCard = findViewById(R.id.txtCreditCardNumber);
        return StringUtils.isCreditCard(txtCreditCard.getText().toString());
    }

    /**
     * Validates the cvv field.
     *
     * @return <b>true</b> if the cvv is valid. <b>false</b> otherwise
     */
    private boolean cvvValid() {
        final EditText txtCvv = findViewById(R.id.txtCreditCardCvv);
        return StringUtils.isCvv(txtCvv.getText().toString());
    }

    /**
     * Validates the month field.
     *
     * @return <b>true</b> if the month is valid. <b>false</b> otherwise
     */
    private boolean monthValid() {
        final EditText txtMonth = findViewById(R.id.txtCreditCardMonth);
        return StringUtils.isMonth(txtMonth.getText().toString());
    }

    /**
     * Validates the year field.
     *
     * @return <b>true</b> if the year is valid. <b>false</b> otherwise
     */
    private boolean yearValid() {
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        final EditText txtYear = findViewById(R.id.txtCreditCardYear);
        return StringUtils.isYearInRange(txtYear.getText().toString(), year, year + 10);
    }
}

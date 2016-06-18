package ds.mappie.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by George-PC on 18-Jun-16.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    ds.mappie.dialogs.TimePicker time;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return dialog;

    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;

        time = new ds.mappie.dialogs.TimePicker();
        time.show(getFragmentManager(), "timepicker");

    }

    @Override
    public String toString(){
        return String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + " " + time.getHour() + ":" + time.getMinute();
    }


    public ds.mappie.dialogs.TimePicker getTime() {
        return time;
    }
}

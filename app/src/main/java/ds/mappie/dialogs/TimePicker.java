package ds.mappie.dialogs;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import java.util.Calendar;

public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePickerDialog time = new TimePickerDialog(getActivity(), this, hour, minute, true);
        return time;

    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        this.minute = minute;

    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
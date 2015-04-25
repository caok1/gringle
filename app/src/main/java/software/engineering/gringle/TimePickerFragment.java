package software.engineering.gringle;

/*import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;*/

/**
 * Created by kevin on 4/19/15.
 */
/*public class TimePickerFragment extends DialogFragment {
    public static final String EXTRA_TIME =
            "software.engineering.gringle.time";

    private Date mDate;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;
        Intent i = new Intent();
        i.putExtra(EXTRA_TIME, mDate);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_TIME);

        // Create a Calendar to get the year, month, and day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_time, null);

        TimePicker timePicker = (TimePicker) v.findViewById(R.id.dialog_time_timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //mDate.setHours(hourOfDay);
                //mDate.setMinutes(minute);
                mDate = new GregorianCalendar(0, 0, 0, hourOfDay, minute).getTime();
                // Update argument to preserve selected value on rotation
                getArguments().putSerializable(EXTRA_TIME, mDate);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }
}
*/

import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.DialogFragment;
        import android.view.View;
        import android.widget.TimePicker;
        import android.widget.TimePicker.OnTimeChangedListener;

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: TimePickerFragment.java
 * Description: A fragment class used to display the TimePicker widget.
 * Hosted by DraftPagerActivity
 *Last modified on: 4/20/15
 */

public class TimePickerFragment extends DialogFragment {
    public static final String EXTRA_TIME =
            "software.engineering.gringle.time";

    private Date mDate;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_TIME);

        Calendar cal = Calendar.getInstance();
        cal.setTime(mDate);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
        TimePicker tp = (TimePicker)v.findViewById(R.id.dialog_time_timePicker);
        tp.setCurrentHour(hours);
        tp.setCurrentMinute(mins);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(mDate);
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);

                mDate = cal.getTime();
                getArguments().putSerializable(EXTRA_TIME, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK);
                    }
                }).create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) return;
        Intent i = new Intent();
        i.putExtra(EXTRA_TIME, mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}


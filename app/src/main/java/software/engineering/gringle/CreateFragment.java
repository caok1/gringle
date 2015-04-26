package software.engineering.gringle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kevin on 4/25/15.
 */

public class CreateFragment extends Fragment {
    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_TIME = "time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Message mMessage;

    private EditText mRecipientTitleField;
    private Button mDateDelayButton;
    private Button mTimeDelayButton;
    private EditText mContentField;

    private Button mSaveButton;
    private Button mQueueButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = new Message();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, parent, false);

        mRecipientTitleField = (EditText)v.findViewById(R.id.recipient);
        mRecipientTitleField.setText(mMessage.getRecipientTitle());
        mRecipientTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mMessage.setRecipientTitle(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //Intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }

        });

        mContentField = (EditText)v.findViewById(R.id.message_content);
        mContentField.setText(mMessage.getContent());
        mContentField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mMessage.setContent(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //Intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }

        });

        mDateDelayButton = (Button) v.findViewById(R.id.date_delay);
        updateDate();
        mDateDelayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mMessage.getCreationDate());
                dialog.setTargetFragment(CreateFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mTimeDelayButton = (Button) v.findViewById(R.id.time_delay);
        updateTime();
        mTimeDelayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mMessage.getCreationDate());
                dialog.setTargetFragment(CreateFragment.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
            }
        });

        mSaveButton = (Button)v.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    DraftHolder.get(getActivity()).addDraft(mMessage);
                    getActivity().onBackPressed();
                    Toast.makeText(getActivity(),"Message Saved!",
                            Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                } catch (Exception e) {
                    Toast.makeText(getActivity(),"Save failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        mQueueButton = (Button)v.findViewById(R.id.queue_button);
               // mQueueButton.setEnabled(false);
        //Temporary implementation. Testing if sending text messages works.
        mQueueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNo = mRecipientTitleField.getText().toString();
                String sms = mContentField.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getActivity(),"SMS Sent!",
                            Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                } catch (Exception e) {
                    Toast.makeText(getActivity(),"SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMessage.setCreationDate(date);
            updateDate();
        } else if (requestCode == REQUEST_TIME) {
            Date date = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mMessage.setCreationDate(date);
            updateTime();
        }
    }

    public void updateDate() {
        Date date = mMessage.getCreationDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        mDateDelayButton.setText(sdf.format(date).toString());
    }

    public void updateTime() {
        Date date = mMessage.getCreationDate();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        mTimeDelayButton.setText(sdf.format(date).toString());
    }
}

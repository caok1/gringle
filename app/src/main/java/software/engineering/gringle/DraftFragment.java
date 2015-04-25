package software.engineering.gringle;

import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.UUID;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.text.format.DateFormat;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.CompoundButton.OnCheckedChangeListener;
        import android.widget.EditText;

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: DraftFragment.java
 * Description: A fragment that manages the UI screen for viewing the details and
 * editing drafts
 *Last modified on: 4/23/15
 */

public class DraftFragment extends Fragment {
    public static final String EXTRA_DRAFT_ID =
            "software.engineering.gringle.draft_id";

    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_TIME = "time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Message mMessage;
    private EditText mRecipientTitleField;
    private EditText mContentField;
    private Button mDateDelayButton;
    private Button mTimeDelayButton;

    private Button mDeleteButton;
    private Button mSaveButton;
    private Button mQueueButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID draftId = (UUID) getArguments().getSerializable(EXTRA_DRAFT_ID);

        mMessage = DraftHolder.get(getActivity()).getMessage(draftId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_draft, parent, false);

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

        mDateDelayButton = (Button) v.findViewById(R.id.date_delay);
        updateDate();
        mDateDelayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mMessage.getCreationDate());
                dialog.setTargetFragment(DraftFragment.this, REQUEST_DATE);
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
                dialog.setTargetFragment(DraftFragment.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
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

        mDeleteButton = (Button)v.findViewById(R.id.delete_button);
        mDeleteButton.setEnabled(false);

        mSaveButton = (Button)v.findViewById(R.id.save_button);
        mSaveButton.setEnabled(false);

        mQueueButton = (Button)v.findViewById(R.id.queue_button);
        mQueueButton.setEnabled(false);

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        mDateDelayButton.setText(sdf.format(date).toString());
    }

    public void updateTime() {
        Date date = mMessage.getCreationDate();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        mTimeDelayButton.setText(sdf.format(date).toString());
    }

    public static DraftFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DRAFT_ID, crimeId);

        DraftFragment fragment = new DraftFragment();
        fragment.setArguments(args);

        return fragment;
    }
}

package software.engineering.gringle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;


/**
 * Created by kevin on 4/10/15.
 * Connected to HomeActivity
 */
public class DraftFragment extends Fragment {
    public static final String EXTRA_DRAFT_ID =
            "software.engineering.gringle.crime_id";

    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    private Message mMessage;
    private EditText mRecipientTitleField;
    private Button mTimeDelayButton;
    private EditText mContentField;

    private Button mDeleteButton;
    private Button mSaveButton;
    private Button mQueueButton;

    private void updateDate() {
        //change to send time delay when ready
        mTimeDelayButton.setText(mMessage.getCreationDate().toString());
    }

    public static DraftFragment newInstance(UUID draftId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DRAFT_ID, draftId);
        DraftFragment fragment = new DraftFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMessage.setCreationDate(date);
            //change creation date to send time later
            updateDate();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID draftId = (UUID)getArguments().getSerializable(EXTRA_DRAFT_ID);

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

        //Need to replace creation date with set time later when set time works
        mTimeDelayButton = (Button)v.findViewById(R.id.time_delay);
        updateDate();
        mTimeDelayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mMessage.getCreationDate());
                dialog.setTargetFragment(DraftFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
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
}
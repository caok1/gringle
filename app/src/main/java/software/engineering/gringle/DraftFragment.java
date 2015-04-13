package software.engineering.gringle;

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

import java.util.UUID;


/**
 * Created by kevin on 4/10/15.
 * Connected to HomeActivity
 */
public class DraftFragment extends Fragment {
    public static final String EXTRA_DRAFT_ID =
            "software.engineering.gringle.crime_id";

    private Message mMessage;
    private EditText mRecipientTitleField;
    private Button mTimeDelayButton;
    private EditText mContentField;

    private Button mDeleteButton;
    private Button mSaveButton;
    private Button mQueueButton;

    public static DraftFragment newInstance(UUID draftId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DRAFT_ID, draftId);
        DraftFragment fragment = new DraftFragment();
        fragment.setArguments(args);

        return fragment;
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
        mTimeDelayButton.setText(mMessage.getCreationDate().toString());
        mTimeDelayButton.setEnabled(false);


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
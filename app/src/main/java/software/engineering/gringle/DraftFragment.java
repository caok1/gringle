package software.engineering.gringle;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by kevin on 4/10/15.
 * Connected to HomeActivity
 */
public class DraftFragment extends Fragment {
    private Message mMessage;
    private EditText mRecipientTitleField;
    private EditText mTimeTitleField;
    private EditText mContentField;

    private Button mDeleteButton;
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
        View v = inflater.inflate(R.layout.fragment_draft, parent, false);

        mRecipientTitleField = (EditText)v.findViewById(R.id.recipient);
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

        mTimeTitleField = (EditText)v.findViewById(R.id.time_delay);
        mTimeTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mMessage.setTimeTitle(c.toString());
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
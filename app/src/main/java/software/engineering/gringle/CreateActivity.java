package software.engineering.gringle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kevin on 4/1/15.
 */
public class CreateActivity extends Activity {

    private Message mMessage;
    private EditText mRecipientTitleField;
    private EditText mTimeTitleField;
    private EditText mContentTitleField;

    private Button mSaveButton;
    private Button mQueueButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mMessage = new Message();

        mRecipientTitleField = (EditText) findViewById(R.id.recipient);
        mTimeTitleField = (EditText) findViewById(R.id.time_delay);
        mContentTitleField = (EditText) findViewById(R.id.message_content);

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setEnabled(false);

        mQueueButton = (Button) findViewById(R.id.queue_button);
        mQueueButton.setEnabled(false);

//Code Used in Criminal Intent. Slightly different because it used fragments
      /*  mRecipientTitleField = (EditText)v.findViewById(R.id.recipient);
        mRecipientTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                    mMessage.setRecipientTitle(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int before, int count) {
                // Purpose Left Blank for Now
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }
        });
        return v;*/
    }
}
/*
{
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                    // This space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }
        });
        return v;
    }
 */

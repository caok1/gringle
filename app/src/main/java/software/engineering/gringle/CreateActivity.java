package software.engineering.gringle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kevin on 4/1/15.
 */
public class CreateActivity extends ActionBarActivity {

    private Message mMessage;
    private EditText mRecipientTitleField;
    private EditText mTimeTitleField;
    private EditText mContentField;

    private Button mSaveButton;
    private Button mQueueButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Just an action bar to display "new message" to inform user where they are
        //Maybe not the best way to do this
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.new_message_title);



        mMessage = new Message();

        mRecipientTitleField = (EditText) findViewById(R.id.recipient);
        mTimeTitleField = (EditText) findViewById(R.id.time_delay);
        mContentField = (EditText) findViewById(R.id.message_content);

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setEnabled(false);

        mQueueButton = (Button) findViewById(R.id.queue_button);
        mQueueButton.setEnabled(false);
    }
}
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
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: CreateActivity.java
 * Description: Allows the user to create a new delayed message to be saved
 * as a draft or queued
 *Last modified on: 4/5/15
 */

public class CreateActivity extends ActionBarActivity {

    private Message mMessage;
    private EditText mRecipientTitleField;
    private Button mTimeDelayButton;
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
        mTimeDelayButton = (Button) findViewById(R.id.time_delay);
        mTimeDelayButton.setEnabled(false);
        mContentField = (EditText) findViewById(R.id.message_content);

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setEnabled(false);

        mQueueButton = (Button) findViewById(R.id.queue_button);
        mQueueButton.setEnabled(false);
    }
}
package software.engineering.gringle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: HomeActivity.java
 * Description: The home screen of the app that will allow the user to create new messages,
 * view saved drafts, or view queued messages.
 *Last modified on: 4/1/15
 */

public class HomeActivity extends ActionBarActivity {
    private Button mNewMessageButton;
    private Button mViewDraftsButton;
    private Button mViewQueuedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNewMessageButton = (Button) findViewById(R.id.new_message_button);
        mNewMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CreateActivity.class);
                startActivity(i);
            }
        });

        mViewDraftsButton = (Button) findViewById(R.id.view_draft_button);
        mViewDraftsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DraftListActivity.class);
                startActivity(i);
            }
        });
        //mViewDraftsButton.setEnabled(false);

        mViewQueuedButton = (Button) findViewById(R.id.view_queue_button);
        mViewQueuedButton.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

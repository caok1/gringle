package software.engineering.gringle;


import android.support.v4.app.Fragment;

/**
 * Created by kevin on 4/11/15.
 */

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: DraftListActivity.java
 * Description: Hosts DraftListFragment that displays the list of drafts
 *Last modified on: 4/11/15
 */

public class DraftListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DraftListFragment();
    }

}

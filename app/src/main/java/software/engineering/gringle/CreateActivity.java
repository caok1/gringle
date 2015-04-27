package software.engineering.gringle;

import android.support.v4.app.Fragment;

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: CreateActivity.java
 * Description: Hosts the fragment for creating new delayed messages.
 * as a draft or queued
 *Last modified on: 4/25/15
 */

public class CreateActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CreateFragment();
    }
}
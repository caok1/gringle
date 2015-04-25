package software.engineering.gringle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

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
 * Filename: SingleFragmentActivity.java
 * Description: A generic fragment class used to create other fragments.
 *Last modified on: 4/1/15
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
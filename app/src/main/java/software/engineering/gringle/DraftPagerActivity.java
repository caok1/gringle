package software.engineering.gringle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by kevin on 4/12/15.
 */

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: DraftPagerActivity.java
 * Description: An activity that hosts DraftFragment to display a draft message.Also provides
 * a UI that lets users swipe between drafts within the list
 *Last modified on: 4/12/15
 */

public class DraftPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Message> mDrafts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mDrafts = DraftHolder.get(this).getDrafts();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int pos) {
                Message draft = mDrafts.get(pos);
                return DraftFragment.newInstance(draft.getId());
            }

            @Override
            public int getCount() {
                return mDrafts.size();
            }
        });

        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(DraftFragment.EXTRA_DRAFT_ID);
        for (int i = 0; i < mDrafts.size(); i++) {
            if (mDrafts.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}

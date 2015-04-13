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

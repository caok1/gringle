package software.engineering.gringle;


import android.support.v4.app.Fragment;

/**
 * Created by kevin on 4/11/15.
 */
public class DraftListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DraftListFragment();
    }

}

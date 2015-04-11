package software.engineering.gringle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by kevin on 4/10/15.
 */
public class DraftActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DraftFragment();
    }
}

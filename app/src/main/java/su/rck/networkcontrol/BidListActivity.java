package su.rck.networkcontrol;

import android.support.v4.app.Fragment;

public class BidListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BidListFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        QueryPreferences.deletePrefAuthorizedUserID(this);
    }
}

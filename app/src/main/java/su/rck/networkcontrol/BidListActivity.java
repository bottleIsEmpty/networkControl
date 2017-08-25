package su.rck.networkcontrol;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BidListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BidListFragment();
    }
}

package su.rck.networkcontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;

/**
 * Created by Александр on 10.08.2017.
 */

public class SignInActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_сontainer);

        if (fragment == null) {
            fragment = new SignInFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_сontainer, fragment)
                    .commit();
        }
    }
}

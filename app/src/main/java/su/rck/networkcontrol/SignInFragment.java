package su.rck.networkcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Александр on 23.08.2017.
 */

public class SignInFragment extends Fragment {

    private TextView loginField;
    private TextView passwordField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        loginField = view.findViewById(R.id.login_field);
        passwordField = view.findViewById(R.id.password_field);

        Button btn = view.findViewById(R.id.sign_in_button);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (checkFields()) {
                    Intent intent = new Intent(getActivity(), BidListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return  view;
    }

    public boolean checkFields() {
        String rLogin = loginField.getText().toString();
        String rPassword = passwordField.getText().toString();

        return (!rLogin.isEmpty() && !rPassword.isEmpty());
    }
}

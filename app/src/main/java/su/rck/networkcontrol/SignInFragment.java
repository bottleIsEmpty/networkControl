package su.rck.networkcontrol;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Александр on 23.08.2017.
 */

public class SignInFragment extends Fragment {

    private final String TAG = "su.rck.SignInFragment";
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
                    new doLoginTask().execute();
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

    private class doLoginTask extends AsyncTask<Void, Void, Boolean> {
        boolean response;
        String login;
        String password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            login = loginField.getText().toString();
            password = passwordField.getText().toString();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                response = NetworkController.get().signIn(login, password);
            } catch (IOException ioe) {
                Log.e(TAG, ioe.toString());
            } catch (JSONException je) {
                Log.e(TAG, je.toString());
            }
            return response;
        }


        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            if (response) {
                Intent intent = new Intent(getActivity(), BidListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Неверный логин или пароль", Toast.LENGTH_LONG).show();
            }
        }
    }
}

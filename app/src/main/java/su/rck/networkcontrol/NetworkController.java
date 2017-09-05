package su.rck.networkcontrol;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Александр on 02.09.2017.
 */

public class NetworkController {

    private static NetworkController sNetworkController;
    private static final String TAG = "rck.NetworkController";

    //HTTP-Адреса для запросов

    private static final String SIGN_IN_ADDRESS = "http://c668044p.beget.tech/login.php";   //Вход в систему
    private static final String GET_BIDS_ADDRESS = "http://c668044p.beget.tech/index.php";  //Пполучение всех заявок мастера

    //Конструктор класса

    private NetworkController() {
    }

    //Функция, возвращающая singleton-экземпляр класса

    static public NetworkController get() {
        if (sNetworkController == null) {
            sNetworkController = new NetworkController();
        }

        return sNetworkController;
    }
    //POST-запрос к серверу

    private String getUrlString(String urlSpec, String param) throws IOException {
        param = "data=" + param;
        byte[] data = param.getBytes();

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.getOutputStream().write(data);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            byte[] buffer = new byte[1024];
            for (int bytesRead; (bytesRead = in.read(buffer)) > 0; ) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toString();
        } finally {
            connection.disconnect();
        }

    }

    //Получение всех заявок конкретного мастера

    public List<Bid> fetchBids (int masterId) throws IOException, JSONException, ParseException {
        JSONObject params = new JSONObject();

        params.put("id", masterId);

        String stringParams = params.toString();
        String response = getUrlString(GET_BIDS_ADDRESS, stringParams);

        return parseBids(response);
    }

    //Разбор JSON-строки и возврат массива заявок

    private List<Bid> parseBids(String JSONData) throws JSONException, ParseException{
        List<Bid> bids = new ArrayList<>();

        JSONObject jBidsObject = new JSONObject(JSONData);
        JSONArray jBidsArray = jBidsObject.getJSONArray("bids");

        for (int i = 0; i < jBidsArray.length(); i++) {
            JSONObject bidJsonObject = jBidsArray.getJSONObject(i);

            int id = bidJsonObject.getInt("id");
            String district = bidJsonObject.getString("district");
            String street = bidJsonObject.getString("street");
            String house = bidJsonObject.getString("house") + "/" +
                    bidJsonObject.getInt("flat");
            Date date;
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.ENGLISH);
            date = format.parse(bidJsonObject.getString("date"));
            boolean routerState = bidJsonObject.getBoolean("is_active");
            String phone = bidJsonObject.getString("phone");
            String details = bidJsonObject.getString("details");

            bids.add(new Bid(id, district, street, house, date, routerState, phone, details));
        }

        return bids;
    }

    //Вход в систему

    public boolean signIn(String login, String password)  throws IOException, JSONException{
        JSONObject params = new JSONObject();

        params.put("password", password);
        params.put("login", login);

        String stringParams = params.toString();

        String respString = getUrlString(SIGN_IN_ADDRESS, stringParams);
        Log.d(TAG, respString);

        JSONObject result = new JSONObject(respString);

        return result.getBoolean("result");
    }
}
package su.rck.networkcontrol;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Александр on 05.09.2017.
 */

public class QueryPreferences {

    private static final String PREF_LAST_RESULT_ID = "lastResultId";

    public static String getPrefLastResultID(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setPrefLastResultId(Context context, int lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_LAST_RESULT_ID, lastResultId)
                .apply();
    }
}

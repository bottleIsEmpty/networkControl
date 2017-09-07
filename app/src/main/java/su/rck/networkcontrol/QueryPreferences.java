package su.rck.networkcontrol;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Александр on 05.09.2017.
 */

public class QueryPreferences {

    private static final String PREF_LAST_RESULT_ID = "lastResultId";
    private static final String PREF_AUTHORIZED_USER_ID = "authorizedUserId";

    public static String getPrefLastResultID(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setPrefLastResultId(Context context, int lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_LAST_RESULT_ID, lastResultId)
                .apply();
    }

    public static void setPrefAuthorizedUserID(Context context, int userId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_AUTHORIZED_USER_ID, userId)
                .apply();
    }

    public static int getPrefAuthorizedUserID(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_AUTHORIZED_USER_ID, 0);
    }

    public static void deletePrefAuthorizedUserID(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .remove(PREF_AUTHORIZED_USER_ID)
                .apply();
    }
}

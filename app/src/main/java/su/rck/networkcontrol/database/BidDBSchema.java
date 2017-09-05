package su.rck.networkcontrol.database;

/**
 * Created by Александр on 28.08.2017.
 */

public class BidDBSchema {
    public static final String DB_NAME = "BidDB";
    public static final int DB_VERSION = 1;

    public static final class BidTable {
        public static final String NAME = "bids";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String DISTRICT = "district";
            public static final String STREET = "street";
            public static final String HOUSE = "house";
            public static final String DATE = "date";
            public static final String ROUTER_STATE = "router_state";
            public static final String PHONE = "phone";
            public static final String DETAILS = "details";
            public static final String MASTER = "master";
        }
    }

    public static final class UserTable {
        public static final String NAME = "users";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String USER_NAME = "user_name";
            public static final String USER_SURNAME = "user_surname";
            public static final String LOGIN = "login";
            public static final String PASSWORD = "password";
        }
    }
}

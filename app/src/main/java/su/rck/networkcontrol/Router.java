package su.rck.networkcontrol;

import java.sql.Time;

/**
 * Created by Александр on 15.07.2017.
 */

public class Router {

    private int RRouterId;          //Идентификатор роутера
    private boolean RIsWorking;     //Текущее состояние роутера
    private Time RCheckTime;        //Время проверки соединения

    public Router (int routerId, boolean isWorking, Time checkTime) {
        RRouterId = routerId;
        RIsWorking = isWorking;
        RCheckTime = checkTime;
    }

    public boolean isWorking() {
        return RIsWorking;
    }


}

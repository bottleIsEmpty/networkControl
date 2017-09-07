package su.rck.networkcontrol;

import android.util.Log;

import java.util.Date;
import java.util.UUID;

/**
 * Класс, содержащий информацию о заявке
 */

public class Bid {

    private int BID;             //Номер заявки
    private String BDistrict;    //Район
    private String BStreet;      //Улица
    private String BHouse;       //Дом/квартира
    private Date BDate;          //Дата/время заявки
    private String BPhoneNumber; //Номер телефона
    private boolean BRouterState;//Состояние роутера
    private String BDetails;     //Детали заявки
    private int BMaster;         //Мастер, ответственный за заявку

    //Конструкторы



    public Bid (int ID, String District, String Street, String House, Date Date, boolean RouterState, String PhoneNumber,
                String Details, int Master) {
        BID = ID;
        BDistrict = District;
        BStreet = Street;
        BHouse = House;
        BDate = Date;
        BRouterState = RouterState;
        BPhoneNumber = PhoneNumber;
        BDetails = Details;
        BMaster = Master;
    }

    //Сеттеры/Геттеры

    public String getDistrict() {
        return BDistrict;
    }

    public void setDistrict(String BDistrict) {
        this.BDistrict = BDistrict;
    }

    public String getStreet() {
        return BStreet;
    }

    public void setStreet(String BStreet) {
        this.BStreet = BStreet;
    }

    public String getHouse() {
        return BHouse;
    }

    public void setHouse(String BHouse) {
        this.BHouse = BHouse;
    }

    public String getDetails() {
        return BDetails;
    }

    public void setDetails(String BDetails) {
        this.BDetails = BDetails;
    }

    public String getAddress() {
        return "#" + BID + " " + BDistrict + ", " + BStreet + ", " + BHouse;
    }

    public int getID() {
        return BID;
    }

    public void setID(int BID) {
        this.BID = BID;
    }


    public Date getDate() {
        return BDate;
    }

    public void setDate(Date BDate) {
        this.BDate = BDate;
    }

    public String getPhoneNumber() {
        return BPhoneNumber;
    }

    public void setPhoneNumber(String BPhoneNumber) {
        this.BPhoneNumber = BPhoneNumber;
    }

    public boolean getRouterState() {
        return BRouterState;
    }

    public void setRouterState(boolean BRouterState) {
        this.BRouterState = BRouterState;
    }

    public int getMaster() {
        return BMaster;
    }

    public void setMaster(int BMaster) {
        this.BMaster = BMaster;
    }

    public void showInfo() {
        Log.d("BIDCLASS", BID + " " + BDistrict + " " + BStreet + " " + BHouse + " " + BDate + " " + BRouterState + " " + BPhoneNumber + " " + BDetails);
    }

}

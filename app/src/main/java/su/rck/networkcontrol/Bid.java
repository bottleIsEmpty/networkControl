package su.rck.networkcontrol;

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
    private String BDetails;     //Детали заявки

    //Конструкторы



    public Bid (int ID, String District, String Street, String House, Date Date, String PhoneNumber,
                String Details) {
        BID = ID;
        BDistrict = District;
        BStreet = Street;
        BHouse = House;
        BDate = Date;
        BPhoneNumber = PhoneNumber;
        BDetails = Details;
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


}

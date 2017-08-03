package su.rck.networkcontrol;

import java.util.Date;
import java.util.UUID;

/**
 *
 * Класс, содержащий информацию о заявке
 *
 */

public class Bid {

    private UUID BID;            //Номер заявки
    private String BAddress;     //Адресс
    private Date BDate;          //Дата/время заявки
    private String BPhoneNumber; //Номер телефона
    private String BInfo;        //Текст заявки
    private boolean BIsOpened;   //Открыта/закрыта

    //Конструктор

    public Bid (UUID ID, String Address, Date Date, String PhoneNumber, String Info, boolean IsOpened) {
        BID = ID;
        BAddress = Address;
        BDate = Date;
        BPhoneNumber = PhoneNumber;
        BInfo = Info;
        BIsOpened = IsOpened;
    }

    //Сеттеры/Геттеры

    public UUID getID() {
        return BID;
    }

    public void setID(UUID BID) {
        this.BID = BID;
    }

    public String getAddress() {
        return BAddress;
    }

    public void setAddress(String BAddress) {
        this.BAddress = BAddress;
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

    public String getInfo() {
        return BInfo;
    }

    public void setInfo(String BInfo) {
        this.BInfo = BInfo;
    }

    public boolean isOpened() {
        return BIsOpened;
    }

    public void setOpened(boolean BIsOpened) {
        this.BIsOpened = BIsOpened;
    }
}

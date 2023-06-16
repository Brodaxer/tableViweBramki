package pl.tvknet.stanurzadzen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class UrzadzenieMagazyn {
    private StringProperty Model;
    private StringProperty SerialNumber;
    private StringProperty MAC;
    private Stan Stan;
    private StringProperty Lokalizacja;
   // private Button button ;


//    public Button getButton() {
//        return button;
//    }
//
//    public void setButton(Button button) {
//        this.button = button;
//    }

    @Override
    public String toString() {
        return  Model +
                "," +SerialNumber +
                "," + MAC +
                "," + Stan +
                "" + Lokalizacja ;
    }
    public UrzadzenieMagazyn(){}

//    public UrzadzenieMagazyn(String model, String serialNumber,
//                             String MAC, pl.tvknet.stanurzadzen.Stan stan, String lokalizacja, Button button) {
//        this.Model = new SimpleStringProperty(model);
//        this.SerialNumber = new SimpleStringProperty(serialNumber);
//        this.MAC = new SimpleStringProperty(MAC);
//        this.Stan = stan;
//        this.Lokalizacja = new SimpleStringProperty(lokalizacja);
//        this.button=button;
//    }
    public UrzadzenieMagazyn(String model, String serialNumber,
                             String MAC, pl.tvknet.stanurzadzen.Stan stan, String lokalizacja) {
        this.Model = new SimpleStringProperty(model);
        this.SerialNumber = new SimpleStringProperty(serialNumber);
        this.MAC = new SimpleStringProperty(MAC);
        this.Stan = stan;
        this.Lokalizacja = new SimpleStringProperty(lokalizacja);

    }


    public String getModel() {
        return Model.get();
    }

    public StringProperty modelProperty() {
        return Model;
    }

    public void setModel(String model) {
        this.Model.set(model);
    }

    public String getSerialNumber() {
        return SerialNumber.get();
    }

    public StringProperty serialNumberProperty() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.SerialNumber.set(serialNumber);
    }

    public String getMAC() {
        return MAC.get();
    }

    public StringProperty MACProperty() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC.set(MAC);
    }

    public pl.tvknet.stanurzadzen.Stan getStan() {
        return Stan;
    }

    public void setStan(pl.tvknet.stanurzadzen.Stan stan) {
        Stan = stan;
    }

    public String getLokalizacja() {
        return Lokalizacja.get();
    }

    public StringProperty lokalizacjaProperty() {
        return Lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.Lokalizacja.set(lokalizacja);
    }
}
enum Stan{
    Magazyn , Wydane
}

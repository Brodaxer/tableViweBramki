package pl.tvknet.stanurzadzen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StanUrzadzenController {
    ObservableList<UrzadzenieMagazyn> urzadzenieMagazyns = FXCollections.observableArrayList();
    ObservableList<String> comboboxStan = FXCollections.observableArrayList("Wydane","Magazyn");
    @FXML
    private TableColumn<UrzadzenieMagazyn, String> MACColumn;

    @FXML
    private TableColumn<UrzadzenieMagazyn, String> SNColumn;


    @FXML
    private TableColumn<UrzadzenieMagazyn, String> lokalizacjaColumn;

    @FXML
    private TableColumn<UrzadzenieMagazyn, String> modelColumn;

    @FXML
    private TableColumn<UrzadzenieMagazyn, Stan> stanColumn ;

    @FXML
    private TableView<UrzadzenieMagazyn> tabelaUrzadzen ;
    @FXML
    private TextField nowyLokalizacja;

    @FXML
    private TextField nowyMAC;

    @FXML
    private TextField nowyModel;

    @FXML
    private TextField nowySN;

    @FXML
    private ComboBox<String> nowyStan ;
    @FXML
    private Button NoweUrzadzenie;



    public void dodajNoweUrzadzenie(){
        NoweUrzadzenie.setOnAction(event -> {
            String model = nowyModel.getText();
            String SN = nowySN.getText( );
            String MAC = nowyMAC.getText();
            String stan = nowyStan.getValue();
            System.out.println(stan);
            String lokalizacja = nowyLokalizacja.getText();
            urzadzenieMagazyns.add(new UrzadzenieMagazyn(model,SN,MAC,Stan.valueOf(stan),lokalizacja));
            try (
                PreparedStatement prep = KomunikacjaSQL.getConnection().prepareStatement("INSERT INTO `barmki`(`Model`, `SN`, `MAC`, `Stan`, `Lokalizacja`) VALUES (?,?,?,?,?)")
            ) {
                prep.setString(1, model);
                prep.setString(2, SN);
                prep.setString(3, MAC);
                prep.setString(4, stan);
                prep.setString(5, lokalizacja);
                prep.execute();
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        fill();
    }


    public void fill(){
        urzadzenieMagazyns.clear();

        try{
            query = "SELECT * FROM barmki";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                urzadzenieMagazyns.add(new UrzadzenieMagazyn(resultSet.getString("Model"),
                        resultSet.getString("SN"),
                        resultSet.getString("MAC"),
                        Stan.valueOf(resultSet.getString("Stan")),
                        resultSet.getString("Lokalizacja")));
                tabelaUrzadzen.setItems(urzadzenieMagazyns);
            }
            preparedStatement.close();
            resultSet.close();
        }catch (SQLException ex){
            System.err.println(ex.getErrorCode());
        }

    }

    public void intializeCells (){
        tabelaUrzadzen.setEditable(true);
        tabelaUrzadzen.getSelectionModel().setCellSelectionEnabled(true);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("Model"));
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setOnEditCommit(event -> {
            UrzadzenieMagazyn mag = event.getRowValue();
            String old = mag.getModel();
            mag.setModel(event.getNewValue());
            PreparedStatement prep = null;
            try {
                prep = KomunikacjaSQL.getConnection().prepareStatement("UPDATE barmki SET Model = '"+event.getNewValue()+"' WHERE Model = '"+old+"'");
                prep.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        SNColumn.setCellValueFactory(new PropertyValueFactory<>("SerialNumber"));
        SNColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        SNColumn.setOnEditCommit(event ->{
            UrzadzenieMagazyn mag = event.getRowValue();
            String old = mag.getSerialNumber();
            mag.setSerialNumber(event.getNewValue());
            PreparedStatement prep = null;
            try {
                prep = KomunikacjaSQL.getConnection().prepareStatement("UPDATE barmki SET SN = '"+event.getNewValue()+"' WHERE SN = '"+old+"'");
                prep.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        MACColumn.setCellValueFactory(new PropertyValueFactory<>("MAC"));
        MACColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        MACColumn.setOnEditCommit(event ->{
                    UrzadzenieMagazyn mag = event.getRowValue();
                    String old = mag.getMAC();
                    mag.setMAC(event.getNewValue());
                    PreparedStatement prep = null;
                    try {
                        prep = KomunikacjaSQL.getConnection().prepareStatement("UPDATE barmki SET MAC = '"+event.getNewValue()+"' WHERE MAC = '"+old+"'");
                        prep.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }});
        stanColumn.setCellValueFactory(new PropertyValueFactory<>("Stan"));
        stanColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Stan.Magazyn,Stan.Wydane));
        stanColumn.setOnEditCommit(event ->{
            UrzadzenieMagazyn mag = event.getRowValue();
            String old = mag.getSerialNumber();
            mag.setStan(event.getNewValue());
            PreparedStatement prep = null;
            try {
                prep = KomunikacjaSQL.getConnection().prepareStatement("UPDATE barmki SET Stan = '"+event.getNewValue()+"' WHERE SN = '"+old+"'");
                prep.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        lokalizacjaColumn.setCellValueFactory(new PropertyValueFactory<>("Lokalizacja"));
        lokalizacjaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lokalizacjaColumn.setOnEditCommit(event ->{
            UrzadzenieMagazyn mag = event.getRowValue();
            String old = mag.getLokalizacja();
            mag.setLokalizacja(event.getNewValue());
            PreparedStatement prep = null;
            try {
                prep = KomunikacjaSQL.getConnection().prepareStatement("UPDATE barmki SET Lokalizacja = '"+event.getNewValue()+"' WHERE Lokalizacja = '"+old+"'");
                prep.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }});
        tabelaUrzadzen.setItems(urzadzenieMagazyns);


    }
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    UrzadzenieMagazyn urzadzenieMagazyn = null;

    public void initialize() throws SQLException {
        nowyStan.setItems(comboboxStan);
        connection = KomunikacjaSQL.getConnection();
        intializeCells();

        fill();



    }


}

module pl.tvknet.stanurzadzen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pl.tvknet.stanurzadzen to javafx.fxml;
    exports pl.tvknet.stanurzadzen;
}
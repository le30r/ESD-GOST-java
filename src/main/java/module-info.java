module com.example.electronicdigitalsignature {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.electronicdigitalsignature to javafx.fxml;
    exports com.example.electronicdigitalsignature;
    exports com.example.electronicdigitalsignature.utils;
    opens com.example.electronicdigitalsignature.utils to javafx.fxml;
    exports com.example.electronicdigitalsignature.controllers;
    opens com.example.electronicdigitalsignature.controllers to javafx.fxml;
}
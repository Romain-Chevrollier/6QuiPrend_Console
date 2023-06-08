module com.example._6quiprend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires java.desktop;

    opens com.example._6quiprend to javafx.fxml;
    exports com.example._6quiprend;
}
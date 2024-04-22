module org.kevin.desktopappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires unirest.java.core;
    requires unirest.modules.gson;
    requires com.google.gson;


    opens org.kevin.desktopappfx to javafx.fxml;
    exports org.kevin.desktopappfx;
}
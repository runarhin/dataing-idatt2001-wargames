module wargames {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens edu.ntnu.idatt2001.runarin.frontend.controllers to javafx.fxml;
    exports edu.ntnu.idatt2001.runarin.frontend;
    exports edu.ntnu.idatt2001.runarin.frontend.unused;
    exports edu.ntnu.idatt2001.runarin.frontend.model;
}
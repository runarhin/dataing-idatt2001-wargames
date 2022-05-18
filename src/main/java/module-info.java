module wargames {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens edu.ntnu.idatt2001.runarin.wargames.frontend.controllers to javafx.fxml;
    exports edu.ntnu.idatt2001.runarin.wargames.frontend;
    exports edu.ntnu.idatt2001.runarin.wargames.frontend.unused;
    exports edu.ntnu.idatt2001.runarin.wargames.frontend.model;
}
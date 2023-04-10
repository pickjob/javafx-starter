module javafx.starter {
    // kotlin
    requires kotlin.stdlib;

    // JavaFx
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // CssFx
    requires fr.brouillard.oss.cssfx;

    // 日志
    requires org.slf4j;
    requires io.github.microutils.kotlinlogging;

    opens javafx.starter to javafx.graphics, javafx.fxml;
    opens javafx.starter.application to javafx.graphics, javafx.fxml;
    opens javafx.starter.showcase to javafx.graphics, javafx.fxml;
}

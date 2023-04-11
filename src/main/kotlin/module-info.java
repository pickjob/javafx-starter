module javafx.starter {
    // kotlin
    requires kotlin.stdlib;

    // JavaFx
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // SPI
    provides javafx.starter.transform.ITransform with
            javafx.starter.transform.impl.Base64ITransformImpl,
            javafx.starter.transform.impl.Base64UrlITransformImpl,
            javafx.starter.transform.impl.UrlITransformImpl,
            javafx.starter.transform.impl.JsonPrettyITransformImpl;

    uses javafx.starter.transform.ITransform;

    // CssFx
    requires fr.brouillard.oss.cssfx;

    // 日志
    requires org.slf4j;
    requires io.github.microutils.kotlinlogging;
    // JSON
    requires com.google.gson;
    // coroutines
    requires kotlinx.coroutines.core;
    requires kotlinx.coroutines.javafx;

    opens javafx.starter to javafx.graphics, javafx.fxml;
    opens javafx.starter.application to javafx.graphics, javafx.fxml;
    opens javafx.starter.transform to javafx.graphics, javafx.fxml;
    opens javafx.starter.showcase to javafx.graphics, javafx.fxml;
}

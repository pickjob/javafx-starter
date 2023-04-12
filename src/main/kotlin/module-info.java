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
    // ikonli
    uses org.kordamp.ikonli.IkonProvider;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;

    // ikonli 图标
    requires org.kordamp.ikonli.antdesignicons;
    requires org.kordamp.ikonli.bootstrapicons;
    requires org.kordamp.ikonli.boxicons;
    requires org.kordamp.ikonli.bpmn;
    requires org.kordamp.ikonli.captainicon;
    requires org.kordamp.ikonli.carbonicons;
    requires org.kordamp.ikonli.codicons;
    requires org.kordamp.ikonli.coreui;
    requires org.kordamp.ikonli.dashicons;
    requires org.kordamp.ikonli.devicons;
    requires org.kordamp.ikonli.elusive;
    requires org.kordamp.ikonli.entypo;
    requires org.kordamp.ikonli.evaicons;
    requires org.kordamp.ikonli.feather;
    requires org.kordamp.ikonli.fileicons;
    requires org.kordamp.ikonli.fluentui;
    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.fontelico;
    requires org.kordamp.ikonli.foundation;
    requires org.kordamp.ikonli.hawcons;
    requires org.kordamp.ikonli.icomoon;
    requires org.kordamp.ikonli.ionicons;
    requires org.kordamp.ikonli.ionicons4;
    requires org.kordamp.ikonli.jam;
    requires org.kordamp.ikonli.ligaturesymbols;
    requires org.kordamp.ikonli.lineawesome;
    requires org.kordamp.ikonli.linecons;
    requires org.kordamp.ikonli.maki;
    requires org.kordamp.ikonli.maki2;
    requires org.kordamp.ikonli.mapicons;
    requires org.kordamp.ikonli.material;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.ikonli.materialdesign;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.kordamp.ikonli.medicons;
    requires org.kordamp.ikonli.metrizeicons;
    requires org.kordamp.ikonli.microns;
    requires org.kordamp.ikonli.ociicons;
    requires org.kordamp.ikonli.octicons;
    requires org.kordamp.ikonli.openiconic;
    requires org.kordamp.ikonli.paymentfont;
    requires org.kordamp.ikonli.prestashopicons;
    requires org.kordamp.ikonli.remixicon;
    requires org.kordamp.ikonli.runestroicons;
    requires org.kordamp.ikonli.simpleicons;
    requires org.kordamp.ikonli.simplelineicons;
    requires org.kordamp.ikonli.subway;
    requires org.kordamp.ikonli.themify;
    requires org.kordamp.ikonli.typicons;
    requires org.kordamp.ikonli.unicons;
    requires org.kordamp.ikonli.weathericons;
    requires org.kordamp.ikonli.websymbols;
    requires org.kordamp.ikonli.whhg;
    requires org.kordamp.ikonli.win10;
    requires org.kordamp.ikonli.zondicons;

    opens javafx.starter to javafx.graphics, javafx.fxml;
    opens javafx.starter.application to javafx.graphics, javafx.fxml;
    opens javafx.starter.transform to javafx.graphics, javafx.fxml;
    opens javafx.starter.ikonli to javafx.graphics, javafx.fxml;
    opens javafx.starter.showcase to javafx.graphics, javafx.fxml;
}

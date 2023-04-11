package javafx.starter.transform.impl

import javafx.starter.common.ERROR_RESULT
import javafx.starter.transform.ITransform
import mu.KotlinLogging
import org.slf4j.Logger
import java.net.URLDecoder
import java.net.URLEncoder


class UrlITransformImpl : ITransform {

    override fun transform(src: String): String {
        try {
            return URLEncoder.encode(src, Charsets.UTF_8)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
        return ERROR_RESULT
    }

    override fun reverse(src: String): String {
        try {
            return URLDecoder.decode(src, Charsets.UTF_8)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
        return ERROR_RESULT
    }

    override val supportReverse = true

    private val logger: Logger = KotlinLogging.logger {}
}

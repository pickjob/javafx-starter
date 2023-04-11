package javafx.starter.transform.impl

import javafx.starter.transform.ITransform
import javafx.starter.common.ERROR_RESULT
import mu.KotlinLogging
import org.slf4j.Logger
import java.lang.Exception
import java.util.*
import java.util.Base64.Decoder
import java.util.Base64.Encoder

abstract class AbstractBase64ITransformImpl constructor(private val type: String): ITransform {
    private val encoder: Encoder = when(type) {
        "url" -> Base64.getUrlEncoder()
        else -> Base64.getEncoder()
    }
    private val decoder: Decoder = when(type) {
        "url" -> Base64.getUrlDecoder()
        else -> Base64.getDecoder()
    }

    override fun transform(src: String): String {
        try {
            return encoder.encodeToString(src.encodeToByteArray())
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
        return ERROR_RESULT
    }

    override fun reverse(src: String): String {
        try {
            return decoder.decode(src.encodeToByteArray()).decodeToString()
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
        return ERROR_RESULT
    }

    override val supportReverse = true

    private val logger: Logger = KotlinLogging.logger {}
}

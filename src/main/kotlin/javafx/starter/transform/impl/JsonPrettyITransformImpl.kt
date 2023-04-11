package javafx.starter.transform.impl

import com.google.gson.GsonBuilder
import javafx.starter.common.ERROR_RESULT
import javafx.starter.transform.ITransform
import mu.KotlinLogging
import org.slf4j.Logger


class JsonPrettyITransformImpl: ITransform {
    override fun transform(src: String): String {
        try {
            val obj = prettyGson.fromJson(src, Map::class.java)
            return prettyGson.toJson(obj)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
        return ERROR_RESULT
    }

    override fun reverse(src: String): String {
        try {
            val obj = minifyGson.fromJson(src, Map::class.java)
            return minifyGson.toJson(obj)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
        return ERROR_RESULT
    }

    override val supportReverse = true

    private val prettyGson = GsonBuilder().setPrettyPrinting().create()
    private val minifyGson = GsonBuilder().create()

    private val logger: Logger = KotlinLogging.logger {}
}

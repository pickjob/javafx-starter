package javafx.starter.transform

/**
 * @author: pickjob@126.com
 * @date: 2023-04-11
 */
interface ITransform {

    fun transform(src: String): String

    fun reverse(src: String): String {
        TODO("Not Support Reverse")
    }

    val supportReverse: Boolean;
}

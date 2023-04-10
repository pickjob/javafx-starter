package javafx.starter.application

/**
 * @author: pickjob@126.com
 * @date: 2023-03-20
 */
data class NavigatorRecord(val name: String, val targetPath: String, val children: List<NavigatorRecord>) {
    override fun toString(): String {
        return name
    }
}

package javafx.starter.application

import org.kordamp.ikonli.javafx.FontIcon

/**
 * @author: pickjob@126.com
 * @date: 2023-03-20
 */
data class NavigatorRecord(val name: String, val icon: FontIcon, val targetPath: String, val children: List<NavigatorRecord>) {
    override fun toString(): String {
        return name
    }
}

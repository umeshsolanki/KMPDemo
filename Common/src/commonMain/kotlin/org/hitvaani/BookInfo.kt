package org.hitvaani

import com.devuss.common.BaseApiEntity
import kotlinx.serialization.Serializable

enum class Theme(
    val text: String = "#000000", val background: String = "#000000", val image: String? = null
) {
    YELLOW, VRINDAVAN(background = "#55ff55"), DARK
}

data class ThemeConfig(
    val name: Theme = Theme.YELLOW
)

data class AppConfig(
    val nightMode: Boolean = false, var themeConfig: ThemeConfig = ThemeConfig()
)

data class ChapterDetail(
    val name: String = "",
    val padSankhya: Int = 1,
)

data class BookInfo(
    val id: String = "",
    val title: String = "",
    val subTitle: String = "",
    val chapters: List<ChapterDetail> = emptyList(),
) : BaseApiEntity<String>

@Serializable
data class VersesInfo(
    val verseNumber: String = "",
    val verseText: String = "",
)
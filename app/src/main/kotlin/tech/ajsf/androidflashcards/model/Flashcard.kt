package tech.ajsf.androidflashcards.model

import android.arch.persistence.room.Ignore
import java.net.URL

const val defaultAnswer =
    "An answer has not been provided for this question yet. To provide an answer, click the edit button."

val defaultLinks = listOf(
    URL("https://github.com/MindorksOpenSource/android-interview-questions"),
    URL("https://stackoverflow.com")
)

data class Flashcard(
    val question: String,
    var answer: String = defaultAnswer,
    val category: String
) {

    init {
        if (answer == "") answer = defaultAnswer
    }

    @Ignore
    var links: List<URL> = defaultLinks
}

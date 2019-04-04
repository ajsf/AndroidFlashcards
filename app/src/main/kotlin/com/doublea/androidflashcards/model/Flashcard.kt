package com.doublea.androidflashcards.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.net.URL

const val defaultAnswer = "An answer has not been provided for this question yet. To provide an answer, click the edit button."

val defaultLinks = listOf(
        URL("https://github.com/MindorksOpenSource/android-interview-questions"),
        URL("https://stackoverflow.com"))

@Entity
data class Flashcard(@PrimaryKey val question: String, var answer: String = defaultAnswer) {

    init {
        if (answer == "") answer = defaultAnswer
    }

    @Ignore var links: List<URL> = defaultLinks
}

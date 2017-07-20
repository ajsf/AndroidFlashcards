package com.doublea.androidflashcards.model

import java.net.URL

val defaultAnswer = "An answer has not been provided for this question yet. To provide an answer, click the edit button."

val defaultLinks = listOf<URL>(
        URL("https://github.com/MindorksOpenSource/android-interview-questions"),
        URL("https://stackoverflow.com"))

data class Flashcard(
        val question: String,
        var answer: String = defaultAnswer,
        val links: List<URL> = defaultLinks)
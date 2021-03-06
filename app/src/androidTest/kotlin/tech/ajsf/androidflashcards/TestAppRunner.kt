package tech.ajsf.androidflashcards

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class TestAppRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}
package com.example.quiz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getBooleanExtra(EXTRA_MESSAGE, false)
        findViewById<TextView>(R.id.textView2).apply {
            text = if (message) "Prawda" else "Fałsz"
        }
    }

    fun onCheckQuestionClick(view: View) {
        var message = intent.getStringExtra(EXTRA_QUESTION)
        var uri = Uri.parse("http://www.google.com/search?q=" + message)
        var browser_intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null)
        {
            startActivity(browser_intent)
        }
    }
}
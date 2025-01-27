package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layout : ConstraintLayout = findViewById(R.id.main)
        val firstButton = findViewById<Button>(R.id.premierBouton)
        val secondButton = findViewById<Button>(R.id.secondBouton)
        val thirdButton = findViewById<Button>(R.id.troisiemeBouton)
        thirdButton.isEnabled = false
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                thirdButton.isEnabled = true
                true
            } else {
                false
            }
        }

        firstButton.setOnClickListener {
            val selectedText = editText.text
            textView.text = selectedText
        }

        secondButton.setOnClickListener {
            val secondTextView : TextView = TextView(this)
            val selectedText = editText.text
            secondTextView.text = selectedText
            layout.addView(secondTextView)
        }

        thirdButton.setOnClickListener {
            val text_to_display = editText.text.toString()
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putExtra(EXTRA_TEXT, text_to_display)
            startActivity(intent)
        }
    }
}

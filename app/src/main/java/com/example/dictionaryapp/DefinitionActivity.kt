package com.example.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_definition.*

class DefinitionActivity : AppCompatActivity() {

    private val  key = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)

        definition_text_view.text = intent.getStringExtra(key)

        back_arrow.setOnClickListener{finish()}
    }
}
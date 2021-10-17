package com.example.dictionaryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val key = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)

        btn_find.setOnClickListener {

            val url = getUrl()
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response: String ->
                    try {
                        extractDefinitionFromJson(response)
                    }catch (exception : Exception){
                        exception.printStackTrace()
                    }
                },
                { error: VolleyError ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }

            )

            queue.add(stringRequest)
        }
    }

    private fun getUrl(): String {
        val word = word_edit_text.text
        val apiKey = "ea03cf41-c33b-456f-9c87-82dc11004184"

        return "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
    }

    private fun extractDefinitionFromJson(response: String) {

        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra(key, firstShortDefinition.toString())
        startActivity(intent)
    }
}
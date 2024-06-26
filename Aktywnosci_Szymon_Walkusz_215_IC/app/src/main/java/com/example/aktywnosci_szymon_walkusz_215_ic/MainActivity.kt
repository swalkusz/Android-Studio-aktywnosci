package com.example.aktywnosci_szymon_walkusz_215_ic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var sortButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sortButton = findViewById(R.id.sortedButton)
        val editText = findViewById<EditText>(R.id.inputField)
        val radioGroup = findViewById<RadioGroup>(R.id.sortMethodRadioGroup)

//        function to handle on a click
        sortButton.setOnClickListener {
            val inputText = editText.text.toString()
            val selectedSortAlgorithm = when (radioGroup.checkedRadioButtonId) {
                R.id.sort1RadioButton -> SortAlgorithm.BUBBLE_SORT.toString()
                R.id.sort2RadioButton -> SortAlgorithm.SELECTION_SORT.toString()
                else -> SortAlgorithm.BUBBLE_SORT.toString()
            }
            intent = Intent(this, AktywnoscDruga::class.java)
            intent.putExtra("algorithm", selectedSortAlgorithm)
            intent.putExtra("inputText", inputText)
            startActivity(intent)
        }
    }

}
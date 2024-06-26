package com.example.aktywnosci_szymon_walkusz_215_ic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AktywnoscDruga : AppCompatActivity() {

    private lateinit var homePageBtn: Button

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.aktywnosc_druga)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        homePageBtn = findViewById(R.id.backToHomeButton)
        val extras = intent.extras ?: return
        val algorithm = extras.getString("algorithm")
        val inputText = extras.getString("inputText")


        val oldChain: TextView = findViewById(R.id.oldChain)
        oldChain.text = inputText
        val sortedChain: TextView = findViewById(R.id.sortedChain)

        val sortedNumbers = when (algorithm) {
            SortAlgorithm.BUBBLE_SORT.toString() -> inputText?.let { bubbleSort(it) }
            SortAlgorithm.SELECTION_SORT.toString() -> inputText?.let { selectionSort(it) }
            else -> "nie dziala: $algorithm"
        }
        sortedChain.text = "Posortowano algorytmem $algorithm .\n $sortedNumbers"

        homePageBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun bubbleSort(text: String): String {
        val numbers =
            text.split("\\s+".toRegex()).filter { it.isNotEmpty() }.map { it.trim().toInt() }
                .toIntArray()
        for (i in numbers.indices) {
            for (j in 0 until numbers.size - i - 1) {
                if (numbers[j] > numbers[j + 1]) {
                    val temp = numbers[j]
                    numbers[j] = numbers[j + 1]
                    numbers[j + 1] = temp
                }
            }
        }
        return numbers.joinToString(" ")
    }

    private fun selectionSort(text: String): String {
        val numbers =
            text.split("\\s+".toRegex()).filter { it.isNotEmpty() }.map { it.trim().toInt() }
                .toIntArray()
        for (i in numbers.indices) {
            var minIdx = i
            for (j in i + 1 until numbers.size) {
                if (numbers[j] < numbers[minIdx]) {
                    minIdx = j
                }
            }
            val temp = numbers[minIdx]
            numbers[minIdx] = numbers[i]
            numbers[i] = temp
        }
        return numbers.joinToString(" ")
    }
}
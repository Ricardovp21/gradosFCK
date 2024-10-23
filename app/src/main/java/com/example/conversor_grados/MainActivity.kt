package com.example.conversor_grados.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.conversor_grados.Celsius
import com.example.conversor_grados.Faren
import com.example.conversor_grados.Kelvin
import com.example.conversor_grados.R

class MainActivity : AppCompatActivity() {

    private lateinit var editValue: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var buttonConvert: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Asegúrate de llamar a setContentView.

        // Inicialización de vistas.
        editValue = findViewById(R.id.edit_value)
        spinnerFrom = findViewById(R.id.spinner_from)
        spinnerTo = findViewById(R.id.spinner_to)
        buttonConvert = findViewById(R.id.button_convert)
        textResult = findViewById(R.id.text_result)

        // Adaptador para los Spinners.
        val units = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        // Evento onClick del botón.
        buttonConvert.setOnClickListener {
            val inputValue = editValue.text.toString()
            if (inputValue.isNotEmpty()) {
                val value = inputValue.toDouble()
                val fromUnit = spinnerFrom.selectedItem.toString()
                val toUnit = spinnerTo.selectedItem.toString()

                val result = convertUsingClasses(value, fromUnit, toUnit)
                textResult.text = "Resultado: $result $toUnit"
            } else {
                textResult.text = "Ingresa tu valor"
            }
        }
    }

    // Método para realizar la conversión usando clases.
    private fun convertUsingClasses(value: Double, fromUnit: String, toUnit: String): Double {
        return when (fromUnit) {
            "Celsius" -> {
                val celsius = Celsius(value)
                when (toUnit) {
                    "Fahrenheit" -> Faren.parse(celsius).value
                    "Kelvin" -> Kelvin.parse(celsius).value
                    else -> celsius.value
                }
            }
            "Fahrenheit" -> {
                val fahrenheit = Faren(value)
                when (toUnit) {
                    "Celsius" -> Celsius.parse(fahrenheit).value
                    "Kelvin" -> Kelvin.parse(fahrenheit).value
                    else -> fahrenheit.value
                }
            }
            "Kelvin" -> {
                val kelvin = Kelvin(value)
                when (toUnit) {
                    "Celsius" -> Celsius.parse(kelvin).value
                    "Fahrenheit" -> Faren.parse(kelvin).value
                    else -> kelvin.value
                }
            }
            else -> value
        }
    }
}

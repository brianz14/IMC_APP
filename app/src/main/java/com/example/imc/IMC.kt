package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMC : AppCompatActivity() {

    //declaramos para acceder despues

    private var esSeleccionadaHombre: Boolean = true
    private var esSeleccionadaMujer: Boolean = false

    private lateinit var btnCalcular: Button

    private lateinit var vistaHombre: CardView
    private lateinit var vistaMujer: CardView

    private lateinit var tvAltura: TextView
    private lateinit var rsAltura: RangeSlider

    //peso
    private lateinit var btnRestarPeso: FloatingActionButton
    private lateinit var btnSumarPeso: FloatingActionButton
    private lateinit var tvPesoEntero: TextView
    private var pesoActual: Int = 60


    //edad
    private lateinit var btnRestarEdad: FloatingActionButton
    private lateinit var btnSumarEdad: FloatingActionButton
    private lateinit var tvEdadEntero: TextView
    private var edadActual: Int = 13


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        iniciarComponente()
        iniciarEventoListener()
        iniciarInterfazUsuario()
    }


    private fun iniciarComponente() {
        vistaHombre = findViewById(R.id.vistaHombre)
        vistaMujer = findViewById(R.id.vistaMujer)
        tvAltura = findViewById(R.id.tvAltura)
        rsAltura = findViewById(R.id.rsAltura)

        //peso
        btnRestarPeso = findViewById(R.id.btnRestarPeso)
        btnSumarPeso = findViewById(R.id.btnSumarPeso)
        tvPesoEntero = findViewById(R.id.tvPeso)

        //edad
        btnRestarEdad = findViewById(R.id.btnRestarEdad)
        btnSumarEdad = findViewById(R.id.btnSumarEdad)
        tvEdadEntero = findViewById(R.id.tvEdad)

        //btn
        btnCalcular = findViewById(R.id.btnCalcular)

    }

    private fun iniciarEventoListener() {
        vistaHombre.setOnClickListener {
            cambiarColor()
            modificarGeneroColor()
        }
        vistaHombre.setOnClickListener {
            cambiarColor()
            modificarGeneroColor()
        }
        rsAltura.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val res = df.format(value)
            tvAltura.text = "$res"
        }

        btnRestarPeso.setOnClickListener {

            modificarPeso(-1)
        }

        btnSumarPeso.setOnClickListener {

            modificarPeso(+1)
        }

        btnRestarEdad.setOnClickListener {

            modificarEdad(-1)

        }

        btnSumarEdad.setOnClickListener {
            modificarEdad(+1)
        }

        btnCalcular.setOnClickListener {
            Calcular()
        }


    }

    private fun Calcular() {
        var altura: Double? = tvAltura.text.toString().toDouble();
        var peso: Double? = tvPesoEntero.text.toString().toDouble();

        altura = (altura?.div(100));
        altura = (altura!! * altura!!)
        var resultado: Double = peso!!.div(altura!!)
        var texto:String?="";
        when(resultado){
            in Double.MIN_VALUE..17.9->{texto="Peso por debajo de la normalidad"}
            in 18.0..24.9->{texto="Peso adecuado"}
            in 25.0..29.9->{texto="Sobrepeso"}
            in 30.0..Double.MAX_VALUE->{texto="Obesidad"}
        }
        Toast.makeText(this, " $texto", Toast.LENGTH_SHORT)
            .show()
    }

    private fun modificarEdad(num: Int) {
        var num = tvEdadEntero.text.toString().toInt() + (num)
        if (num >= 12) {
            edadActual = num
            tvEdadEntero.text = edadActual.toString()
        }

    }

    private fun modificarPeso(num: Int) {
        var num = tvPesoEntero.text.toString().toInt() + (num)
        if (num >= 60) {
            pesoActual = num
            tvPesoEntero.text = pesoActual.toString()
        }

    }


    private fun cambiarColor() {
        esSeleccionadaMujer = !esSeleccionadaMujer
        esSeleccionadaHombre = !esSeleccionadaHombre

    }

    private fun modificarGeneroColor() {

        vistaHombre.setCardBackgroundColor(obtenerFondoColor(esSeleccionadaHombre))
        vistaMujer.setCardBackgroundColor(obtenerFondoColor(esSeleccionadaMujer))

    }

    private fun obtenerFondoColor(esVistaSeleccionada: Boolean): Int {

        val referenciaColor = if (esVistaSeleccionada) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, referenciaColor)

    }

    private fun iniciarInterfazUsuario() {

        modificarGeneroColor()

    }
}
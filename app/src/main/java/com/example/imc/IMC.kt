package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private var alturaActual:Int=45

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

    //calcular


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

        var altura:Double= tvAltura.text.toString().toDouble();
        var peso:Double= tvPesoEntero.text.toString().toDouble();

        altura = altura.div(100)

        val resultado: Double = peso/(altura*altura)
        val resultadoFormateado = String.format("%.2f", resultado)

        var texto=""
        var descripcion=""
        when(resultado){
            in Double.MIN_VALUE..17.9->{texto="Peso inferior"; descripcion="El peso esta debajo de lo mormal para tu peso y altura"}
            in 18.0..24.9->{texto="Peso adecuado"; descripcion="El peso es adecuado para tu peso y altura"}
            in 25.0..29.9->{texto="Sobrepeso"; descripcion="El peso esta ligeramente encima de lo mormal para tu peso y altura"}
            in 30.0..Double.MAX_VALUE->{texto="Obesidad" ; descripcion="El peso esta muy por encima de lo mormal para tu peso y altura"}
        }



        val intent= Intent(this,ResultadoActivity::class.java)
        intent.putExtra("var_numero",resultadoFormateado)
        intent.putExtra("var_texto",texto)
        intent.putExtra("var_descripcion",descripcion)
        startActivity(intent)

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
        if (num >= 45) {
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
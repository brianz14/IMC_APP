package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {

    //label
    private lateinit var lblResuladoCorto: TextView
    private lateinit var lblNumeroCalculado: TextView
    private lateinit var lblDescripcion: TextView

    //botones
    private lateinit var btnRecalcular: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        iniciarComponente()
        iniciarEventoListener()
        iniciarInterfazUsuario()
    }

    private fun iniciarInterfazUsuario() {
        modificarContenido();
    }

    private fun modificarContenido() {
        val resultado:String? =intent.extras?.getString("var_numero");
        val titulo=intent.extras?.getString("var_texto");
        val descripcion=intent.extras?.getString("var_descripcion");

        lblDescripcion.text=descripcion
        lblNumeroCalculado.text=resultado
        lblResuladoCorto.text=titulo
    }

    private fun iniciarComponente() {
        //label
        lblResuladoCorto = findViewById(R.id.lblResuladoCorto)
        lblNumeroCalculado = findViewById(R.id.lblNumeroCalculado)
        lblDescripcion = findViewById(R.id.lblDescripcion)

        //boton
        btnRecalcular=findViewById(R.id.btnReCalcular)

    }

    private fun iniciarEventoListener() {
        btnRecalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val intento= Intent(this,IMC::class.java)
        startActivity(intento)

    }



}
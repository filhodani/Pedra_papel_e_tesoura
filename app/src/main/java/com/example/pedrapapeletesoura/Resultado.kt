package com.example.pedrapapeletesoura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class Resultado : AppCompatActivity() {

    private val escolhas = IntArray(3)
    private val resultados = IntArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        var jogadas = getPreferences(MODE_PRIVATE).getInt("JOGADAS", 0)
        var pontos = getPreferences(MODE_PRIVATE).getInt("PONTOS", 0)

        val escolha = intent.getIntExtra("ESCOLHA", 0)

        escolhas[0] = R.drawable.pedra1
        escolhas[1] = R.drawable.papel1
        escolhas[2] = R.drawable.tesoura1

        val imgEscolha = findViewById<ImageView>(R.id.imgEscolha)
        imgEscolha.setImageResource(escolhas[escolha])

        resultados[0] = R.drawable.pedra
        resultados[1] = R.drawable.papel
        resultados[2] = R.drawable.tesoura

        val resultado = Random.nextInt(0, 3)

        val imgResultado = findViewById<ImageView>(R.id.imgResultado)
        imgResultado.setImageResource(resultados[resultado])

        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        if(escolha == resultado)
            txtResultado.text = getString(R.string.tied)
        else{
            if((escolha == 0 && resultado == 2) || (escolha == 1 && resultado == 0) || (escolha == 2 && resultado == 1)) {
                txtResultado.text = getString(R.string.victory)
                pontos++
            }
            else
                txtResultado.text = getString(R.string.defeat)
        }

        title = "${getString(R.string.score)}: $pontos / ${++jogadas}"

        getPreferences(MODE_PRIVATE).edit().putInt("JOGADAS", jogadas).apply()
        getPreferences(MODE_PRIVATE).edit().putInt("PONTOS", pontos).apply()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.opcoes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnuNovoJogo -> novoJogo()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    fun novoJogo(){
        getPreferences(MODE_PRIVATE).edit().putInt("JOGADAS", 0).apply()
        getPreferences(MODE_PRIVATE).edit().putInt("PONTOS", 0).apply()
        finish()
    }
}
package com.example.pedrapapeletesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun play(view: View){
        val escolha = view.tag.toString().toInt()

        val i = Intent(this, Resultado::class.java)
        i.putExtra("ESCOLHA", escolha)
        startActivity(i)
    }
}
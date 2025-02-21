package mx.itson.cheems2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var gameOverCard = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        start()
        //Log.d("el valor de la carta","La carta perdedora es ${gameOverCard}".toString())
    }

    fun start(){
        for(i in 1..6){
           val btnCard = findViewById<View>(
               resources.getIdentifier("carta$i", "id", this.packageName)
           ) as ImageButton
            btnCard.setOnClickListener(this)
            btnCard.setBackgroundResource(R.drawable.backtcg)
        }
        gameOverCard =(1..6).random()
        Log.d("el valor de la carta","La carta perdedora es ${gameOverCard}".toString())

    }
    @SuppressLint("ServiceCast")
    fun flip(card : Int){
        if(card == gameOverCard){
            //perdedor
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                val vibrattorAdmin =applicationContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as Vibrator
                val vibrator =vibrattorAdmin
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
            }else{
            val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(1000)
            }
            Toast.makeText(this, getString(R.string.text_game_over), Toast.LENGTH_SHORT).show();

            for(i in 1..6){
                val btnCard = findViewById<View>(
                    resources.getIdentifier("carta$i", "id", this.packageName)
                ) as ImageButton
                if(i == card){
                    btnCard.setBackgroundResource(R.drawable.emperorn)
                }else{
                    btnCard.setBackgroundResource(R.drawable.emperoraa)
                }
            }
        }else{//cambios
            //Sigue
            val btnCard = findViewById<View>(
                resources.getIdentifier("carta$card", "id", this.packageName)
            ) as ImageButton
            btnCard.setBackgroundResource(R.drawable.emperoraa)
        }
    }

    override fun onClick(v: View) {
     when(v.id){
         R.id.carta1 ->{flip(1)}
         R.id.carta2 ->{flip(2)}
         R.id.carta3 ->{flip(3)}
         R.id.carta4 ->{flip(4)}
         R.id.carta5 ->{flip(5)}
         R.id.carta6 ->{flip(6)}
     }
    }
}
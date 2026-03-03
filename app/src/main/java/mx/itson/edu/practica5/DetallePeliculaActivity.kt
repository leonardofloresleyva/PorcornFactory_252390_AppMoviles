package mx.itson.edu.practica5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallePeliculaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)

        val peliculaImage: ImageView = findViewById(R.id.image_movie_descrition)
        val nombrePelicula: TextView = findViewById(R.id.movie_title_description)
        val peliculaDesc: TextView = findViewById(R.id.movie_description)
        val seatsLeft: TextView = findViewById(R.id.seat_left)

        val bundle= intent.extras
        if (bundle != null){
            peliculaImage.setImageResource(bundle.getInt("header"))
            nombrePelicula.text = bundle.getString("titulo")
            peliculaDesc.text = bundle.getString("sinopsis")
            val seats= bundle.getInt("numberSeats")
            seatsLeft.text = "$seats seats available."
        }

        val buttonTickets: Button = findViewById(R.id.btn_buy_tickets)
        buttonTickets.setOnClickListener {
            val intent = Intent(this, SeatSelectionActivity::class.java)
            intent.putExtra("name", bundle?.getString("titulo"))
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
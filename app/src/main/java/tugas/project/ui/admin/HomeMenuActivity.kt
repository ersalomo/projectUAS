package tugas.project.ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tugas.project.databinding.ActivityHomeMenuBinding

class HomeMenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivListDogs.setOnClickListener {
            Toast.makeText(applicationContext,"List Anjing",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeAdmin::class.java))
        }
        binding.ivMainanDog.setOnClickListener {
            Toast.makeText(applicationContext,"Mainan Anjing",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,ActivityMainanDog::class.java))
        }
        binding.ivMakananDog.setOnClickListener {
            Toast.makeText(applicationContext,"Makanan Anjing",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,ActivityMakananAnjing::class.java))
        }

    }
}
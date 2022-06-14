package tugas.project.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tugas.project.R
import tugas.project.databinding.ActivityHomeMenuBinding
import tugas.project.databinding.ActivityMenuUserBinding
import tugas.project.ui.admin.ActivityMainanDog
import tugas.project.ui.admin.ActivityMakananAnjing
import tugas.project.ui.admin.HomeAdmin

class ActivityMenuUser : AppCompatActivity() {
    private lateinit var binding:ActivityMenuUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivListDogsUser.setOnClickListener {
            Toast.makeText(applicationContext,"LIst Anjing", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeUser::class.java))
        }

        binding.ivMainanDogUser.setOnClickListener {
            Toast.makeText(applicationContext,"Mainan Anjing", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ActivityListMainanDog::class.java))
        }

        binding.ivMakananDogUser.setOnClickListener {
            Toast.makeText(applicationContext,"Makanan Anjing", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ActivityListMakananDog::class.java))
        }


    }
}
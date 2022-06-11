package tugas.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tugas.project.auth.LoginAdminActivity
import tugas.project.auth.LoginUserActivity
import tugas.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.bLoginAdmin.setOnClickListener {
           val intent:Intent  = Intent(this,LoginAdminActivity::class.java)
            startActivity(intent)
        }

        binding.bLoginUser.setOnClickListener {
            Intent(this,LoginUserActivity::class.java).let {
                startActivity(it)
            }
        }

    }
}
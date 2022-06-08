package tugas.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import tugas.project.auth.LoginAdminActivity
import tugas.project.auth.LoginUserActivity
import tugas.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var btnLoginAdmin : ImageView
    private lateinit var btnLoginUser : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        btnLoginAdmin = binding.bLoginAdmin
        btnLoginUser = binding.bLoginUser

        btnLoginAdmin.setOnClickListener {
            Intent(this,LoginAdminActivity::class.java).let {
                startActivity(it)
            }
        }

        btnLoginUser.setOnClickListener {
            Intent(this,LoginUserActivity::class.java).let {
                startActivity(it)
            }
        }


    }
}
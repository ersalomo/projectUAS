package tugas.project.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tugas.project.R
import tugas.project.databinding.ActivityLoginAdminBinding

class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
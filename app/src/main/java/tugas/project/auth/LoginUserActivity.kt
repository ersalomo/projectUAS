package tugas.project.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tugas.project.R
import tugas.project.databinding.ActivityLoginUserBinding

class LoginUserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)


    }
}
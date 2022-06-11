package tugas.project.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import tugas.project.databinding.ActivityLoginAdminBinding
import tugas.project.ui.admin.HomeAdmin
import tugas.project.ui.user.HomeUser

class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginAdminBinding
    private lateinit var auntentifikasi : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auntentifikasi = FirebaseAuth.getInstance()
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bLoginAdmin.setOnClickListener {
            val email = binding.emailAdmin.text.toString().trim()
            val password = binding.passswordAdmin.text.toString().trim()

            if(email.isEmpty()){
                binding.emailAdmin.error="Email tidak boleh kosong"
                binding.emailAdmin.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty() || password.length<6){
                binding.passswordAdmin.error="Password tidak boleh kosong dan minimal6karakter"
                binding.passswordAdmin.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailAdmin.error="Format penulisan Email Salah"
                binding.emailAdmin.requestFocus()
                return@setOnClickListener
            }
            loginUser(email,password)
        }
    }
    private fun loginUser(email:String,password:String){
        auntentifikasi.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Intent(this,HomeAdmin::class.java).also {
                            intent -> intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_LONG).show()

                }
            }
    }
}
package tugas.project.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import tugas.project.R
import tugas.project.databinding.ActivityLoginUserBinding
import tugas.project.ui.user.ActivityMenuUser
import tugas.project.ui.user.HomeUser

class LoginUserActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var auntentifikasi :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        auntentifikasi = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.etEmail)
        editPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.bLogin)
        btnRegister = findViewById(R.id.bRegister)
        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if(email.isEmpty()){
                editEmail.error="Email tidak boleh kosong"
                editEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty() || password.length<6){
                editPassword.error="Password tidak boleh kosong dan minimal6karakter"
                editPassword.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editEmail.error="Format penulisan Email Salah"
                editEmail.requestFocus()
                return@setOnClickListener
            }
            loginUser(email,password)
        }
        btnRegister.setOnClickListener {
            Intent(this,UserRegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
    private fun loginUser(email:String,password:String){
        auntentifikasi.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Intent(this,ActivityMenuUser::class.java).also { intent ->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auntentifikasi.currentUser !=null){
            Intent(this,HomeUser::class.java).also{
                    intent -> intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }
        }
    }
}
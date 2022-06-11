package tugas.project.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import tugas.project.R
import tugas.project.auth.LoginUserActivity

class HomeUser:AppCompatActivity() {
    private lateinit var btnLogout : Button
    private lateinit var otentikasi:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)

        otentikasi = FirebaseAuth.getInstance()
        btnLogout = findViewById(R.id.bLogout)
        btnLogout.setOnClickListener {
            otentikasi.signOut()
            Intent(this, LoginUserActivity::class.java).also{
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }


    }
}
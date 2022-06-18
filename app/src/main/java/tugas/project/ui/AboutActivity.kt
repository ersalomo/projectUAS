package tugas.project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import tugas.project.R
import tugas.project.ui.admin.HomeMenuActivity

class AboutActivity : AppCompatActivity() {
    private lateinit var btnPindah:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        btnPindah = findViewById(R.id.imgPindahHome)

        btnPindah.setOnClickListener {
            Toast.makeText(this,"Home Menu",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeMenuActivity::class.java))
        }
    }
}
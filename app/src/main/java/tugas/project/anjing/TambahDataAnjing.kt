package tugas.project.anjing

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import tugas.project.databinding.ActivityTambahDataBinding
import tugas.project.model.Anjing

class TambahDataAnjing:AppCompatActivity() {
    private lateinit var binding:ActivityTambahDataBinding

    private lateinit var ref:DatabaseReference
    private var listDog = ArrayList<Anjing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().getReference("dogs")
        binding.bTambahData.setOnClickListener {
            createData()
            clearColom()
        }
    }
    private fun createData(){
        val nama : String = binding.edNama.text.toString().trim()
        val jenis : String = binding.edJenis.text.toString().trim()
        val desc : String = binding.edDesc.text.toString().trim()
        val image:String? = binding.uriImage.text.toString().trim()
        if(nama.isEmpty()){
            binding.edNama.error = "nama is required"
            return
        }
        if(jenis.isEmpty()) {
            binding.edDesc.error = "jenis is required"
            return
        }
        if(desc.isEmpty()){
            binding.edDesc.error = "Desc is required"
        }
        val dogId = ref.push().key
        val dog = Anjing(dogId!!,nama,jenis,desc,image)
        if (dogId !=null){
            ref.child(dogId).setValue(dog).addOnCompleteListener{
                Toast.makeText(applicationContext,"Data Berhasil Ditambah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearColom(){
        binding.edNama.setText("")
        binding.edJenis.setText("")
        binding.edDesc.setText("")
        binding.uriImage.setText("")
    }
}
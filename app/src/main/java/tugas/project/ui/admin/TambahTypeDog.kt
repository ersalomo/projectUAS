package tugas.project.ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import tugas.project.anjing.ListTypeDogAdapter
import tugas.project.databinding.ActivityTambahDataBinding
import tugas.project.databinding.ActivityTambahTypeDogBinding
import tugas.project.model.Anjing
import tugas.project.model.DogListType
import tugas.project.ui.detail.DetailListType

class TambahTypeDog : AppCompatActivity() {

    private lateinit var ref: DatabaseReference

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_JENIS = "extra_jenis"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_IMAGE ="extra_image"
    }
    private lateinit var binding:ActivityTambahTypeDogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahTypeDogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID)
        val image = intent.getStringExtra(EXTRA_IMAGE)

        binding.apply {
            tvGetJenis.setText(intent.getStringExtra(EXTRA_JENIS))
            tvGetDesc.setText(intent.getStringExtra(EXTRA_DESC))

            bAddType.setOnClickListener {
                saveDogType()
                clearColomn()
                pindah(id!!)
            }
            Glide.with(binding.root)
                .load(image)
                .circleCrop()
                .into(binding.ivTypeDog)
        }

        ref = FirebaseDatabase.getInstance().getReference("tb_typeDog").child(id!!)

    }
    private fun saveDogType(){
       val nama =  binding.etNameDoggy.text.toString().trim()
        if(nama.isEmpty()){
            binding.etNameDoggy.error ="Nama dog is required"
            return
        }
        val typeId = ref.push().key
        val typeDog = DogListType(typeId!!,nama)
        if (typeId != null){
            ref.child(typeId).setValue(typeDog).addOnCompleteListener{
                Toast.makeText(applicationContext,"Berhasil ditambah", Toast.LENGTH_LONG).show()
            }
        }

    }//save

    private fun clearColomn(){
        binding.etNameDoggy.setText("")
    }
    private fun pindah(id:String){
        val intent = Intent(this,DetailListType::class.java)
        intent.putExtra(DetailListType.ID,id)
        startActivity(intent)
    }
}
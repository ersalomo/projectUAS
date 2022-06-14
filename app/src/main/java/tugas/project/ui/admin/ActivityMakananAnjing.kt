package tugas.project.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import tugas.project.anjing.ListMKNAdapter
import tugas.project.databinding.ActivityMakananAnjingBinding
import tugas.project.model.Makanan

class ActivityMakananAnjing : AppCompatActivity() {
    private lateinit var binding:ActivityMakananAnjingBinding
    private lateinit var ref : DatabaseReference
    private var listMknDog = ArrayList<Makanan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMakananAnjingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ref = FirebaseDatabase.getInstance().getReference("tb_makan_dog")
        binding.mbTambah.setOnClickListener {
            tambahData()
            clearInput()
        }
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    listMknDog.clear()

                    for (x in snapshot.children){
                        val makanan:Makanan? = x.getValue(Makanan::class.java)
                        if(makanan!= null){
                            listMknDog.add(makanan)
                        }
                    }
                    val adapter = ListMKNAdapter(listMknDog)
                    binding.apply {
                       rvListMkn.layoutManager = LinearLayoutManager(this@ActivityMakananAnjing)
                       rvListMkn.setHasFixedSize(true)
                       rvListMkn.adapter = adapter

                   }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Cancelled",error.message)
            }

        })

    }

    private fun tambahData(){
        val nama = binding.etNamaMKN.text.toString().trim()
        val jenis = binding.etJenisMKN.text.toString().trim()
        val berat = binding.etBeratMKN.text.toString().trim()

        if(nama.isEmpty()){
            binding.etNamaMKN.error =" Harus diisi"
            return
        }
        if(jenis.isEmpty()){
            binding.etJenisMKN.error =" Harus diisi"
            return
        }
        if(jenis.isEmpty()){
            binding.etBeratMKN.error =" Harus diisi"
            return
        }

        val id = ref.push().key
        var makanan = Makanan(id!!,nama,jenis,berat)
        ref.child(id).setValue(makanan).addOnCompleteListener {
            Toast.makeText(applicationContext,"Berhasil Ditambah",Toast.LENGTH_LONG).show()

        }
    }

    private fun clearInput(){
        binding.apply {
            etNamaMKN.setText("")
            etJenisMKN.setText("")
            etBeratMKN.setText("")
        }
    }
}
package tugas.project.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import tugas.project.anjing.ListMKNAdapter
import tugas.project.anjing.Mainan
import tugas.project.anjing.MainanAdapter
import tugas.project.databinding.ActivityMainanDogactivityBinding
import tugas.project.model.Makanan

class ActivityMainanDog : AppCompatActivity() {
    private lateinit var binding:ActivityMainanDogactivityBinding
    private lateinit var ref : DatabaseReference
    private var listMainanDog = ArrayList<Mainan>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainanDogactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ref = FirebaseDatabase.getInstance().getReference("tb_mainan_dog")
        binding.mbTbhMainan.setOnClickListener {
            addMainanData()

        }
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    listMainanDog.clear()
                    for (x in snapshot.children){
                        val mainan:Mainan? = x.getValue(Mainan::class.java)
                        if(mainan!= null){
                            listMainanDog.add(mainan)
                        }
                    }
                    val adapter = MainanAdapter(listMainanDog)
                    binding.apply {
                        lvMainanDog.layoutManager = LinearLayoutManager(this@ActivityMainanDog)
                        lvMainanDog.setHasFixedSize(true)
                        lvMainanDog.adapter = adapter

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Cancelled",error.message)
            }

        })


    }

    private fun addMainanData(){
        val nama = binding.etNameMainan.text.toString().trim()
        val image = binding.etUrlMainan.text.toString().trim()

        if(nama.isEmpty()){
            binding.etNameMainan.error =" Harus diisi"
            return
        }
        if(image.isEmpty()){
            binding.etUrlMainan.error =" Harus diisi"
            return
        }

        val id = ref.push().key
        val mainan = Mainan(id!!,image,nama)

        ref.child(id).setValue(mainan).addOnCompleteListener {
            Toast.makeText(applicationContext,"Add data succuessfully added",Toast.LENGTH_LONG).show()
        }
    }
}
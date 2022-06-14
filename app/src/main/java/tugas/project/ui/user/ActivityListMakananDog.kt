package tugas.project.ui.user


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import tugas.project.anjing.ListMKNAdapter
import tugas.project.databinding.ActivityListMakananDogBinding
import tugas.project.model.Makanan

class ActivityListMakananDog : AppCompatActivity() {
    private lateinit var ref : DatabaseReference
    private var listMknDog = ArrayList<Makanan>()
    private lateinit var binding:ActivityListMakananDogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMakananDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ref = FirebaseDatabase.getInstance().getReference("tb_makan_dog")
        ref.addValueEventListener(object : ValueEventListener {
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
                        rvMakananDog.layoutManager = LinearLayoutManager(this@ActivityListMakananDog)
                        rvMakananDog.setHasFixedSize(true)
                        rvMakananDog.adapter = adapter

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Cancelled",error.message)
            }

        })

    }
}
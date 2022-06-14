package tugas.project.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import tugas.project.anjing.Mainan
import tugas.project.anjing.MainanAdapter
import tugas.project.databinding.ActivityListMainanDogBinding

class ActivityListMainanDog : AppCompatActivity() {
    private lateinit var binding:ActivityListMainanDogBinding
    private lateinit var ref : DatabaseReference
    private var listMainanDog = ArrayList<Mainan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMainanDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ref = FirebaseDatabase.getInstance().getReference("tb_mainan_dog")

        ref.addValueEventListener(object :ValueEventListener{
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
                        rvMainananDog.layoutManager = LinearLayoutManager(this@ActivityListMainanDog)
                        rvMainananDog.setHasFixedSize(true)
                        rvMainananDog.adapter = adapter

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Cancelled",error.message)
            }

        })



    }
}
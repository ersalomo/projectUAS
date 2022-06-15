package tugas.project.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import tugas.project.anjing.ListTypeDogAdapter
import tugas.project.databinding.ActivityDetailListTypeBinding
import tugas.project.model.DogListType

class DetailListType : AppCompatActivity() {
    private lateinit var ref: DatabaseReference
    private  var listTypeDogs = ArrayList<DogListType>()
    private lateinit var binding:ActivityDetailListTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailListTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().getReference("tb_typeDog")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists())
                {
                    listTypeDogs.clear()
                    for(h in snapshot.children){
                        val type:DogListType? = h.getValue(DogListType::class.java)
                        if(type !=null){
                            listTypeDogs.add(type)
                        }
                    }
                    val adapter = ListTypeDogAdapter(listTypeDogs)
                    binding.apply {
                        rvlistItemType.layoutManager = LinearLayoutManager(this@DetailListType)
                        rvlistItemType.setHasFixedSize(true)
                        rvlistItemType.adapter = adapter


                    }


                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })



    }
}
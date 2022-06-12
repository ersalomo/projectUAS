package tugas.project.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import tugas.project.R
import tugas.project.anjing.AdapterDogsUser
import tugas.project.auth.LoginUserActivity
import tugas.project.databinding.ActivityHomeUserBinding
import tugas.project.databinding.ActivityLoginUserBinding
import tugas.project.model.Anjing

class HomeUser:AppCompatActivity() {
    private lateinit var binding : ActivityHomeUserBinding
    private lateinit var otentikasi:FirebaseAuth
    private lateinit var ref : DatabaseReference
    private var listDogs = ArrayList<Anjing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        otentikasi = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("dogs")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    listDogs.clear()
                    for (h in snapshot.children){
                        val anjing:Anjing? = h.getValue(Anjing::class.java)
                        if (anjing != null){
                            listDogs.add(anjing)
                        }
                    }
                    val adapterDogUser  =AdapterDogsUser(listDogs)
//                    binding.rvDogUser.layoutManager = LinearLayoutManager(this@HomeUser)
                    binding.rvDogUser.layoutManager = GridLayoutManager(this@HomeUser,2)
                    binding.rvDogUser.itemAnimator = SlideInLeftAnimator()
                    binding.rvDogUser.setHasFixedSize(true)
                    binding.rvDogUser.adapter = adapterDogUser

                    //click to get detail
                    adapterDogUser.setOnClickedCB(object : AdapterDogsUser.OnItemClicked{
                        override fun getDogDetail(anjing: Anjing) {
                            showDetail(anjing)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Cancelled",error.message)
            }

        })


        binding.bLogout.setOnClickListener {
            otentikasi.signOut()
            Intent(this, LoginUserActivity::class.java).also{
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }


    }

    private fun showDetail(anjing: Anjing){
        Toast.makeText(applicationContext,anjing.nama,Toast.LENGTH_LONG).show()
    }
}
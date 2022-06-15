package tugas.project.ui.admin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView
import tugas.project.R
import tugas.project.anjing.AdapterDog
import tugas.project.anjing.TambahDataAnjing
import tugas.project.auth.LoginUserActivity
import tugas.project.databinding.ActivityMainActivitycccBinding
import tugas.project.model.Anjing


class HomeAdmin: AppCompatActivity() {
    private lateinit var binding:ActivityMainActivitycccBinding
    private lateinit var ref : DatabaseReference
    private var listDogs = ArrayList<Anjing>()
    private lateinit var otentikasi: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivitycccBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ref = FirebaseDatabase.getInstance().getReference("dogs")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    listDogs.clear()
                    for(h in snapshot.children){
                        val anjing : Anjing? = h.getValue(Anjing::class.java)
                        if(anjing !=null){
                            listDogs.add(anjing)
                        }
                    }
                    val adapterDog = AdapterDog(listDogs)
                    binding.rvDoggy.layoutManager = LinearLayoutManager(this@HomeAdmin)
                    binding.rvDoggy.setHasFixedSize(true)
                    binding.rvDoggy.adapter = adapterDog

                    //getDetail
                    adapterDog.setOnItemListener(object :AdapterDog.OnCLickedItem{
                        override fun onClickedItem(anjing: Anjing) {
                            Toast.makeText(this@HomeAdmin,"You cliked type of ${anjing.jenis}",Toast.LENGTH_LONG).show()
                            MoveActWithData(anjing)
                        }
                        override fun onClickToEdit(anjing: Anjing) {
                            Toast.makeText(this@HomeAdmin,"You wanna edit ${anjing.nama}",Toast.LENGTH_LONG).show()
                            showEditDailog(anjing)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        otentikasi = FirebaseAuth.getInstance()
        binding.bTambahData.setOnClickListener {
            Intent(applicationContext,TambahDataAnjing::class.java).also {
                Toast.makeText(this,"Tambah data",Toast.LENGTH_LONG).show()
                startActivity(it)
            }
        }
    }//end constructor super

    private fun showEditDailog(anjing: Anjing){
        val builder  = AlertDialog.Builder(this)
        builder.setTitle("Edit Data Doggy")
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.edit_this_dog, null)
        val image  = view.findViewById<ImageView>(R.id.imageDog)
        val txtNama = view.findViewById<EditText>(R.id.getEdName)
        val txtJenis = view.findViewById<EditText>(R.id.getEdJenis)
        val txtDesc = view.findViewById<EditText>(R.id.getEdDesc)
        val uriImage = view.findViewById<EditText>(R.id.getUriDog)

        Glide.with(this)
            .load(anjing.image)
            .circleCrop()
            .thumbnail()
            .into(image)
        txtNama.setText(anjing.nama)
        txtJenis.setText(anjing.jenis)
        txtDesc.setText(anjing.desc)
        uriImage.setText(anjing.image)

        builder.setView(view)
        builder.setPositiveButton("Update"){p0,p1->
            val dbDogs = FirebaseDatabase.getInstance().getReference("dogs")
            val nama = txtNama.text.toString().trim()
            val jenis = txtJenis.text.toString().trim()
            val desc = txtDesc.text.toString().trim()
            val imageUri = uriImage.text.toString().trim()
            if (nama.isEmpty()){
                txtNama.error = "Masukkan Nama yang benar"
                txtNama.requestFocus()
                return@setPositiveButton
            }
            if (jenis.isEmpty()){
                txtJenis.error = "Masukkan input  yang benar"
                txtJenis.requestFocus()
                return@setPositiveButton
            }
            if (desc.isEmpty()){
                txtDesc.error = "Masukkan input yang benar"
                txtDesc.requestFocus()
                return@setPositiveButton
            }
            val anjing =  Anjing(anjing.id,nama,jenis,desc,imageUri)
            dbDogs.child(anjing.id!!).setValue(anjing)
            Toast.makeText(applicationContext,"Update data sukses", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No"){p0,p1 ->
        }
        builder.setNeutralButton("Delete"){p0,p1 ->
            val delDog = FirebaseDatabase.getInstance().getReference("dogs").child(anjing.id)
            delDog.removeValue()
            Toast.makeText(this,"Data berhasil dihapus",Toast.LENGTH_LONG).show()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun MoveActWithData(anjing: Anjing){
        val intent = Intent(this@HomeAdmin,TambahTypeDog::class.java)
        intent.putExtra(TambahTypeDog.EXTRA_ID,anjing.id)
        intent.putExtra(TambahTypeDog.EXTRA_JENIS, anjing.jenis)
        intent.putExtra(TambahTypeDog.EXTRA_DESC, anjing.desc)
        intent.putExtra(TambahTypeDog.EXTRA_IMAGE,anjing.image)
        startActivity(intent)
    }

}
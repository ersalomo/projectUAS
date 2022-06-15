package tugas.project.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import tugas.project.R
import tugas.project.databinding.ActivityDetailDogBinding
import tugas.project.model.Anjing

class ActivityDetailDog : AppCompatActivity() {
    private lateinit var binding:ActivityDetailDogBinding
    companion object{
        const val EXTRA_IMAGE="extra_image"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_JENIS ="extra_jenis"
        const val EXTRA_DESC ="extra_desc"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(intent.getStringExtra(EXTRA_IMAGE))
            .circleCrop()
            .into(binding.ivDog)
        binding.tvDogName.text = intent.getStringExtra(EXTRA_NAME)
        binding.tvDogDesc.text = intent.getStringExtra(EXTRA_DESC)
        binding.tvDogType.text = intent.getStringExtra(EXTRA_JENIS)


    }


}
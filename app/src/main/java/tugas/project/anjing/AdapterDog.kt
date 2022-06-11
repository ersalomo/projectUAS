package tugas.project.anjing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tugas.project.R
import tugas.project.databinding.ItemDogsBinding
import tugas.project.model.Anjing

class AdapterDog(private val listDogs:ArrayList<Anjing>):RecyclerView.Adapter<AdapterDog.DogViewHolder>() {
    private lateinit var onCLickedItem: OnCLickedItem

    fun setOnItemListener(onCLickedItem: OnCLickedItem){
        this.onCLickedItem = onCLickedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = ItemDogsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DogViewHolder((view))
    }

    override fun getItemCount(): Int = listDogs.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
    holder.bind(listDogs[position])
        holder.binding.root.setOnClickListener {
            onCLickedItem.onClickedItem(listDogs[position])
        }
        holder.binding.bEdit.setOnClickListener {
            onCLickedItem.onClickToEdit(listDogs[position])
        }
    }
   inner class DogViewHolder(val binding:ItemDogsBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(anjing: Anjing){
                binding.apply {
                    tvtNamaDog.text =anjing.nama
                    tvJenisDog.text ="Type : ${anjing.jenis}"
                    tvdescDog.text = "Desc : ${anjing.desc}"

                }
                Glide.with(binding.root)
                    .load(anjing.image)
                    .circleCrop()
                    .thumbnail()
                    .placeholder(R.drawable.dogy2)
                    .into(binding.imgDog)

            }
    }
    interface OnCLickedItem{
        fun onClickedItem(anjing: Anjing)
        fun onClickToEdit(anjing: Anjing)
    }
}
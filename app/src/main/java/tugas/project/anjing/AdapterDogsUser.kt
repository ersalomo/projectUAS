package tugas.project.anjing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tugas.project.R
import tugas.project.databinding.ItemlistDogsForUserBinding
import tugas.project.model.Anjing

class AdapterDogsUser(private val listDogs:ArrayList<Anjing>):RecyclerView.Adapter<AdapterDogsUser.ViewHolder>() {
    private lateinit var onItemClicked: OnItemClicked

    fun setOnClickedCB(onItemClicked: OnItemClicked){
        this.onItemClicked = onItemClicked
    }
    inner class ViewHolder(val binding: ItemlistDogsForUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(anjing: Anjing){
            binding.apply {
                Glide.with(root)
                    .load(anjing.image)
                    .thumbnail()
                    .placeholder(R.drawable.img)
                    .into(ivDog)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = ItemlistDogsForUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listDogs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDogs[position])
        holder.binding.root.setOnClickListener {
            onItemClicked.getDogDetail(listDogs[position])
        }
    }
    interface OnItemClicked{
        fun getDogDetail(anjing: Anjing)
    }
}
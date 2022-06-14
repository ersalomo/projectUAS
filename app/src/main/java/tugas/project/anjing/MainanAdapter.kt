package tugas.project.anjing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tugas.project.databinding.ListMainanBinding

class MainanAdapter(private var listMain:ArrayList<Mainan>):RecyclerView.Adapter<MainanAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainanAdapter.ViewHolder {
        val view = ListMainanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainanAdapter.ViewHolder, position: Int) {
        holder.bind(listMain[position])

    }

    override fun getItemCount(): Int = listMain.size

    inner class ViewHolder(val binding:ListMainanBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(mainan: Mainan){
                binding.apply {
                    Glide.with(root)
                        .load(mainan.image)
                        .circleCrop()
                        .into(imgMainan)
                    tvNnmMAinan.text = mainan.nama
                }
            }
    }


}
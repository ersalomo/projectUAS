package tugas.project.anjing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tugas.project.databinding.ListTypeOfDogsBinding
import tugas.project.model.Anjing
import tugas.project.model.DogListType

class ListTypeDogAdapter(private val listTypeDogs:ArrayList<DogListType>):RecyclerView.Adapter<ListTypeDogAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTypeDogAdapter.ViewHolder {
            val view = ListTypeOfDogsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListTypeDogAdapter.ViewHolder, position: Int) {
        holder.bind(listTypeDogs[position])
    }

    override fun getItemCount(): Int=listTypeDogs.size
    inner class ViewHolder(val binding:ListTypeOfDogsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(anjing: DogListType){
       binding.apply {
           tvtypeDog.text = anjing.nama
            }
        }

    }


}
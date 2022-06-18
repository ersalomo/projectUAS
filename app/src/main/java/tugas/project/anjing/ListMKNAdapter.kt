package tugas.project.anjing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tugas.project.databinding.ListMakananBinding
import tugas.project.model.Makanan

class ListMKNAdapter(private var listMakan:ArrayList<Makanan>):RecyclerView.Adapter<ListMKNAdapter.ViewHolder> (){

    inner class ViewHolder(val binding:ListMakananBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(makanan: Makanan){
            binding.apply {
                tvNamaMKN.text = makanan.nama
                tvJenisMKN.text = "${makanan.jenis}"
                tvBeratMKN.text = "Berat ${makanan.berat}"
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = ListMakananBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMakan.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMakan[position])
    }
}
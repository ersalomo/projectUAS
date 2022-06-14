package tugas.project.model

data class Makanan(
    var id:String,
    var nama:String,
    var jenis:String,
    var berat:String){

    constructor():this("","","","")
}

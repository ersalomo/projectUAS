package tugas.project.model

data class Anjing(
    var id:String,
    var nama:String,
    var jenis:String,
    var desc:String,
    var image:String?){
    constructor():this("","","","","")
}

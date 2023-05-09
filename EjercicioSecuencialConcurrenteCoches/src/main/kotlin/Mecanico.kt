import com.google.common.util.concurrent.AtomicDouble

class Mecanico(val nombre: String, val lista : List<Coche>, var recaudaccionTotal: AtomicDouble) : Thread() {

     override fun run() {
         var total = 0.0
         println("Trabajando mecanico : $nombre")
         lista.forEach {
             println("El mecanico : ${nombre} esta trabajando en el coche: ${it}")
             var recaudacion = it.reparacion - it.precio
             recaudaccionTotal.getAndAdd(recaudacion)
             total += recaudacion
         }
         println("El mecanico : ${nombre} su recaudacion total: $total")

     }

    fun procesarReparacion() : Double{

        var total = 0.0
        println("Trabajando mecanico : $nombre")
        lista.forEach {
            println("El mecanico : ${nombre} esta trabajando en el coche: ${it}")
            var recaudacion = it.reparacion - it.precio
            recaudaccionTotal.getAndAdd(recaudacion)
            total += recaudacion
        }
        println("El mecanico : ${nombre} su recaudacion total: $total")
        return total
    }
 }
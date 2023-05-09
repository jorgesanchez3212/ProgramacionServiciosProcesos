import kotlinx.coroutines.delay
import kotlin.random.Random

data class Taller(
    val nombre: String
) {

    suspend fun repararCoche(coche : Coche) : Int {
        var recaudacion = 0
        println("Soy el mecanico $nombre")
        println("Soy el mecanico $nombre y voy a arreglar el coche $coche")
        delay(Random.nextLong(300,600))
        recaudacion += coche.coste
        return recaudacion

    }
}
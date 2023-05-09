import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.system.measureTimeMillis

data class Taller(
    val nombre : String,
    val misCoches : List<Car> = List((Random.nextInt(4,8))){Car(Random.nextInt(200,500))}
) {


    suspend fun repararCoche() : Int{
        var recaudacion = 0
        println("Soy el mecanido $nombre y tengo ${misCoches.size} coches")
        measureTimeMillis {
            misCoches.forEachIndexed { index, car ->
                println("Soy el mecanico $nombre y estoy arreglado un coche $index")
                delay(Random.nextLong(300,600))
                recaudacion += car.coste
            }
        }.also {
            println("Tiempo de ejecucion total del mecanido $nombre $it ms")
            println("Soy el mecanico $nombre y he recaudado $recaudacion")
            return recaudacion
        }
    }



}
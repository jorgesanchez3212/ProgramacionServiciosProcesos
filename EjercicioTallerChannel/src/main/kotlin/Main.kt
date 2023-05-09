import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope( Dispatchers.Default)
    val listaMecanicos = listOf<Taller>(Taller("1"), Taller("2"), Taller("3"))
    val listaCoche = mutableListOf<Coche>()

    listaCoche.addAll(generarCoches())
    val channel = Channel<Coche>(5)




}

fun generarCoches( ): List<Coche>{
    val listaCoche = mutableListOf<Coche>()

    for (i in 0 until 60){
        val car = Coche(Random.nextInt(400, 600))
        listaCoche.add(car)
    }
    return listaCoche
}
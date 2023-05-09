import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.random.Random

import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)
    val listaCoches : BlockingQueue<Coche> = LinkedBlockingQueue<Coche>()
    val listaMecanicos = listOf<Taller>(Taller("1",listaCoches), Taller("2",listaCoches), Taller("3",listaCoches))



    println("Ejecución con async y await")
    measureTimeMillis {
        var recaudacion = 0
        listaCoches.addAll(generarCoches())
        val pendientes = listaMecanicos.mapIndexed { index, mecanico ->
            scope.async {
                mecanico.repararCoche()
            }
        }

        pendientes.forEach {
            recaudacion += it.await()
        }

        println("EL total de la recaudación ha sido de $recaudacion")

    }.also {
        println("La ejecución ha tardado $it ms")
    }

}


fun generarCoches( ): List<Coche>{
    val listaCoche = mutableListOf<Coche>()

    for (i in 0 until 60){
        val car = Coche(Random.nextInt(400, 600))
        listaCoche.add(car)
    }
    return listaCoche
}
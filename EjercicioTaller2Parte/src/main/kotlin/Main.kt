import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {

    var listaMecanicos = List(3){Taller("$it")}
    val myScope = CoroutineScope(Dispatchers.Default)


    println("Ejecucion con corrutinas launch")
    measureTimeMillis {
        val mutex = Mutex()
        var recaudacion = 0
        val jobs = listaMecanicos.mapIndexed { index, mecanico ->
            println("Soy el mecanico $index y comienzo a reparar")
            myScope.launch {
                val res = mecanico.repararCoche()
                mutex.withLock {
                    recaudacion += res
                }
            }
        }
        jobs.joinAll()
        println("Recaudacion total $recaudacion")

    }.also {
        println("Tiempo total de ejecución total es $it ms ")
    }


println("Ejecución con Async/Await")
    measureTimeMillis {
        var recaudacion = 0
        val pendientes = listaMecanicos.mapIndexed { index, mecanico ->
            println("Soy el mecanico $index y comienzo a reparar los coches")
            myScope.async {
                mecanico.repararCoche()
            }
        }

        pendientes.forEach{ recaudacion += it.await() }
        println("Recaudacion total es: $recaudacion EUROS ")

    }.also { println("Tiempo de ejecucion async await $it ms") }


}


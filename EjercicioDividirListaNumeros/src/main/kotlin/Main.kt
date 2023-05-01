import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val bloques = listaNumerosAleatorios().chunked(10)


    val tiempo = measureTimeMillis {
        bloques.forEach {
            val media = it.sum().toDouble() / it.size
            println("La suma de la media de $it es $media ")
        }
    }


    println("\n")
    println("\n")
    println("\n")


    val time = measureTimeMillis {

        val deferred = bloques.map {
            async {
                val media = it.sum().toDouble() / it.size
                println("La suma de la media de $it es $media ")
            }
        }
        deferred.awaitAll()
    }

    println("El programa ha tardado $tiempo milisegundos")
    println("El programa ha tardado $time milisegundos")

}



fun listaNumerosAleatorios() : List<Int> {
    val list = mutableListOf<Int>()
    for(i in 0 until 100){
        val num = Random.nextInt(0,1000)
        list.add(num)
    }
    return list
}
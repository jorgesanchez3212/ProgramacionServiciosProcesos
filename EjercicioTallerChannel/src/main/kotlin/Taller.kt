import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.BlockingDeque
import java.util.concurrent.BlockingQueue
import kotlin.random.Random

data class Taller(
    val nombre: String,
    val listaCoche : BlockingQueue<Coche>
) {
    val mutex = Mutex()
    val channel = Channel<Coche>(5)


    suspend fun repararCoche() : Int {
        var recaudacion = 0
        var coche : Coche
        var i = 0
        println("Soy el mecánico $nombre")

        while (listaCoche.isNotEmpty()){
                mutex.withLock {
                    val car = listaCoche.take()
                    channel.send(car)
                    coche = channel.receive()
                }
            println("Soy el mecánico $nombre y voy a arreglar el coche $coche")


            delay(Random.nextLong(300,600))
            recaudacion += coche.coste

        }





        return recaudacion
    }


}


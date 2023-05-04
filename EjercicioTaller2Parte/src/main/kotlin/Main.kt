import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlin.random.Random

fun main() = runBlocking<Unit> {

    //Productor - Consumidor

    val cola = mutableListOf<Car>()
    val semaforo = Mutex()
    val capacidadMax = 5
    var capacidadActual = 0
    val colaDentroTaller = mutableListOf<Car>()


    cola.addAll(generarCoches())

    // Productores

    for (entrada in 1..2) {
        launch(Dispatchers.Default) {
            while (cola.isNotEmpty()){
                delay(Random.nextLong(200,400))
                semaforo.withLock {
                    if (capacidadActual < capacidadMax) {
                        val car = cola.removeFirst()
                        colaDentroTaller.add(car)
                        capacidadActual++
                        println("Ha entrado el coche $car")

                    }
                }
            }
        }
    }

    // Consumidores

    for (mecanico in 1..3){
        launch(Dispatchers.Default) {
            while (cola.isNotEmpty()){
                if (colaDentroTaller.isNotEmpty())
                    semaforo.withLock {
                        val car = colaDentroTaller.removeFirst()
                        delay(Random.nextLong(300, 600))
                        println("El mecanico $mecanico ha arreglado el coche $car")
                        capacidadActual--
                    }
            }
        }
    }
}


fun generarCoches() : List<Car>{
    val cola = mutableListOf<Car>()
    for(i in 1..60){
        val car = Car(i)
        cola.add(car)
    }
    return cola
}


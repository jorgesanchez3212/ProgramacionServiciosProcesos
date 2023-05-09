import com.google.common.util.concurrent.AtomicDouble
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.random.nextInt

fun main(){
    println("Primera parte")
    //paralela()
    //secuencial()
}

    fun paralela() {
        var recaudaccionTotal = AtomicDouble(0.0)
        val m1 = Mecanico("Mecanico 1",generarCooches(), recaudaccionTotal)
        val m2 = Mecanico("Mecanico 2",generarCooches(),recaudaccionTotal)
        val m3 = Mecanico("Mecanico 3",generarCooches(), recaudaccionTotal)

        var pool = Executors.newFixedThreadPool(3)
        pool.execute(m1)
        pool.execute(m2)
        pool.execute(m3)

        pool.shutdown()

        while (!pool.isTerminated);

        println("La recaudación total es : ${recaudaccionTotal}")

    }



    fun secuencial(){
        var total : Double
        var recaudaccionTotal = AtomicDouble(0.0)
        val m1 = Mecanico("Mecanico 1",generarCooches(), recaudaccionTotal)
        val m2 = Mecanico("Mecanico 2",generarCooches(),recaudaccionTotal)
        val m3 = Mecanico("Mecanico 3",generarCooches(), recaudaccionTotal)

        val t1 = m1.procesarReparacion()
        val t2 = m2.procesarReparacion()
        val t3 = m3.procesarReparacion()

        total = t1 + t2 + t3
        println("El total es : $total")
    }



fun generarCooches() : List<Coche> {
    val numCoches = Random.nextInt(4..8)
    val coches = mutableListOf<Coche>()
    for (i in 1..numCoches){
        val coche = Coche(Random.nextDouble(100.0,300.0), Random.nextDouble(50.0,200.0))
        coches.add(coche)
    }
    return coches
}

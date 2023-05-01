import kotlinx.coroutines.*
import kotlin.random.Random


fun main() = runBlocking {
    val mecanicos = listOf("Mecánico 1", "Mecánico 2", "Mecánico 3")
    val trabajosPendientes = mecanicos.map { mecanico -> async { repararCoche(mecanico) } }
    val ingresos = trabajosPendientes.awaitAll()
    val ingresoTotal = ingresos.sum()
    println("La recaudación total obtenida es de $ingresoTotal")
}

fun repararCoche(mecanico: String): Int {
    val cantidadCoches = Random.nextInt(4, 9)
    var ingresoTotal = 0
    for (i in 0 until cantidadCoches) {
        val reparacion = Random.nextInt(100, 300)
        val precio = Random.nextInt(50, 200)
        ingresoTotal += reparacion - precio
    }
    println("$mecanico ha reparado $cantidadCoches coches y ha obtenido un ingreso total de $ingresoTotal")
    return ingresoTotal
}

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.concurrent.Callable
import java.util.concurrent.Executors

fun main(){
   // crearCsvCoches("data",generadorCoches())


    println("Future y callable")
    val pool = Executors.newSingleThreadExecutor()
    println("Callable")
    val future = pool.submit(Callable {
        println("Callable")
        leerCsv("data"+File.separator+"coches.csv")
    })
    pool.shutdown()
    println(future.get())

}


fun generadorCoches():List<Coche>{
    val lista = mutableListOf<Coche>()
    for(i in 1..1000){
        lista.add(Coche(
            i.toLong(),
            "Ford",
            LocalDate.now()
        ))
    }
    return lista
}


fun crearCsvCoches(ruta : String, list : List<Coche>){
    if(Files.notExists(Path.of(ruta))){
        Files.createDirectories(Path.of(ruta))
    }
    val fichero = File(ruta + File.separator + "coches.csv")
    fichero.writeText("id;marca;fechaMatriculacion\n")
    list.forEach {
        fichero.appendText("${it.id};${it.marca};${it.fechaMatriculacion}\n")
    }
}



fun leerCsv(ruta : String) : List<Coche>{
    if (Files.notExists(Path.of(ruta))){
        System.err.println("No existe la ruta $ruta")
        System.exit(0)
    }

    val fichero = File(ruta)
    return fichero.readLines()
        .drop( 1)
        .map { it.split(";") }
        .map {
            it.map {
                it.trim()
            }
            Coche(
                it[0].toLong(),
                it[1],
                LocalDate.parse(it[2])
            )
        }
    }
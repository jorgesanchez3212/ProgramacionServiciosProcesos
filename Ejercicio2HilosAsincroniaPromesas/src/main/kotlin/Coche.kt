import java.time.LocalDate

data class Coche(
    val id: Long,
    val marca: String,
    val fechaMatriculacion: LocalDate
) {
}
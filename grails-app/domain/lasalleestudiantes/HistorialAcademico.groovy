package lasalleestudiantes

class HistorialAcademico {

    int semestre
    Date inscrito_en
    Date finalizado_en
    boolean trunco
    Estudiante estudiante
    Especializacion especializacion

    static belongsTo = [Estudiante, Especializacion]

    static constraints = {
        semestre range: 1..15, blank: false
        finalizado_en nullable: true
    }

    String toString() {
        return estudiante.toString() + "-" + especializacion.toString()
    }
}

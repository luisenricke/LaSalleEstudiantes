package lasalleestudiantes

class HistorialAcademico {

    int             semestre
    Date            inscrito_en
    Date            finalizado_en
    boolean         trunco
    Estudiante      estudiante
    Especializacion especializacion
    NivelAcademico  nivelAcademico

    static belongsTo = [Estudiante, Especializacion, NivelAcademico]

    static constraints = {
        semestre range: 1..15, blank: false
        finalizado_en nullable: true
        especializacion nullable: true
    }

    String toString() {
        estudiante.toString() + "-" + especializacion.toString()
    }
}

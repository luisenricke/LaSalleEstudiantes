package lasalleestudiantes

class HistorialAcademico {

    int semestre
    Date inscrito_en
    Date finalizado_en
    boolean es_trunco

    static belongsTo = [estudiante: Estudiante, especializacion: Especializacion]

    static constraints = {
        semestre range: 1..15, blank: false
        finalizado_en nullable: true
    }
}

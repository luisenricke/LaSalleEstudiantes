package lasalleestudiantes

class Especializacion {

    String nombre

    static belongsTo = [nivelAcademico: NivelAcademico]
    static hasMany   = [historialesAcademicos: HistorialAcademico]

    static constraints = {
        nombre minSize: 5, blank: false, unique: true
    }

    String toString() {
        nombre
    }
}

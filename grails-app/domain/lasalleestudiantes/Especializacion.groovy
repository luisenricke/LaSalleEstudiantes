package lasalleestudiantes

class Especializacion {

    String nombre

    static belongsTo = [nivelAcademico: NivelAcademico]
    static hasMany = [historialAcademico: HistorialAcademico]

    static constraints = {
        nombre minSize: 5, blank: false, unique: true
    }

    String toString() {
        return nombre
    }
}

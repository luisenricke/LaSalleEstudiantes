package lasalleestudiantes

class Estudiante {

    String nombre
    String paterno
    String materno
    String matricula
    String correo

    static hasMany = [historialAcademico: HistorialAcademico]

    static constraints = {
        nombre size: 2..70, blank: false
        paterno size: 2..70, blank: false
        materno size: 2..70, blank: false
        matricula size: 9, blank: false, unique: true
        correo email: true, blank: false, unique: true
    }

    String toString() {
        return matricula
    }
}

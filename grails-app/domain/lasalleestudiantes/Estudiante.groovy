package lasalleestudiantes

class Estudiante {

    String nombre
    String paterno
    String materno
    String matricula
    String correo
    String contrasenia

    static hasMany = [historialesAcademicos: HistorialAcademico]

    static constraints = {
        nombre size: 2..70, blank: false
        paterno size: 2..70, blank: false
        materno size: 2..70, blank: false
        matricula size: 9..9, blank: false, unique: true
        correo email: true, blank: false, unique: true
        contrasenia minSize: 8, password: true, blank: false
    }

    String toString() {
        matricula
    }
}

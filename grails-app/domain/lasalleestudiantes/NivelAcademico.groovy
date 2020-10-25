package lasalleestudiantes

class NivelAcademico {

    String nombre

    static hasMany = [especializaciones: Especializacion]

    static constraints = {
        nombre minSize: 5, blank: false, unique: true
    }

    String toString() {
        return nombre
    }
}

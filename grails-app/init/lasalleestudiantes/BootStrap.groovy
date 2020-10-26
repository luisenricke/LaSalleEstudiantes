package lasalleestudiantes

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        creandoNivelAcademico('Bachillerato', [])
        creandoNivelAcademico( "Licenciatura", ["Enfermería", "Software", "Arquitectura"])
        creandoNivelAcademico( "Maestría", ["Fiscal", "Educación"])
        creandoNivelAcademico( "Doctorado", ["Comunicación", "Gastronomía"])
        creandoEstudiantes()
    }

    def destroy = {
    }

    def static creandoNivelAcademico(String nombre, List<String> especializaciones) {
        if (nombre.isBlank()) return

        NivelAcademico _nivelAcademico = new NivelAcademico(nombre: nombre)
        _nivelAcademico.save()

        for (especializacion in especializaciones) {
            Especializacion _especializacion = new Especializacion(nombre: especializacion, nivelAcademico: _nivelAcademico)
            _especializacion.save()
        }
    }

    def static creandoEstudiantes() {
        Estudiante _estudiante = new Estudiante()
        _estudiante.setNombre("Luis Enrique")
        _estudiante.setPaterno("Villalobos")
        _estudiante.setMaterno("Meléndez")
        _estudiante.setMatricula("123456789")
        _estudiante.setCorreo("luisvillalobosmelendez@outlook.com")
        _estudiante.save()
    }
}

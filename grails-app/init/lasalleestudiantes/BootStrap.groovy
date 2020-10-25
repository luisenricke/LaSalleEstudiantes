package lasalleestudiantes

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        creandoNivelAcademico 'Bachillerato', []
        creandoNivelAcademico "Licenciatura", ["Enfermería", "Software", "Arquitectura"]
        creandoNivelAcademico "Maestría", ["Fiscal", "Educación"]
        creandoNivelAcademico "Doctorado", ["Comunicación", "Gastronomía"]
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
}

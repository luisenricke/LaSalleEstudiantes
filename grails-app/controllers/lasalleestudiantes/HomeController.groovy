package lasalleestudiantes

import grails.validation.ValidationException
import org.grails.taglib.GrailsTagException

import javax.servlet.ServletException
import java.lang.reflect.InvocationTargetException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

class HomeController {

    EstudianteService         estudianteService
    NivelAcademicoService     nivelAcademicoService
    EspecializacionService    especializacionService
    HistorialAcademicoService historialAcademicoService

    def index() {}

    def register() {

    }

    def save() {
        try {
            imprimirPeticion(params)
        } catch (ServletException e) {

        }
        redirect action: "index", method: "GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def encontrarEspecialidades(Long nivelAcademicoId) {
        println "nivelAcademicoId -> ${nivelAcademicoId} "

        try {
            NivelAcademico nivelAcademico = NivelAcademico.get(nivelAcademicoId)
            def especializaciones = Especializacion.findAllByNivelAcademico(nivelAcademico, [order: 'name'])

            // for (item in especializaciones) println "Especializacion ${item.nombre}"

            render g.select(
                    id: 'especializacion-id',
                    name: 'especializacion-id',
                    from: especializaciones,
                    optionKey: 'id',
                    noSelection: [null: ' - Elige una especialidad - '])
        } catch (NullPointerException | GrailsTagException | InvocationTargetException ex) {
            render g.select(id: 'especializacion-id', name: 'especializacion-id', from: [], optionKey: 'id', noSelection: [null: ' - Elige una especialidad - '])
        }
    }

    def imprimirPeticion(params) {
        println "nombre ${params.estudiante.nombre}"
        println "paterno ${params.estudiante.paterno}"
        println "materno ${params.estudiante.materno}"
        println "matricula ${params.estudiante.matricula}"
        println "correo ${params.estudiante.correo}"
        println "contraseñ ${params.estudiante.contrasenia}"
        println "nivelacademico ${params.nivelAcademico.id}"
        println "especializacion ${params.especializacion.id as String}"
        println "semestre ${params.historial.semestre}"
    }
}

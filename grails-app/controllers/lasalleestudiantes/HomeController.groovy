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

    def register() {}

    def alert() {}

    def save() {
        imprimirPeticion(params)

        def estudiante = new Estudiante()
        estudiante.setNombre(params.estudiante.nombre)
        estudiante.setPaterno(params.estudiante.paterno)
        estudiante.setMaterno(params.estudiante.materno)
        estudiante.setMatricula(params.estudiante.matricula)
        estudiante.setCorreo(params.estudiante.correo)
        estudiante.setContrasenia(params.estudiante.contrasenia)

        if (!estudiante.save()) {
            // Checa los posibles errores sin guardar la indormación
            estudiante.errors.allErrors.each {
                if (it.arguments.contains("matricula") && estudiante.getMatricula().length() == 9) {
                    redirect action: "alert", method: "GET"
                    return
                }
            }
        }

        try {
            estudianteService.save(estudiante)
        } catch (ValidationException e) {
            respond estudiante.errors, view: 'register'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), estudiante.id])
                redirect estudiante
            }
            '*' { respond estudiante, [status: CREATED] }
        }
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

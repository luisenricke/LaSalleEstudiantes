package lasalleestudiantes

import grails.validation.ValidationException
import org.grails.taglib.GrailsTagException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    def save() {
        //imprimirPeticion(params)

        def estudiante = new Estudiante()
        estudiante.setNombre(params.estudiante.nombre)
        estudiante.setPaterno(params.estudiante.paterno)
        estudiante.setMaterno(params.estudiante.materno)
        estudiante.setMatricula(params.estudiante.matricula)
        estudiante.setCorreo(params.estudiante.correo)
        estudiante.setContrasenia(params.estudiante.contrasenia)
        def nivelacademicoid =  params.nivelAcademico.id as Integer
        def semestre =  params.historial.semestre as Integer

        int especializacionid = 0
        try {
             especializacionid = params.especializacion.id as Integer
        }catch(NumberFormatException | NullPointerException e) {
            println "No se selecciono ninguna especialidad"
        }

        // Verificar si ya está registrado y lanzar la alerta
        def _estudiante = Estudiante.findByMatricula(estudiante.getMatricula())
        if (_estudiante != null) {
            redirect action: "alert", method: "GET"
            return
        }

        // Validar todos los campos
        if (!estudiante.validate()) {
            respond estudiante.errors, view: 'register'
            return
        }

        // Validar que sea de primer semestre
        if (semestre != 1) {
            render view: 'register', model: [error_semestre: "Únicamente los alumnos de primer semestre se pueden registrar"]
            return
        }

        // Validar que haya seleccionado una especialidad si existe alguna relación con NivelAcademico
        def academia = nivelAcademicoService.get(nivelacademicoid)
        def especializaciones = academia.especializaciones as List
        if (!especializaciones.isEmpty() && especializacionid == 0) {
            render view: 'register', model: [error_semestre: "Para el nivel académico que estas cursando debes seleccionar una especialidad"]
            return
        }

        // Insertar
        estudianteService.save(estudiante)

        def historial = new HistorialAcademico()
        historial.setEstudiante(estudiante)
        historial.setNivelAcademico(academia)
        if(!especializaciones.isEmpty()) {
            def especializacion = especializacionService.get(especializacionid)
            historial.setEspecializacion(especializacion)
        }
        historial.setSemestre(semestre)
        historial.setTrunco(false)
        historial.setInscrito_en(new Date())

        historialAcademicoService.save(historial)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estudiante.label', default: 'Estudiante'), estudiante.id])
                redirect controller: "home", action: "index", method: "GET"
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
                    noSelection: ['': ' - Elige una especialidad - '])
        } catch (NullPointerException | GrailsTagException | InvocationTargetException ex) {
            render g.select(id: 'especializacion-id', name: 'especializacion-id', from: [], optionKey: 'id', noSelection: ['': ' - Elige una especialidad - '])
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
        println "especializacion ${params.especializacion.id}"
        println "semestre ${params.historial.semestre}"
    }
}

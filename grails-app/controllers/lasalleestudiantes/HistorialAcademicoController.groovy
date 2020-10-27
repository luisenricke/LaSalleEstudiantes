package lasalleestudiantes

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HistorialAcademicoController {

    HistorialAcademicoService historialAcademicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond historialAcademicoService.list(params), model:[historialAcademicoCount: historialAcademicoService.count()]
    }

    def show(Long id) {
        respond historialAcademicoService.get(id)
    }

    def create() {
        respond new HistorialAcademico(params)
    }

    def save(HistorialAcademico historialAcademico) {
        if (historialAcademico == null) {
            notFound()
            return
        }

        try {
            historialAcademicoService.save(historialAcademico)
        } catch (ValidationException e) {
            respond historialAcademico.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'historialAcademico.label', default: 'HistorialAcademico'), historialAcademico.id])
                redirect historialAcademico
            }
            '*' { respond historialAcademico, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond historialAcademicoService.get(id)
    }

    def update(HistorialAcademico historialAcademico) {
        if (historialAcademico == null) {
            notFound()
            return
        }

        try {
            historialAcademicoService.save(historialAcademico)
        } catch (ValidationException e) {
            respond historialAcademico.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'historialAcademico.label', default: 'HistorialAcademico'), historialAcademico.id])
                redirect historialAcademico
            }
            '*'{ respond historialAcademico, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        historialAcademicoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'historialAcademico.label', default: 'HistorialAcademico'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'historialAcademico.label', default: 'HistorialAcademico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

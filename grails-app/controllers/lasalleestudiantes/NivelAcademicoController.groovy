package lasalleestudiantes

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NivelAcademicoController {

    NivelAcademicoService nivelAcademicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond nivelAcademicoService.list(params), model:[nivelAcademicoCount: nivelAcademicoService.count()]
    }

    def show(Long id) {
        respond nivelAcademicoService.get(id)
    }

    def create() {
        respond new NivelAcademico(params)
    }

    def save(NivelAcademico nivelAcademico) {
        if (nivelAcademico == null) {
            notFound()
            return
        }

        try {
            nivelAcademicoService.save(nivelAcademico)
        } catch (ValidationException e) {
            respond nivelAcademico.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nivelAcademico.label', default: 'NivelAcademico'), nivelAcademico.id])
                redirect nivelAcademico
            }
            '*' { respond nivelAcademico, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nivelAcademicoService.get(id)
    }

    def update(NivelAcademico nivelAcademico) {
        if (nivelAcademico == null) {
            notFound()
            return
        }

        try {
            nivelAcademicoService.save(nivelAcademico)
        } catch (ValidationException e) {
            respond nivelAcademico.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nivelAcademico.label', default: 'NivelAcademico'), nivelAcademico.id])
                redirect nivelAcademico
            }
            '*'{ respond nivelAcademico, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        nivelAcademicoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nivelAcademico.label', default: 'NivelAcademico'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivelAcademico.label', default: 'NivelAcademico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

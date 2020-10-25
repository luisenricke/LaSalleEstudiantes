package lasalleestudiantes

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EspecializacionController {

    EspecializacionService especializacionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond especializacionService.list(params), model:[especializacionCount: especializacionService.count()]
    }

    def show(Long id) {
        respond especializacionService.get(id)
    }

    def create() {
        respond new Especializacion(params)
    }

    def save(Especializacion especializacion) {
        if (especializacion == null) {
            notFound()
            return
        }

        try {
            especializacionService.save(especializacion)
        } catch (ValidationException e) {
            respond especializacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'especializacion.label', default: 'Especializacion'), especializacion.id])
                redirect especializacion
            }
            '*' { respond especializacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond especializacionService.get(id)
    }

    def update(Especializacion especializacion) {
        if (especializacion == null) {
            notFound()
            return
        }

        try {
            especializacionService.save(especializacion)
        } catch (ValidationException e) {
            respond especializacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'especializacion.label', default: 'Especializacion'), especializacion.id])
                redirect especializacion
            }
            '*'{ respond especializacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        especializacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'especializacion.label', default: 'Especializacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'especializacion.label', default: 'Especializacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

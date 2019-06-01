package intersistemas



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AutorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Autor.async.task {
            [autorInstanceList: list(params), count: count() ]
        }.then { result ->
            respond result.autorInstanceList, model:[autorInstanceCount: result.count]
        }
    }

    def show(String id) {
        Autor.async.get(id).then { autorInstance ->
            respond autorInstance
        }
    }

    def create() {
        respond new Autor(params)
    }

    def save(Autor autorInstance) {
        Autor.async.withTransaction {
            if (autorInstance == null) {
                notFound()
                return
            }

            if(autorInstance.hasErrors()) {
                respond autorInstance.errors, view:'create' // STATUS CODE 422
                return
            }

            autorInstance.save flush:true
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'autorInstance.label', default: 'Autor'), autorInstance.id])
                    redirect autorInstance
                }
                '*' { respond autorInstance, [status: CREATED] }
            }
        }
    }

    def edit(String id) {
        Autor.async.get(id).then { autorInstance ->
            respond autorInstance
        }
    }

    def update(String id) {
        Autor.async.withTransaction {
            def autorInstance = Autor.get(id)
            if (autorInstance == null) {
                notFound()
                return
            }

            autorInstance.properties = params
            if( !autorInstance.save(flush:true) ) {
                respond autorInstance.errors, view:'edit' // STATUS CODE 422
                return
            }

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'Autor.label', default: 'Autor'), autorInstance.id])
                    redirect autorInstance
                }
                '*'{ respond autorInstance, [status: OK] }
            }
        }
    }

    def delete(String id) {
        Autor.async.withTransaction {
            def autorInstance = Autor.get(id)
            if (autorInstance == null) {
                notFound()
                return
            }

            autorInstance.delete flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'Autor.label', default: 'Autor'), autorInstance.id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'autorInstance.label', default: 'Autor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

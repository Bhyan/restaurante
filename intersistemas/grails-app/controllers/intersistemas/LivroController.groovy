package intersistemas



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LivroController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Livro.list(params), model:[livroInstanceCount: Livro.count()]
    }

    def show(Livro livroInstance) {
        respond livroInstance
    }

    def create() {
        respond new Livro(params)
    }

    @Transactional
    def save(Livro livroInstance) {
        if (livroInstance == null) {
            notFound()
            return
        }

        if (livroInstance.hasErrors()) {
            respond livroInstance.errors, view:'create'
            return
        }

        livroInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'livro.label', default: 'Livro'), livroInstance.id])
                redirect livroInstance
            }
            '*' { respond livroInstance, [status: CREATED] }
        }
    }

    def edit(Livro livroInstance) {
        respond livroInstance
    }

    @Transactional
    def update(Livro livroInstance) {
        if (livroInstance == null) {
            notFound()
            return
        }

        if (livroInstance.hasErrors()) {
            respond livroInstance.errors, view:'edit'
            return
        }

        livroInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Livro.label', default: 'Livro'), livroInstance.id])
                redirect livroInstance
            }
            '*'{ respond livroInstance, [status: OK] }
        }
    }

    def buscar(String titulo, String autor, String isbn) {

        if(titulo != null || autor != null || isbn != null){
            def lista = Livro.consultarLivroPor(titulo, autor, isbn).list()
            render(view: '/livro/buscar', model: [livros: lista])
        }
        else {
            render(view: '/livro/buscar')
        }
    }

    @Transactional
    def comprar() {
        def compra = Livro.get(params.id)

        try {
            compra.debitarEstoque(1)
            if(!compra.save(flush: true)) {
                throw new RuntimeException(compra.errors)
            }
            flash.message = g.message(code: 'msg.default.success.buy', default: 'Compra realizada com sucesso.')
        }
        catch (RuntimeException erro) {
            flash.error = erro.message
        }

        redirect(action: 'buscar', params: [titulo: compra?.titulo, autor:
        compra?.autor?.nome, isbn: compra?.isbn])

    }

    @Transactional
    def comprarAjax() {

        def listaIds = params.list('checkboxId')
        def quantidade = params.list('qtdCompra')

        def livros = []

        for (int i = 0; i < listaIds.size(); i++) {
            def id = listaIds[i]
            def compra = Livro.get(id)

            livros << compra

            def quantidadeCompra = 1

            try {
                if(quantidade[i] && quantidade[i]!="")
                    quantidadeCompra = quantidade[i] as int

                compra.debitarEstoque(quantidadeCompra)
                if (!compra.save(flush: true)) {
                    throw new RuntimeException(compra.errors)
                }

                flash.message = g.message(code: 'msg.default.success.buy', default: 'Compra realizada com sucesso.')
                render template: 'comprar', model: [livros: livros]
            }
            catch (LivroException le) {
                flash.error = le.message
                render template: 'comprar', model: [livros: livros]
            }catch (RuntimeException erro) {
                render erro.message, status: INTERNAL_SERVER_ERROR
            }
        }
    }

    @Transactional
    def delete(Livro livroInstance) {

        if (livroInstance == null) {
            notFound()
            return
        }

        livroInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Livro.label', default: 'Livro'), livroInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'livro.label', default: 'Livro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

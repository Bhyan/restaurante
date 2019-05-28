package restaurante

class ConsultaController {

    def index() {

        def lista = Cliente.findAll()

        render(view: "index", model: [lista: lista.nome])
    }
}

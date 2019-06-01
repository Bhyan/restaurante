package restaurante

import grails.converters.JSON

class VendaController {

    def vendaService

    def index() {
        def produtos = Produto.list()
        render(view: "index", model: [produtos: produtos])
    }

    def salvar() {
        def produto = params.produto

        def quantidade = params.quantidade

        def mensagem = [:]

        try{
            vendaService.salvar(params.nome, params.email, params.produto.toList(), params.quantidade.toList())
            mensagem += ["resposta": "OK"]
        }
        catch(Exception erro){
            mensagem += ["resposta": erro.message]
        }

        render mensagem as JSON
    }
}

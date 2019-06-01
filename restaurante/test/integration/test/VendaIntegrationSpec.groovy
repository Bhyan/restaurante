package test

import grails.test.spock.IntegrationSpec
import restaurante.Estoque
import restaurante.Produto
import restaurante.VendaController

class VendaIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "vender 1 produto"() {
        given:
            Produto produto = new Produto()
            produto.nome = nomeProduto
            produto.preco = precoProduto
            Estoque estoque = new Estoque()
            estoque.quantidade = quantidadeEstoque
            estoque.quantidadeMinima = quantidadeMinimaEstoque
            produto.setEstoque(estoque)
            produto = produto.save(flush: true)

        VendaController controller = new VendaController()

        def produtos = []
        produtos += produto.id

        def quantidades = []
        quantidades += 2

        controller.params.nome = nomeCliente
        controller.params.email = emailCliente
        controller.params.produto = produtos
        controller.params.quantidade = quantidades

        when:
            controller.salvar()

        then:
            controller.response.json.resposta == "OK"

        where:
            nomeProduto | precoProduto | quantidadeEstoque | quantidadeMinimaEstoque | quantidadeComprada | nomeCliente | emailCliente
            "Coca-Cola" | 10d          | 10                | 5                       | 1                  | "Eu"        | "eu@gmail.com"
            "Paçoquita" | 2d           | 100               | 90                      | 20                 | "Eu"        | "eu@gmail.com"
    }
}

package restaurante

import grails.transaction.Transactional
import org.terracotta.modules.ehcache.store.nonstop.ExceptionOnTimeoutStore

@Transactional
class VendaService {

    def salvar(String nome, String email, List produtos, List quantidades){

        Cliente cliente = Cliente.findByEmail(email)

        if(cliente == null) {
            cliente = new Cliente()
            cliente.nome = nome
            cliente.email = email
            cliente.senha = "123456"
            cliente.validate()

            if(!cliente.hasErrors()) {
                cliente = cliente.save(flush: true)
            }
            else{
                throw new Exception("Não foi possivel salvar o cliente.")
            }
        }

        Pedido pedido = new Pedido()
        pedido.cliente = cliente
        pedido.dataHora = new Date()

        quantidades.eachWithIndex { qtde, i ->
            if(qtde?.toInteger() > 0){
                Integer idProduto = produtos[i].toInteger()

                Produto produto = Produto.get(idProduto)

                ItemPedido item = new ItemPedido()
                item.produto = produto
                item.quantidade = qtde.toInteger()
                item.valorVenda = produto.preco

                pedido.addToItens(item)
            }
        }

        pedido.valorTotal = pedido.itens.sum{it.valorVenda * it.quantidade}

        try {
            pedido.validate()
            if (!pedido.hasErrors()) {
                pedido = pedido.save(flush: true)

                pedido.itens.each { ItemPedido item ->
                    Produto produto = item.produto
                    produto.estoque.quantidade -= item.quantidade
                    produto.estoque.save(flush: true)
                }
            } else{
                throw new Exception("Preencha todos os campos.")
            }
        }
        catch(Exception erro) {
            throw new Exception("Erro ao salvar o pedido: " + erro.message)
        }
    }

}

package restaurante

class ItemPedido {

    Integer quantidade
    Double valorVenda
    String observacao
    Produto produto
    Pedido pedido

    static constraints = {
    }

    static belongsTo = [Pedido]

    static mapping = {
        produto column: "id_produto"
        pedido column: "id_pedido"
    }
}

package restaurante

class Produto {

    String nome
    Double preco
    Estoque estoque

    static hasMany = [cliente: Cliente, itens: ItemPedido]

    static belongsTo = [Cliente]

    static constraints = {
    }

    static mapping = {
        estoque column: "id_estoque"
        cliente joinTable: [
            name: "preferencias_clientes",
            key:"id_produto",
            column: "id_cliente"
        ]
    }
}

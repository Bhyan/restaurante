package restaurante

class Pedido {

    Date data
    Double valorTotal
    Cliente client

    static hasMany = [itens: ItemPedido]
    static constraints = {
    }

    static mapping = {
        cliente column: "id_cliente"
    }
}

package restaurante

class Pedido {

    Date dataHora
    Double valorTotal
    Cliente client

    static hasMany = [itens: ItemPedido]

    static constraints = {
        client nullable: false
    }

    static mapping = {
        cliente column: "id_cliente"
    }
}

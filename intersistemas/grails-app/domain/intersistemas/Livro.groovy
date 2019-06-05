package intersistemas

class Livro {

    String titulo
    String isbn
    BigDecimal preco
    Integer quantidade
    Autor autor

    static belongsTo = [autor: Autor]

    def debitarEstoque(Integer qtd) {
        if (qtd > quantidade || qtd <= 0) {
            throw new LivroException(livro: this,message: "Quantidade invalida.")
        }

        quantidade -= qtd
    }

    static constraints = {
        titulo nullable: false, blank: false, unique: true
        autor nullable: false, blank: false
        isbn nullable: false, blank: false
        preco min: new BigDecimal(0)
        quantidade min: 0
    }

    static namedQueries = {
        consultarLivroPor { String tituloConsulta, String autorConsulta, String isbnConsulta ->
            or {
                if (tituloConsulta)
                    like('titulo', "%${tituloConsulta}%")
                if (autorConsulta) {
                    autor {
                        like('nome', "%${autorConsulta}%")
                    }
                }
                if (isbnConsulta)
                    like('isbn', "%${isbnConsulta}%")
            }
        }
    }
}

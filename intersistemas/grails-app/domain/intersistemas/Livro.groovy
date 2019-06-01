package intersistemas

class Livro {

    String titulo
    String isbn
    BigDecimal preco
    Integer quantidade
    Autor autor

    static belongsTo = [autor: Autor]

    def debitarEstoque(Integer qtd) {
        if (qtd > quantidade)
            throw new RuntimeException("Quantidade invalida.")

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
        consultarLivroPor { String titulo, String pautor, String isbn ->
            or {
                if (titulo)
                    like('titulo', "%${titulo}%")
                if (pautor)
                    autor{
                        like('nome', "%${pautor}%")
                    }
                    // like('autor', "%${autor}%")
                    //"select livro from Autor livro join livro.Autor autorLivro where autorLivro.name = '%${autor}'"
                if (isbn)
                    like('isbn', "%${isbn}%")
            }
        }
    }
}

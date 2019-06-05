package test

import grails.test.spock.IntegrationSpec
import intersistemas.Autor
import intersistemas.Livro

class LivroIntegrationSpec extends IntegrationSpec {

    def setup() {}

    def cleanup() {}

    def "Compra 1 livro"() {
        given:
            Autor autor = new Autor()
            autor.nome = nomeAutor
            autor.idade = idadeAutor
            autor.save(flush: true)

            Livro livro = new Livro()
            livro.titulo = tituloLivro
            livro.autor = autor
            livro.isbn = isbnLivro
            livro.preco = precoLivro
            livro.quantidade = quantitadeLivro
            livro.save(flush: true)

        when:
            livro.debitarEstoque(1)

        then:
            livro.quantidade == quantitadeLivro - 1

        where:
            nomeAutor        | idadeAutor | tituloLivro                               | isbnLivro         | precoLivro | quantitadeLivro
            "George Orwell"  |         46 | "1984"                                    |"014-118-295-4"    |      35.00 |             100
            "Stephen Hawking"|         76 | "Uma Breve História do Tempo"             |"978-0-553-10953-5"|      42.00 |             100
            "Dave Eggers"    |         49 | "Uma Comovente Obra de Espantoso Talento" |"0-684-86347-2"    |      40.00 |             100
            "Ishmael Beah"   |         38 | "Muito Longe de Casa"                     |"8500021217"       |      40.00 |             100
            "Lemony Snicket" |         49 | "Mau Começo"                              |"972-710-304-9"    |      38.00 |             100
    }

    def "Comprando mais livros que a quantidade no estoque"() {
        given:
            Autor autor = new Autor()
            autor.nome = "George Orwell"
            autor.idade = 46
            autor.save(flush: true)

            Livro livro = new Livro()
            livro.titulo = "1984"
            livro.autor = autor
            livro.isbn = "014-118-295-4"
            livro.preco = 30.00
            livro.quantidade = 100
            livro.save(flush: true)

        when:
            livro.debitarEstoque(101)

        then:
            thrown RuntimeException
    }

    def "Cosulta por autor"() {
        given:
            Autor autor = new Autor()
            autor.nome = "George Orwell"
            autor.idade = 46
            autor.save(flush: true)

            Livro livro = new Livro()
            livro.titulo = "1984"
            livro.autor = autor
            livro.isbn = "014-118-295-4"
            livro.preco = 30.00
            livro.quantidade = 100
            livro.save(flush: true)

        expect:
            def qtd = Livro.consultarLivroPor(null, "George Orwell", null).count()
            qtd == 1

    }
}

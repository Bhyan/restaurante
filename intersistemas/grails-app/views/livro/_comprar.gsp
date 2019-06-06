<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:if test="${flash.error}">
    <div class="errors" role="status">${flash.error}</div>
</g:if>
<table>
    <thead>
    <tr>

        <g:checkBox name="checkboxAll" id="checkboxAll"
                    style="float: right; margin-right: 93px; margin-bottom: 11px"></g:checkBox>

        <g:sortableColumn property="titulo" title="${message(code: 'livro.titulo.label', default: 'Título')}"/>

        <g:sortableColumn property="autor" title="${message(code: 'livro.autor.label', default: 'Autor')}"/>

        <g:sortableColumn property="isbn" title="${message(code: 'livro.isbn.label', default: 'Isbn')}"/>

        <g:sortableColumn property="preco" title="${message(code: 'livro.preco.label', default: 'Preço')}"/>

        <g:sortableColumn property="quantidade"
                          title="${message(code: 'livro.quantidade.label', default: 'Quantidade')}"/>

        <g:sortableColumn property="comprar" title="${message(code: 'livro.comprar.label', default: 'Comprar')}"/>

    </tr>
    </thead>
    <tbody>
    <g:each var="livro" in="${livros}">
        <tr>
            <td>
                <g:link action="show" id="${livro?.id}">
                    ${fieldValue(bean: livro, field: "titulo")}
                </g:link>
            </td>

            <td>${livro?.autor?.nome}</td>

            <td>${livro?.isbn}</td>

            <td id="precoUni_${livro?.id}">${livro?.preco}</td>

            <td>${livro?.quantidade}</td>

            <td><g:checkBox name="checkbox_${livro?.id}" id='checkboxId_${livro?.id}' class="checkboxId" value="${livro?.id}"/></td>

            <td><input type="number" id="qtdCompra_${livro?.id}" class="qtdCompra" min="0" max="${livro?.quantidade}"></td>

        </tr>

    </g:each>
    </tbody>
</table>

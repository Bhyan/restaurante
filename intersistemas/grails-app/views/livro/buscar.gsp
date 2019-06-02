<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Buscar Livro</title>

</head>

<body>

    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
    </div>
    <g:form name="frmBusca" url="[controller: 'livro', action: 'buscar']">
    <div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'titulo', 'error')} required">
        <label for="titulo">
            <g:message code="livro.titulo.label" default="Título"/>
        </label>
        <input type="text" name="titulo" ><br>
    </div>
    <div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'autor', 'error')} required">
        <label for="autor">
            <g:message code="livro.autor.label" default="Autor" />
        </label>
        <input type="text" name="autor" ><br>
    </div>
    <div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'isbn', 'error')} required">
        <label for="isbn">
            <g:message code="livro.isbn.label" default="ISBN" />
        </label>
        <input type="text" name="isbn" ><br><br>
    </div>
        <fieldset class="buttons">
            <g:submitButton name="btnBuscar" value="Buscar" />
        </fieldset>
        <br>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:if test="${flash.error}">
            <div class="errors" role="status">${flash.error}</div>
        </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="titulo" title="${message(code: 'livro.titulo.label', default: 'Título')}" />

            <g:sortableColumn property="autor" title="${message(code: 'livro.autor.label', default: 'Autor')}" />

            <g:sortableColumn property="isbn" title="${message(code: 'livro.isbn.label', default: 'Isbn')}" />

            <g:sortableColumn property="preco" title="${message(code: 'livro.preco.label', default: 'Preço')}" />

            <g:sortableColumn property="quantidade" title="${message(code: 'livro.quantidade.label', default: 'Quantidade')}" />

            <g:sortableColumn property="quantidade" title="Comprar" />
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

                    <td>${livro?.preco}</td>

                    <td>${livro?.quantidade}</td>

                    <td><g:link action="comprar" id="${livro.id}">Comprar</g:link></td>

                </tr>

            </g:each>
        </tbody>
    </table>
    </g:form>
</body>
</html>

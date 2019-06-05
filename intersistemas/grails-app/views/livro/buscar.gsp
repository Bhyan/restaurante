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
    <li style="float: right; margin-top: -25px; display: flex;">
        <sec:ifLoggedIn>
            <h4 style="text-transform: capitalize; margin-top: 5px; margin-right: 5px">${session.user}</h4>
            <a href="/intersistemas/j_spring_security_logout">Sair</a>
        </sec:ifLoggedIn>
    </li>
</div>
<g:form name="frmBusca" url="[controller: 'livro', action: 'buscar']">
    <div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'titulo', 'error')} required">
        <label for="titulo">
            <g:message code="livro.titulo.label" default="Título"/>
        </label>
        <input type="text" name="titulo"><br>
    </div>

    <div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'autor', 'error')} required">
        <label for="autor">
            <g:message code="livro.autor.label" default="Autor"/>
        </label>
        <input type="text" name="autor"><br>
    </div>

    <div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'isbn', 'error')} required">
        <label for="isbn">
            <g:message code="livro.isbn.label" default="ISBN"/>
        </label>
        <input type="text" name="isbn"><br><br>
    </div>
    <fieldset class="buttons">
        <g:submitButton name="btnBuscar" value="Buscar"/>
    </fieldset>
    <br>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
    </g:if>

    <div id="divCompra">
        <g:render template="comprar"></g:render>
    </div>
</g:form>

<a name="btnComprar" value="Comprar" id="comprar">Comprar</a>

<script type="text/javascript">
    $(document).ready(function () {

        $("#comprar").click(function () {

            const data = $('input').serialize()

            //AJAX aqui

            $.ajax({
                method: "POST",
                url: "${createLink(controller: 'livro', action: 'comprarAjax')}",
                data: data
            }).done(function (data) {
                $("#divCompra").html(data)
                // alert("success -"+data);
            }).fail(function (jqXHR, textStatus ) {
                alert("error - "+jqXHR.responseText);
            }).always(function () {

            });


            // $(':checkbox:checked').each(function () {
            //     console.log($(this).attr("id"));
            // });
        });


        $("#checkboxAll").click(function () {
            $(".checkboxId").prop('checked', $(this).prop('checked'));
        });
    });
</script>
</body>

</html>


<%@ page import="intersistemas.Livro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livro.label', default: 'Livro')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-livro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-livro" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="titulo" title="${message(code: 'livro.titulo.label', default: 'Titulo')}" />
					
						<th><g:message code="livro.autor.label" default="Autor" /></th>
					
						<g:sortableColumn property="isbn" title="${message(code: 'livro.isbn.label', default: 'Isbn')}" />
					
						<g:sortableColumn property="preco" title="${message(code: 'livro.preco.label', default: 'Preco')}" />
					
						<g:sortableColumn property="quantidade" title="${message(code: 'livro.quantidade.label', default: 'Quantidade')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${livroInstanceList}" status="i" var="livroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${livroInstance.id}">${fieldValue(bean: livroInstance, field: "titulo")}</g:link></td>

						<td>${fieldValue(bean: autorInstance, field: "autor")}</td>

						<td>${fieldValue(bean: livroInstance, field: "isbn")}</td>
					
						<td>${fieldValue(bean: livroInstance, field: "preco")}</td>
					
						<td>${fieldValue(bean: livroInstance, field: "quantidade")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${livroInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

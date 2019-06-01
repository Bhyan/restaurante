<%@ page import="intersistemas.Livro" %>



<div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'titulo', 'error')} required">
	<label for="titulo">
		<g:message code="livro.titulo.label" default="Titulo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="titulo" required="" value="${livroInstance?.titulo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'autor', 'error')} required">
	<label for="autor">
		<g:message code="livro.autor.label" default="Autor" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="autor" required="" value="${livroInstance?.autor}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'isbn', 'error')} required">
	<label for="isbn">
		<g:message code="livro.isbn.label" default="Isbn" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="isbn" required="" value="${livroInstance?.isbn}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'preco', 'error')} required">
	<label for="preco">
		<g:message code="livro.preco.label" default="Preco" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="preco" value="${fieldValue(bean: livroInstance, field: 'preco')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: livroInstance, field: 'quantidade', 'error')} required">
	<label for="quantidade">
		<g:message code="livro.quantidade.label" default="Quantidade" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantidade" type="number" min="0" value="${livroInstance.quantidade}" required=""/>

</div>


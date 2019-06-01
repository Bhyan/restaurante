<%@ page import="intersistemas.Autor" %>



<div class="fieldcontain ${hasErrors(bean: autorInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="autor.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${autorInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: autorInstance, field: 'idade', 'error')} required">
	<label for="idade">
		<g:message code="autor.idade.label" default="Idade" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="idade" type="number" min="0" value="${autorInstance.idade}" required=""/>

</div>


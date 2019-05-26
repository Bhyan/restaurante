<g:if test="${produtos.size() > 0}">
<table>
  <tr>
    <th>Nome</th>
    <th>Preço</th>
    <th>Qtde. Atual</th>
    <th>Qtde. Minima</th>
    <th>Ações</th>
  </tr>

  <g:each var="produto" in="${produto}">
    <tr>
      <th>${produto.nome}</th>
      <th>${produto.preco}</th>
      <th>${produto.estoque?.quantidade}</th>
      <th>${produto.estoque?.quantidadeMinima}</th>
      <th>
        <g:remoteLink controller="produto" action="alterar" update="divForm" id="${produto.id}"></g:remoteLink>
        <a href="#" onclick="excluir('${produto.id}')">Excluir</a>
      </th>
    </tr>
  </g:each>
</table>
</g:if>
<g:else>
  Não há produto para listar.
</g:else>

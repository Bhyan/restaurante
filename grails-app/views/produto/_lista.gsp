<g:if test="${produtos?.size() > 0}">
<table>
  <tr>
    <th>Nome</th>
    <th>Preço</th>
    <th>Qtde. Atual</th>
    <th>Qtde. Minima</th>
    <th>Ações</th>
  </tr>

  <g:each var="produto" in="${produtos}">
    <tr>
      <td>${produto.nome}</td>
      <td>${produto.preco}</td>
      <td>${produto.estoque?.quantidade}</td>
      <td>${produto.estoque?.quantidadeMinima}</td>
      <td>
        <g:remoteLink controller="produto" action="alterar" update="divForm" id="${produto.id}"></g:remoteLink>
        <a href="#" onclick="excluir('${produto.id}')">Excluir</a>
      </td>
    </tr>
  </g:each>
</table>
</g:if>
<g:else>
  Não há produto para listar.
</g:else>

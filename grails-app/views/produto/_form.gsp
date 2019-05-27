<div name="divMensagem"></div>
<g:formRemote name="frmProduto" url="[controller: 'produto', action: 'salvar']" update="divMensagem" onSuccess="carregarLista()">
  Nome <input type="text" name="nome" value="${produtos.nome}"><br>
  Preço <input type="text" name="preco" value="${produtos.preco}"><br>
  Qtde. atual <input type="text" name="quantidade" value="${produtos.estoque?.quantidade}"><br>
  Qtde. minima <input type="text" name="quantidadeMinima" value="${produtos.estoque?.quantidadeMinima}"><br>
  <input type="submit" name="btnSalvar" value="Salvar">
  <input type="button" name="btnCancelar" value="Cancelar" onclick="cancelar()">
  <input type="hidden" name="id" value="${produtos.id}">
</g:formRemote>

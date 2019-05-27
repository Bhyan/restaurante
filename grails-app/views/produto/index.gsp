<!DOCTYPE html>
<html>
	<head>
	  <meta name="layout" content="main"/>
	  <title>Gerenciamento de produtos</title>

    <script type="text/javascript">
      function carregarLista(){
        <g:remoteFunction controller="produto" action="listar" update="divLista" />
      }
      function excluir(id){
        if (confirm("Deseja realmente excluir?")){
          <g:remoteFunction controller="produto" action="excluir" update="divLista" id="'+id+'" />
        }
      }
      function cancelar(){
        jQuery("#divForm").html("")
      }
    </script>

	</head>
	<body>
	  <g:remoteLink controller="produto" action="adicionar" update="divForm">
      Adicionar
    </g:remoteLink>

	  <div id="divLista">
	    <g:render template="lista" model="[produto: produtos]"></g:render>
	  </div>
	  <div id="divForm"></div>
	</body>
</html>

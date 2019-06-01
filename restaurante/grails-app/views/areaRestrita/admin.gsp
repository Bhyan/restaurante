<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Área Restrita - Restaurante</title>
	</head>
	<body>
    <sec:ifLoggedIn>
      Olá, ${usuario}!
      <a href="/Restaurante/j_spring_security_logout">Sair</a>
    </sec:ifLoggedIn>
	</body>
</html>

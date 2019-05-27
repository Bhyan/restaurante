<table>
    <thead>
    <th>Nome do usuário</th>
    </thead>
    <g:each in="${usuarios}" var="usuario">
        <tr>
            <td>${usuario.username}</td>
        </tr>
    </g:each>
</table>
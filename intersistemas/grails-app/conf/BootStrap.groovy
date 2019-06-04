import comum.Mapeamento
import comum.Permissao
import comum.Usuario
import comum.UsuarioPermissao

class BootStrap {

    def init = { servletContext ->

        if (!Mapeamento.count())
            for (String url in [
                    '/**/favicon.ico',
                    '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                    '/login', '/login.*', '/login/*',
                    '/logout', '/logout.*', '/logout/*']) {
                new Mapeamento(url: url, configAttribute: 'permitAll').save()
            }

        def role_admin = Permissao.findOrSaveWhere(authority: "ROLE_ADMIN")

        Usuario admin = Usuario.findOrSaveWhere(username: "admin", password: "admin", accountExpired: false,
                accountLocked: false, passwordExpired: false).save(flush: true)

        UsuarioPermissao.findOrSaveWhere(usuario: admin, permissao: role_admin)

        Mapeamento.findOrSaveWhere(url: "/livro/**", configAttribute: role_admin?.authority)
        Mapeamento.findOrSaveWhere(url: "/", configAttribute: role_admin?.authority)
        Mapeamento.findOrSaveWhere(url: "/autor/**", configAttribute: role_admin?.authority)

    }

    def destroy = {
    }
}

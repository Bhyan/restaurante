import comum.Mapeamento
import comum.Permissao
import comum.Usuario
import comum.UsuarioPermissao

class BootStrap {

    def init = { servletContext ->

        def role_admin = Permissao.findOrSaveWhere(authority: "ROLE_ADMIN")
        def role_user = Permissao.findOrSaveWhere(authority: "ROLE_USER")

        Usuario admin = Usuario.findOrSaveWhere(username: "admin", password: "admin", accountExpired: false,
                accountLocked: false, passwordExpired: false).save(flush: true)

        UsuarioPermissao.findOrSaveWhere(usuario: admin, permissao: role_admin)


        if (!Mapeamento.count()) {
            for (String url in [
                    '/**/favicon.ico',
                    '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                    '/login', '/login.*', '/login/*',
                    '/logout', '/logout.*', '/logout/*']) {
                new Mapeamento(url: url, configAttribute: 'permitAll').save()
            }
            for (String url in [ "/livro/**", "/", "/autor/**", "/usuario/**", "/permissao/**", "/usuarioPermissao/**" ]) {
                new Mapeamento(url: url, configAttribute: 'ROLE_ADMIN').save()
            }

            Mapeamento.findOrSaveWhere(url: "/livro/**", configAttribute: role_user?.authority)
        }

    }

    def destroy = {
    }
}

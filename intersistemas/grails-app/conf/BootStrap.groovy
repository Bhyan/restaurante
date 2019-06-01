import comum.Mapeamento
import comum.Permissao
import comum.Usuario
import comum.UsuarioPermissao

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
            /**
//             * Criar os papeis existentes
//             */
//            def role_admin = Permissao.findOrSaveWhere(authority: "ROLE_ADMIN")
//
//             /**
//             *  Criar os mapeamentos
//             */
//            for (String url in [
//                    '/', '/index', '/**/favicon.ico',
//                    '/**/js/**', '/**/css/**', '/**/images/**',
//                    '/login', '/login/**', '/logout', '/logout/**']) {
//                def map = Mapeamento.findOrSaveWhere(url: url, configAttribute: 'permitAll')
//            }
//
//            springSecurityService.clearCachedRequestmaps()
//
//            Usuario admin = Usuario.findOrSaveWhere(username: "admin", password: "1234", accountExpired: false,
//                    accountLocked: false, passwordExpired: false).save(flush: true)
//
//            def permissao = UsuarioPermissao.findOrSaveWhere(usuario: admin, permissao: role_admin)
    }

    def destroy = {
    }
}

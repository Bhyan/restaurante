package restaurante

import comum.Permissao
import comum.Usuario
import grails.converters.JSON

class ControlePermissaoController {

    def index() {
		render(view: "index")
	}

	def listarUsuario() {
		def listaUsuarios = Usuario.createCriteria().list{
			order("username")
		}
		render(template: "listaUsuarios", model:[usuarios: listaUsuarios])
	}

	def listarPermissao() {
		def listaPermissoes = Permissao.list()
		render(template: "listaPermissoes", model: [permissoes: listaPermissoes])
	}

	def salvarPermissao() {
		def retorno = [:]
		Permissao permissao = new Permissao()
		permissao.authority = params.permissao
		permissao.validate()

		if(!permissao.hasErrors()) {
			permissao.save(flush: true)
			retorno["mensagem"] = "OK"
		}
		else {
			retorno["mensagem"] = "ERROR"
		}

		render retorno as JSON
	}

	def getPermissao() {
		Permissao permissao = Permissao.get(params.id)
		render permissao as JSON
	}

	def salvarUsuario() {
		def retorno = [:]

		Usuario usuario = new Usuario()
		usuario.username = params.login
		usuario.enabled = true
		usuario.passwordExpired = false
		usuario.accountExpired = false
		usuario.accountLocked = false
		usuario.password = "1234"

		usuario.validate()
		if(!usuario.hasErrors()) {
			usuario.save(flush: true)
			retorno["mensagem"] = "OK"
		}
		else {
			retorno["mensagem"] = "ERRO"
		}

		render retorno as JSON
	}
}

package restaurante

class AreaRestritaController {

    def index() { }

    def logar() {
        render(view: "/areaRestrita/logar")
    }

    def admin() {
            String usuario = springSecurityService.principal.username
        render(view: "/areaRestrita/admin")
    }

    def logout() {
        redirect(action: "logar")
    }
}

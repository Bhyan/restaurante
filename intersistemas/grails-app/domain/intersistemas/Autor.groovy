package intersistemas

class Autor {

    String nome
    Integer idade

    static constraints = {
        nome nullable: false, blank: false, unique: true
        idade min: new Integer(0)
    }
}

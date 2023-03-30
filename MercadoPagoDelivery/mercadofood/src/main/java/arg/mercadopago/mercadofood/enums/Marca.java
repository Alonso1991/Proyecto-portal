package arg.mercadopago.mercadofood.enums;

public enum Marca {

    BK(1, "BK"),
    UNKNOWN(0, "-");

    public static Marca getById(Integer id) {
        for (Marca e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }

        return UNKNOWN;
}

    private String descripcion;

    private Integer id;

    Marca(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getId() {
        return id;
    }
}

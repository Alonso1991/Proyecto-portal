package arg.mercadopago.mercadofood.enums;

public enum StatusOrder {

    ACCEPT(1, "ACCEPT"), REJECTED(2, "REJECTED"), CANCELLED(3, "CANCELLED"),SIN_REPARTIDOR(4, "SIN_REPARTIDOR"),
    UNKNOWN(0, "-");

    public static StatusOrder getById(Integer id) {
        for (StatusOrder e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }

        return UNKNOWN;
    }

    private String descripcion;

    private Integer id;

    StatusOrder(Integer id, String descripcion) {
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

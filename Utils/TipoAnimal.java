package Utils;

public enum TipoAnimal {
    CACHORRO("CACHORRO"),
    GATO("GATO");

    private final String tipo;

    TipoAnimal(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

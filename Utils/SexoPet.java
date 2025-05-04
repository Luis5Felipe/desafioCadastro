package Utils;

public enum SexoPet {
    MACHO("Macho"),
    FEMEA("Femea");

    private final String sexoPet;

    SexoPet(String sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getSexoPet() {
        return sexoPet;
    }
}

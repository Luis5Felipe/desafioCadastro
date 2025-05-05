package Utils;

public enum SexoPet {
    MACHO("MACHO"),
    FEMEA("FEMEA");

    private final String sexoPet;

    SexoPet(String sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getSexoPet() {
        return sexoPet;
    }
}

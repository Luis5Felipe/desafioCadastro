package Utils;

public enum SexoPet {
    Macho("Macho"),
    Femea("Femêa");

    private final String sexoPet;

    SexoPet(String sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getSexoPet() {
        return sexoPet;
    }
}

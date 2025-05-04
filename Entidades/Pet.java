package Entidades;

import Utils.SexoPet;
import Utils.TipoAnimal;

public class Pet {
    private String nome;
    private SexoPet sexoPet;
    private TipoAnimal tipoAnimal;
    private String endereco;
    private double idade;
    private double peso;
    private String race;

    public Pet() {

    }

    public Pet(String endereco, double idade, String nome, double peso, String race, SexoPet sexoPet, TipoAnimal tipoAnimal) {
        this.endereco = endereco;
        this.idade = idade;
        this.nome = nome;
        this.peso = peso;
        this.race = race;
        this.sexoPet = sexoPet;
        this.tipoAnimal = tipoAnimal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public SexoPet getSexoPet() {
        return sexoPet;
    }

    public void setSexoPet(SexoPet sexoPet) {
        this.sexoPet = sexoPet;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}

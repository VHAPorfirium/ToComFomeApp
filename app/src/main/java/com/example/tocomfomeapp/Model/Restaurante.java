package com.example.tocomfomeapp.Model;

public class Restaurante {
    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private String descricao;
    private float estrelas;

    public Restaurante(String nome, String endereco, String bairro, String cidade, String descricao, float estrelas) {
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.descricao = descricao;
        this.estrelas = estrelas;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public float getEstrelas() { return estrelas; }
    public void setEstrelas(float estrelas) { this.estrelas = estrelas; }
}

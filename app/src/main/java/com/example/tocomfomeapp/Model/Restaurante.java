package com.example.tocomfomeapp.Model;
// Este pacote agrupa as classes que representam os dados (modelo) do nosso app.

public class Restaurante {
    // Declaro as variáveis de instância para armazenar os dados do restaurante.
    private String nome;         // Armazena o nome do restaurante.
    private String endereco;     // Armazena o endereço do restaurante.
    private String bairro;       // Armazena o bairro onde o restaurante está localizado.
    private String cidade;       // Armazena a cidade do restaurante.
    private String descricao;    // Armazena uma descrição do restaurante.
    private float estrelas;      // Armazena a avaliação (número de estrelas) do restaurante.
    private String fotoUri;      // Campo para armazenar a URI da foto do restaurante.

    // Construtor da classe, usado para criar uma nova instância de Restaurante com os dados iniciais.
    public Restaurante(String nome, String endereco, String bairro, String cidade, String descricao, float estrelas) {
        this.nome = nome;           // Atribuo o nome recebido ao atributo nome.
        this.endereco = endereco;   // Atribuo o endereço recebido ao atributo endereco.
        this.bairro = bairro;       // Atribuo o bairro recebido ao atributo bairro.
        this.cidade = cidade;       // Atribuo a cidade recebida ao atributo cidade.
        this.descricao = descricao; // Atribuo a descrição recebida ao atributo descricao.
        this.estrelas = estrelas;   // Atribuo a quantidade de estrelas recebida ao atributo estrelas.
        // Note que a fotoUri não é definida aqui; ela será atribuída depois, caso o usuário selecione uma foto.
    }

    // Métodos getter e setter para acessar e modificar os atributos de forma encapsulada.

    // Getter e setter para o nome do restaurante.
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e setter para o endereço.
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Getter e setter para o bairro.
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Getter e setter para a cidade.
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // Getter e setter para a descrição.
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e setter para a avaliação (estrelas).
    public float getEstrelas() {
        return estrelas;
    }
    public void setEstrelas(float estrelas) {
        this.estrelas = estrelas;
    }

    // Getter e setter para a URI da foto.
    public String getFotoUri() {
        return fotoUri;
    }
    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }
}
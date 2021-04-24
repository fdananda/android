package com.fdananda.gitcardview;

public class Cardview {

    private String titulo;
    private String descricao;
    private String autor;
    private int imagem;

    public Cardview() {
    }

    public Cardview(String titulo, String descricao, String autor, int imagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}

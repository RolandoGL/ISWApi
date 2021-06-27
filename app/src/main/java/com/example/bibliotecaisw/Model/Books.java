package com.example.bibliotecaisw.Model;

public class Books {
    private String title, autor, archivo, description,genero,create_at;

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public String getArchivo() {
        return archivo;
    }

    public String getDescription() {
        return description;
    }

    public String getGenero() {
        return genero;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}

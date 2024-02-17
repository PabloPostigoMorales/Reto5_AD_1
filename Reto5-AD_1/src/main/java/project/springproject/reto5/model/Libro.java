package project.springproject.reto5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "libro")

public class Libro implements Serializable {
    public Libro (Long ISBN, String titulo, String autor, String categoria, String edicion) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.edicion = edicion;
    }

    public Libro () {
    }

    @Id
    @Column(name = "ISBN")
    private Long ISBN = null;
    @Column(name = "Titulo")
    private String titulo = null;
    @Column(name = "Autor")
    private String autor = null;
    @Column(name = "Categoria")
    private String categoria = null;
    @Column(name = "Edicion")
    private String edicion = null;


    public boolean noneNull() {
        return ISBN != null && titulo != null && autor != null && categoria != null && edicion != null;
    }

    @Override
    public String toString () {
        return "Libro{" +
                "ISBN='" + ISBN + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                ", edicion='" + edicion + '\'' +
                '}';
    }
}

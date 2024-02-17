package project.springproject.reto5.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.springproject.reto5.model.Libro;
import project.springproject.reto5.repository.LibroRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class LibroController {
    @Autowired
    LibroRepository libroRepository;

    @GetMapping("/all")
    public List<Libro> list(){
        List<Libro> libros = libroRepository.findAll();
        return libros;
    }

    @GetMapping("/ISBN/{ISBN}")
    public ResponseEntity<Libro> getByISBN(@PathVariable Long ISBN){
        ResponseEntity<Libro> libro;
        if (libroRepository.existsById(ISBN)){
            Optional<Libro> lib = libroRepository.findById(ISBN);
            libro = new ResponseEntity<>(lib.get(), HttpStatus.OK);
        }else libro = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return libro;
    }
    @GetMapping("/Categoria/{categoria}")
    public ResponseEntity<List<Libro>> getByCategoria(@PathVariable String categoria){
        ResponseEntity<List<Libro>> libro;
        if (libroRepository.existsByCategoria(categoria)){
            Optional<List<Libro>> lib = Optional.ofNullable(libroRepository.findByCategoria(categoria));
            libro = new ResponseEntity<>(lib.get(), HttpStatus.OK);
        }else libro = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return libro;
    }
    @GetMapping("/Autor/{autor}")
    public ResponseEntity<List<Libro>> getByAutor(@PathVariable String autor){
        ResponseEntity<List<Libro>> libro;
        if (libroRepository.existsByAutor(autor)){
            Optional<List<Libro>> lib = Optional.ofNullable(libroRepository.findByAutor(autor));
            libro = new ResponseEntity<>(lib.get(), HttpStatus.OK);
        }else libro = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return libro;
    }
    
    @PostMapping("/Add/Libro")
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        ResponseEntity<Libro> libroResponse;
        if (libro.noneNull()){
            libroRepository.save(libro);
            libroResponse = new ResponseEntity<Libro>(libro, HttpStatus.CREATED);
        }else libroResponse = new ResponseEntity<Libro>(HttpStatus.I_AM_A_TEAPOT);
        return libroResponse;
    }

    @DeleteMapping("/delete/{ISBN}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable Long ISBN) {
        ResponseEntity<Libro> libroResponse;
        if (libroRepository.existsById(ISBN)) {
            libroRepository.deleteById(ISBN);
            libroResponse = new ResponseEntity<Libro>(HttpStatus.OK);
        }else {
            libroResponse = new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
        }
        return libroResponse;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro){
        ResponseEntity<Libro> salida;
        if (libroRepository.existsById(id)){
            libroRepository.save(libro);
            salida = new ResponseEntity<Libro>(libro,HttpStatus.OK);
        }else salida = new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
        return salida;
    }

}

package co.edu.editorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.editorial.modelo.Libro;
import co.edu.editorial.modelo.Disco;
import co.edu.editorial.modelo.SaludoResponse;
import co.edu.editorial.service.ILibroService;
import co.edu.editorial.service.IDiscoService;
import co.edu.editorial.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.bson.types.ObjectId;

@RestController 
@RequestMapping("/api") 
public class EditorialController {
	
	@Autowired
	private ILibroService libroService;
	
	@Autowired
	private IDiscoService discoService;
	
	
	
	@GetMapping("/saludar/{nombre}")
	public String saludar(@PathVariable String nombre) {
		
		return new String("hola "+ nombre);
	}
	
	@PostMapping("/crear-libro")
	public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
		
		Libro milibro=libro;
		return ResponseEntity.ok().body(this.libroService.crearLibro(milibro));
	}
	
	
	
	@GetMapping("/obtener-libros")
    public ResponseEntity <List<Libro>> obtenerLibros() {
        return ResponseEntity.ok().body(libroService.getTodosLosLibros());
    }
    @GetMapping("/obtener-libro/{id}")
    public ResponseEntity <Libro> obtenerLibroPorId(@PathVariable ObjectId id) {
        return ResponseEntity.ok().body(libroService.getLibroPorId(id));
    }
    
    @DeleteMapping("/eliminar-libro/{id}")
    public HttpStatus eliminarLibro(@PathVariable ObjectId id) {
        this.libroService.eliminarLibro(id);
        return HttpStatus.OK;
    }
	
    
    @PutMapping("/actualizar-libro/{id}")
    public ResponseEntity <Libro> actualizarLibro(@PathVariable ObjectId id, @RequestBody Libro libro) {
        libro.setId(id);
        return ResponseEntity.ok().body(this.libroService.actualizarLibro(libro));
    }
	
    // Endpoints para Discos
    @PostMapping("/crear-disco")
    public ResponseEntity<Disco> crearDisco(@RequestBody Disco disco) {
        return ResponseEntity.ok().body(this.discoService.crearDisco(disco));
    }
    
    @GetMapping("/obtener-discos")
    public ResponseEntity<List<Disco>> obtenerDiscos() {
        return ResponseEntity.ok().body(discoService.getTodosLosDiscos());
    }
    
    @GetMapping("/obtener-disco/{id}")
    public ResponseEntity<Disco> obtenerDiscoPorId(@PathVariable ObjectId id) {
        return ResponseEntity.ok().body(discoService.getDiscoPorId(id));
    }
    
    @DeleteMapping("/eliminar-disco/{id}")
    public HttpStatus eliminarDisco(@PathVariable ObjectId id) {
        this.discoService.eliminarDisco(id);
        return HttpStatus.OK;
    }
    
    @PutMapping("/actualizar-disco/{id}")
    public ResponseEntity<Disco> actualizarDisco(@PathVariable ObjectId id, @RequestBody Disco disco) {
        disco.setId(id);
        return ResponseEntity.ok().body(this.discoService.actualizarDisco(disco));
    }

}

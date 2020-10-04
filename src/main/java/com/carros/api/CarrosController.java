package com.carros.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.DataService;
import com.carros.domain.dto.CarroDTO;


@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service; 
	
	@Autowired
	private DataService serviceData; 

	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> getById(@PathVariable("id") Long id) {
		CarroDTO carro = service.getByIdCarro(id);
		
		return ResponseEntity.ok(carro);
			
		
//		if(optional.isPresent()) {
//			return ResponseEntity.ok(optional.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getByIdTipoCarro(tipo);
		
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}
	
	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<CarroDTO> post(@RequestBody Carro carro) {
			
		CarroDTO c = service.postCarro(carro);
			
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
			
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<CarroDTO> put(@PathVariable("id") Long id, @RequestBody Carro carro) {

        carro.setId(id);

        CarroDTO c = service.updateCarro(carro, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
                
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CarroDTO> deleteByIdCarro(@PathVariable("id") Long id) {
		service.deleteCarro(id);
		
		return ResponseEntity.ok().build();
				
	}
	
	@GetMapping("/data")
	public void mostraDataTeste() {
		serviceData.localDate();
	
	}
	
	@GetMapping("/dataHora")
	public void mostraDataHoraTeste() {
		serviceData.localDateTime();
	}
	
	@DeleteMapping()
	public String deleteCarroObject(@RequestBody Carro carro) {
		service.deleteCarroObjeto(carro);
		
		return "Carro deletado com sucesso";
	}
	
}

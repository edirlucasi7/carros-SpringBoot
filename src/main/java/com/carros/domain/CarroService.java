package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;
import com.carros.domain.exception.ObjectNotFoundException;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros() {
		List<Carro> carros = rep.findAll();
		
//		List<CarroDTO> list = carros.stream().map(CarroDTO::new).collect(Collectors.toList());
		
		List<CarroDTO> list = new ArrayList<>();
		for(Carro c : carros) {
			list.add(CarroDTO.update(c));
		}
		
		return list;
	}
	
	public CarroDTO getByIdCarro(Long id) {
		return rep.findById(id).map(CarroDTO::update).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
	}
	
	public List<CarroDTO> getByIdTipoCarro(String tipo) {
		List<Carro> carros = rep.findByTipo(tipo);
		
		List<CarroDTO> list = carros.stream().map(CarroDTO::update).collect(Collectors.toList());
		
//		List<CarroDTO> list = new ArrayList<>();
//		for(Carro c : carros) {
//			list.add(new CarroDTO(c));
//		}
//		
		return list;
	}

	public CarroDTO postCarro(Carro carro) {
		Assert.isNull(carro.getId(), "O carro não pode ser inserido!");
		
		return CarroDTO.update(rep.save(carro));
	}
	
    public CarroDTO updateCarro(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            // Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return CarroDTO.update(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }
	
	public void deleteCarro(Long id) {
		rep.deleteById(id);
	}
		
		
		
		
//		Optional<Carro> optional = rep.findById(id);
//		
//		if(optional.isPresent()) {
//			Carro db = optional.get();
//			rep.deleteById(db.getId());
//			
//			return CarroDTO.update(db);
//		} else {
//			return null;
//		}
//	}

	
	public void deleteCarroObjeto(Carro carro) {
		rep.delete(carro);
	}

}

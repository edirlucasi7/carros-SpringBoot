package com.carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import com.carros.domain.exception.ObjectNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTest {

    @Autowired
    private CarroService service;

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        CarroDTO c = service.postCarro(carro);

        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);

        // Buscar o objeto
        c = service.getByIdCarro(id);
        assertNotNull(c);

        assertEquals("Porshe",c.getNome());
        assertEquals("esportivos",c.getTipo());

        // Deletar o objeto
        service.deleteCarro(id);

        // Verificar se deletou
        try {
            service.getByIdCarro(id);
            fail("O carro não foi excluído");
        } catch (ObjectNotFoundException e) {
            // OK
        }
    }

    @Test
    public void testLista() {

        List<CarroDTO> carros = service.getCarros();

        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, service.getByIdTipoCarro("classicos").size());
        assertEquals(10, service.getByIdTipoCarro("esportivos").size());
        assertEquals(10, service.getByIdTipoCarro("luxo").size());

        assertEquals(0, service.getByIdTipoCarro("x").size());
    }

    @Test
    public void testGet() {

        CarroDTO c = service.getByIdCarro(11L);

        assertNotNull(c);


        assertEquals("Ferrari FF", c.getNome());
    }
}
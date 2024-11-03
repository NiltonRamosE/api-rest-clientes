package com.sistemas.controlador;

import com.sistemas.dominio.Cliente;
import com.sistemas.servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/cliente", produces="application/json")
@CrossOrigin(origins="*")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity <List<Cliente>> getIndex() {

        return new ResponseEntity<>(clienteService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Cliente> getClienteById(
            @PathVariable("id") Long id) {

        Cliente buscado = clienteService.buscar(id);

        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Cliente(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity <Cliente> crearCliente(
            @RequestBody Cliente cliente) {

        Cliente newCliente = clienteService.agregar(cliente);

        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity <Cliente> actualizarCliente(
            @RequestBody Cliente cliente) {

        Cliente putCliente = clienteService.actualizar(cliente);
        return new ResponseEntity<>(putCliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Cliente> eliminarCliente(@PathVariable("id") Long id) {

        clienteService.eliminar(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

package br.com.ifpe.oxefood.api.cliente;

import java.util.List;

import javax.validation.Valid;

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

import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import br.com.ifpe.oxefood.util.entity.GenericController;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoClienteService;

@RestController
@RequestMapping("/api/enderecocliente")
public class EnderecoClienteController extends GenericController {

    @Autowired
    private EnderecoClienteService enderecoClienteService;

    @PostMapping
    public ResponseEntity<EnderecoCliente> save(@RequestBody @Valid EnderecoClienteRequest request) {

        EnderecoCliente enderecoClienteNovo = request.build();
        EnderecoCliente enderecoCliente = enderecoClienteService.save(enderecoClienteNovo);
        return new ResponseEntity<EnderecoCliente>(enderecoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EnderecoCliente> listarTodos() {
  
       return enderecoClienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public EnderecoCliente obterPorID(@PathVariable Long id) {

       return enderecoClienteService.obterPorID(id);
    }


    //alterado
@PutMapping("/{id}")
    public ResponseEntity<EnderecoCliente> update(@PathVariable("id") Long id, @RequestBody EnderecoCliente request) {

        enderecoClienteService.update(id, request.build());
        return ResponseEntity.ok().build();
    }



       {/* @PutMapping("/enderecocliente/{id}")
    public ResponseEntity<EnderecoCliente> updateEnderecoCliente(@PathVariable("id") Long id, @RequestBody EnderecoCliente request) {

        enderecoClienteService.update(id, request.build());
        return ResponseEntity.ok().build();
    }*/}

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        enderecoClienteService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
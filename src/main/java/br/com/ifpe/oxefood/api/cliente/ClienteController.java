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

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import br.com.ifpe.oxefood.util.entity.GenericController;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends GenericController {

   @Autowired
   private ClienteService clienteService;


   //@ApiOperation(value = "Serviço responsável por salvar um cliente no sistema.")
   @PostMapping
   public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request) {

      Cliente clienteNovo = request.build();
      Cliente cliente = clienteService.save(clienteNovo);
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }

   //@ApiOperation(value = "Serviço responsável por listar todos os clientes do sistema.")
   @GetMapping
   public List<Cliente> listarTodos() {
  
       return clienteService.listarTodos();
   }

   /*@ApiOperation(value = "Serviço responsável por obter um cliente referente ao Id passado na URL.")
   @ApiResponses(value = {
       @ApiResponse(code = 200, message = "Retorna o cliente."),
       @ApiResponse(code = 401, message = "Acesso não autorizado."),
       @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
       @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
       @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
   })*/
   @GetMapping("/{id}")
   public Cliente obterPorID(@PathVariable Long id) {

       return clienteService.obterPorID(id);
   }



   @PutMapping("/{id}")
   public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {

       clienteService.update(id, request.build());
       return ResponseEntity.ok().build();
   }

   //A linha clienteService.delete(id) é responsável por invocar o serviço clienteService e passar o ID do cliente que será excluído como parâmetro. 
   //Ao chamar clienteService.delete(id), o método está solicitando ao serviço clienteService que execute a exclusão do cliente correspondente ao ID fornecido. 
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       clienteService.delete(id);
       return ResponseEntity.ok().build();
   }


   //adicionar um endereço a um cliente
   @PostMapping("/endereco/{clienteId}")
   public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId, @RequestBody @Valid EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
   }

   //alterar endereço de um cliente
   @PutMapping("/endereco/{enderecoId}")
   public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId, @RequestBody EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
   }
  

   //deletar endereço
   @DeleteMapping("/endereco/{enderecoId}")
   public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

       clienteService.removerEnderecoCliente(enderecoId);
       return ResponseEntity.noContent().build();


   }
}

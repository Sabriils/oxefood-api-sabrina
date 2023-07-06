package br.com.ifpe.oxefood.modelo.cliente;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;

@Service
public class ClienteService extends GenericService {

   @Autowired
   private ClienteRepository repository;

   @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;
    

   private UsuarioService usuarioService;

   @Transactional
   public Cliente save(Cliente cliente) {

       usuarioService.save(cliente.getUsuario());

       super.preencherCamposAuditoria(cliente);
       return repository.save(cliente);
   }

   
   @Transactional
   public void update(Long id, Cliente clienteAlterado) {

      Cliente cliente = repository.findById(id).get();
      cliente.setNome(clienteAlterado.getNome());
      cliente.setDataNascimento(clienteAlterado.getDataNascimento());
      cliente.setCpf(clienteAlterado.getCpf());
      cliente.setFoneCelular(clienteAlterado.getFoneCelular());
      cliente.setFoneFixo(clienteAlterado.getFoneFixo());
	    
      super.preencherCamposAuditoria(cliente);
      repository.save(cliente);
  }

  @Transactional
  public void delete(Long id) {

      Cliente cliente = repository.findById(id).get();
      cliente.setHabilitado(Boolean.FALSE);
      super.preencherCamposAuditoria(cliente);

      repository.save(cliente);
  }


//O método listarTodos retorna uma lista de todos os clientes cadastrados no sistema. 
   public List<Cliente> listarTodos() {
    return repository.findAll();
}


   public Cliente obterPorID(Long id) {
    return repository.findById(id).get();
    
    
}

//implementação dos métodos para incluir, alterar e remover o endereço do cliente
@Transactional
   public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {

       Cliente cliente = this.obterPorID(clienteId);
      
       //Primeiro salva o EnderecoCliente:

       endereco.setCliente(cliente);
       endereco.setHabilitado(Boolean.TRUE);
       enderecoClienteRepository.save(endereco);
      
       //Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

       List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();
      
       if (listaEnderecoCliente == null) {
           listaEnderecoCliente = new ArrayList<EnderecoCliente>();
       }
      
       listaEnderecoCliente.add(endereco);
       cliente.setEnderecos(listaEnderecoCliente);
       this.save(cliente);
      
       return endereco;

}

  @Transactional
    public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {

        EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
        endereco.setRua(enderecoAlterado.getRua());
        endereco.setNumero(enderecoAlterado.getNumero());
        endereco.setBairro(enderecoAlterado.getBairro());
        endereco.setCep(enderecoAlterado.getCep());
        endereco.setCidade(enderecoAlterado.getCidade());
        endereco.setEstado(enderecoAlterado.getEstado());
        endereco.setComplemento(enderecoAlterado.getComplemento());

        return enderecoClienteRepository.save(endereco);
    }

    @Transactional
    public void removerEnderecoCliente(Long id) {

        EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
        endereco.setHabilitado(Boolean.FALSE);
        enderecoClienteRepository.save(endereco);

        Cliente cliente = this.obterPorID(endereco.getCliente().getId());
        cliente.getEnderecos().remove(endereco);
        this.save(cliente);
    }


}

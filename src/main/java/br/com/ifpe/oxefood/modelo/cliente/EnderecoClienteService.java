package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;

@Service
public class EnderecoClienteService extends GenericService {
    
    @Autowired
    private EnderecoClienteRepository repository;

    @Transactional
    public EnderecoCliente save(EnderecoCliente enderecoCliente) {

        super.preencherCamposAuditoria(enderecoCliente);
        return repository.save(enderecoCliente);
    }

    public List<EnderecoCliente> listarTodos() {
  
        return repository.findAll();
    }
 
    public EnderecoCliente obterPorID(Long id) {
 
        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, EnderecoCliente enderecoClienteAlterado) {

        EnderecoCliente enderecoCliente = repository.findById(id).get();
    
        enderecoCliente.setRua(enderecoClienteAlterado.getRua());
        enderecoCliente.setNumero(enderecoClienteAlterado.getNumero());
        enderecoCliente.setBairro(enderecoClienteAlterado.getBairro());
        enderecoCliente.setCep(enderecoClienteAlterado.getCep());
        enderecoCliente.setCidade(enderecoClienteAlterado.getCidade());
        enderecoCliente.setEstado(enderecoClienteAlterado.getEstado());
        enderecoCliente.setComplemento(enderecoClienteAlterado.getComplemento());

        super.preencherCamposAuditoria(enderecoCliente);
        repository.save(enderecoCliente);
    }

    @Transactional
    public void delete(Long id) {

        EnderecoCliente enderecoCliente = repository.findById(id).get();
        enderecoCliente.setHabilitado(Boolean.FALSE);
        super.preencherCamposAuditoria(enderecoCliente);

        repository.save(enderecoCliente);
    }

}
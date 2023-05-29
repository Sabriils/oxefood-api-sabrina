package br.com.ifpe.oxefood.modelo.entregador;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;

@Service
public class EntregadorService extends GenericService {

   @Autowired
   private EntregadorRepository repository;

   @Transactional
   public Entregador save(Entregador Entregador) {

       super.preencherCamposAuditoria(Entregador);
       return repository.save(Entregador);
   }


   @Transactional
   public void update(Long id, Entregador entregadorAlterado) {

      Entregador entregador = repository.findById(id).get();
      entregador.setNome(entregadorAlterado.getNome());
      entregador.setDataNascimento(entregadorAlterado.getDataNascimento());
      entregador.setCpf(entregadorAlterado.getCpf());
      entregador.setFoneFixo(entregadorAlterado.getRg());
      entregador.setFoneCelular(entregadorAlterado.getFoneCelular());
      entregador.setFoneFixo(entregadorAlterado.getFoneFixo());
      entregador.setQtdEntregasRealizadas(entregadorAlterado.getQtdEntregasRealizadas());
      entregador.setValorFrete(entregadorAlterado.getValorFrete());
      entregador.setEnderecoRua(entregadorAlterado.getEnderecoRua());
      entregador.setEnderecoNumero(entregadorAlterado.getEnderecoNumero());
      entregador.setEnderecoBairro(entregadorAlterado.getEnderecoBairro());
      entregador.setEnderecoCidade(entregadorAlterado.getEnderecoCidade());
      entregador.setEnderecoCep(entregadorAlterado.getEnderecoCep());
      entregador.setEnderecoUf(entregadorAlterado.getEnderecoUf());
      entregador.setFoneFixo(entregadorAlterado.getFoneFixo());
      entregador.setEnderecoComplemento(entregadorAlterado.getEnderecoComplemento());
      entregador.setAtivo(entregadorAlterado.getAtivo());

      super.preencherCamposAuditoria(entregador);
      repository.save(entregador);
  }



   
   public List<Entregador> listarTodos() {
  
    return repository.findAll();
}

public Entregador obterPorID(Long id) {

    return repository.findById(id).get();
}

}

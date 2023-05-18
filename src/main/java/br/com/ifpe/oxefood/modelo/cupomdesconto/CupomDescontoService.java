package br.com.ifpe.oxefood.modelo.cupomdesconto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;

@Service
public class CupomDescontoService extends GenericService {

   @Autowired
   private CupomDescontoRepository repository;

   @Transactional
   public CupomDesconto save(CupomDesconto cupomDesconto) {

       super.preencherCamposAuditoria(cupomDesconto);
       return repository.save(cupomDesconto);
   }


   
   public List<CupomDesconto> listarTodos() {
  
    return repository.findAll();
}

public CupomDesconto obterPorID(Long id) {

    return repository.findById(id).get();
}

}

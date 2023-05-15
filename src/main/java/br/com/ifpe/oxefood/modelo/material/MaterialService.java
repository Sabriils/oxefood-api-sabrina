package br.com.ifpe.oxefood.modelo.material;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;

@Service
public class MaterialService extends GenericService {

   @Autowired
   private MaterialRepository repository;

   @Transactional
   public Material save(Material material) {

       super.preencherCamposAuditoria(material);
       return repository.save(material);
   }


   
   public List<Material> listarTodos() {
  
    return repository.findAll();
}

public Material obterPorID(Long id) {

    return repository.findById(id).get();
}

}

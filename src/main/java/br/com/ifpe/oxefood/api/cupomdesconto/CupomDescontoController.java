
package br.com.ifpe.oxefood.api.cupomdesconto;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cupomdesconto.CupomDesconto;
import br.com.ifpe.oxefood.modelo.cupomdesconto.CupomDescontoService;
import br.com.ifpe.oxefood.util.entity.GenericController;

@RestController
@RequestMapping("/api/cupomDesconto")
public class CupomDescontoController extends GenericController {

   @Autowired
   private CupomDescontoService cupomDescontoService;

   @PostMapping
   public ResponseEntity<CupomDesconto> save(@RequestBody @Valid CupomDescontoRequest request) {

       CupomDesconto cupomdedesconto = cupomDescontoService.save(request.build());
       return new ResponseEntity<CupomDesconto>(cupomdedesconto, HttpStatus.CREATED);
   }

   @GetMapping
   public List<CupomDesconto> listarTodos() {
  
       return cupomDescontoService.listarTodos();
   }

   @GetMapping("/{id}")
   public CupomDesconto obterPorID(@PathVariable Long id) {

       return cupomDescontoService.obterPorID(id);
   }

}

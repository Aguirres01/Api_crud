package voll.med.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import voll.med.api.medico.DadosAtualizacaoMedico;
import voll.med.api.medico.DadosCadastroMedico;
import voll.med.api.medico.DadosListagemMedico;
import voll.med.api.medico.Medico;
import voll.med.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")


public class MedicoController {


    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    //@Valid vai validar as informações de cada campo, dizendo o que não pode ser vazio
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
        
    }


    // criado DTO record para selecionar apenas alguns dados, porém para exibir a lista precisamos converter usando o metodo stream.
    //ordenado lista usando Page e pageable
    //@PageableDefault size =10, 10 registros
    // sort, ordenar por {"nome"}
    //se o parametro estiver na url, vai sobescrever, usando da URL.
    @GetMapping
    public Page<DadosListagemMedico > listar(@PageableDefault(size=10, sort={"nome"})Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

    }

    @PutMapping
    @Transactional
    //carreguei o medico pelo id, chamei o metodo para atualizar os dados com base no DTO
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        //criação var medico para pegar medico pelo id.
        // repository acessa o banco de dados
         var medico = repository.getReferenceById(dados.id());
         //usando a variavel medico, chamamos o método atualizarInformacoes e salvamos em dados.
         medico.atualizarInformacoes(dados);

    }
    //parametro dinamico, será deletado via URL pelo ID
    // o PathVariable indica que o id que foi chamado é o id do DeleteMapping
    @DeleteMapping("/{id}")
    @Transactional
    //o Pathvariable Long id indica pro DeleteMapping que a referencia para o delete será o Id. 
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        //chamando método excluir no medico, define medico ativo como false
        medico.excluir();
    }


}

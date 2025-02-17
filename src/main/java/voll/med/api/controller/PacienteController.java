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
import voll.med.api.paciente.DadosAtualizacaoPaciente;
import voll.med.api.paciente.DadosCadastroPaciente;
import voll.med.api.paciente.DadosListagemPaciente;
import voll.med.api.paciente.Paciente;
import voll.med.api.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")

public class PacienteController {
    
    @Autowired
    PacienteRepository repository;


    @PostMapping
    @Transactional    
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente>listar(@PageableDefault(size=10, sort={"nome"})Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);


    }

    @DeleteMapping("/{id}")
    @Transactional

    public void excluir (@PathVariable Long id){
    var paciente =  repository.getReferenceById(id);
    paciente.excluir();


    }
}

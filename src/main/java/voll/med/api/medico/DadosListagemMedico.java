package voll.med.api.medico;

public record DadosListagemMedico(

    Long id,

    String nome,

    String email,

    String crm,

    Especialidade especialidade


) {

    //criando construtor de DadosListagemMedico
    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

    }

}

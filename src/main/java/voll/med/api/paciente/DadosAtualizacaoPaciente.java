package voll.med.api.paciente;

import voll.med.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

    Long id,

    String nome,

    String telefone,

    DadosEndereco endereco

) {

}

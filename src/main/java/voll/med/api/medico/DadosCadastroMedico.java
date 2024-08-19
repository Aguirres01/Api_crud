package voll.med.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import voll.med.api.endereco.DadosEndereco;

//usar o bean validation para validar o que é obrigatório ou não.
// NOTBLANK apenas para campo string

public record DadosCadastroMedico(
    
//não pode ser vazio nem nulo
@NotBlank
String nome, 

@NotBlank
@Email
String email, 

@NotBlank
@Pattern(regexp = "\\d{9,11}") 
String telefone,



@NotBlank
//expressão regular, tipo digito, de 4 a 6 digitos
@Pattern(regexp = "\\d{4,6}")
String crm,

// campo especialidade é um enum.
//notblank valida apenas string
@NotNull
Especialidade especialidade, 

@NotNull
@Valid
//usando valid para validar outro DTO. 
DadosEndereco endereco) {

}

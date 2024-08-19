package voll.med.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
    
@NotBlank
String logradouro, 

@NotBlank
String bairro, 

@NotBlank
//expressão regular dizendo que são 8 digitos obrigatoriamente
@Pattern(regexp = "\\d{8}")
String cep, 

@NotBlank
String cidade, 

@NotBlank
String uf, 

String complemento, 

String numero) {
}

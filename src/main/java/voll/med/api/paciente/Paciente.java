package voll.med.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import voll.med.api.endereco.Endereco;

@Entity(name="pacientes")
@Table(name="pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")


public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados){
        this.nome= dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    //método para atualizar dados 
    public void atualizarInformacoes(DadosAtualizacaoPaciente dados){

        //O nome não é obrigatorio na atualização, o if checa se o nome está vindo. Se sim, ele atualiza.
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        //endereco é um objeto, então precisamos criar outro método
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
    public void excluir() {
        this.ativo = false;
    }

    
}

package voll.med.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import voll.med.api.endereco.Endereco;

@Entity(name="medicos")
@Table(name="medicos")
@Getter
// cria automaticamente construtor sem argumentos
@NoArgsConstructor
//cria construtor com todos os argumentos
@AllArgsConstructor
// vai verificar se temos mais de um id, e vai usar id como referencia
@EqualsAndHashCode(of="id")


public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;


    // marcação para tipo enum, será armazenado no BD tipo String
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    // não será criada uma tabela exclusica para endereço no BD
    // para funcionar, na classe Endereco precisa estar anotado com @Embedded
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.email= dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade= dados.especialidade();
        this.endereco = new Endereco(dados.endereco());


    }

    //método para atualizar dados 
    public void atualizarInformacoes(DadosAtualizacaoMedico dados){

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

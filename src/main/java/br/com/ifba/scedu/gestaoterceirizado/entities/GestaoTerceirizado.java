package br.com.ifba.scedu.gestaoterceirizado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import br.com.ifba.scedu.person.model.Person;

@Entity // Define que esta classe é uma entidade JPA, mapeando-a para o banco de dados
@Table(name = "gestaoTerceirizado") // Especifica o nome da tabela no banco de dados
@Getter // Lombok gera automaticamente os métodos getters para todos os campos
@Setter // Lombok gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor // Lombok gera automaticamente um construtor com todos os atributos como parâmetros
@NoArgsConstructor  // Lombok gera automaticamente um construtor vazio
public class GestaoTerceirizado {

    @Id // Define que o campo `id` é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor do `id` será gerado automaticamente
    @Column(name = "id", updatable = false, nullable = false) // Especifica a coluna `id` no banco de dados, que não pode ser alterada nem nula
    private Long id;

    @ManyToOne // Define uma relação Many-to-One (muitos terceirizados podem estar associados a uma única pessoa)
    @JoinColumn(name = "person_id", referencedColumnName = "id") // Especifica a chave estrangeira que faz referência à coluna `id` na tabela `Person`
    private Person person; // Relaciona o terceirizado a uma pessoa

    @Column(name = "phone_terceirizado", nullable = false) // Especifica que o campo `phone` é obrigatório no banco de dados
    private String phone; // Armazena o número de telefone do terceirizado

    @Column(name = "email_terceirizado", nullable = false) // Especifica que o campo `email` é obrigatório no banco de dados
    @Email // Validação para garantir que o valor armazenado seja um e-mail válido
    private String email; // Armazena o e-mail do terceirizado

    @Column(name = "position", nullable = false) // Especifica que o campo `position` (cargo) é obrigatório
    private String position; // Armazena o cargo do terceirizado

    @Column(name = "enterprise", nullable = false) // Especifica que o campo `enterprise` (empresa) é obrigatório
    private String enterprise; // Armazena a empresa onde o terceirizado trabalha

    @Column(name = "department", nullable = false) // Especifica que o campo `department` (departamento) é obrigatório
    private String department; // Armazena o departamento onde o terceirizado está alocado

    @Column(name = "status", nullable = false) // Especifica que o campo `status` (ativo/inativo) é obrigatório
    private boolean status; // Indica o status do terceirizado (ativo ou inativo)

    @Column(name = "observations", nullable = false) // Especifica que o campo `observations` é obrigatório
    private String observations; // Armazena observações adicionais sobre o terceirizado

    // Método que retorna um Optional contendo o terceirizado baseado no ID. Pode ser útil para evitar problemas com valores nulos
    public Optional<GestaoTerceirizado> getTerceirizadoById(Long id) {
        return this.getTerceirizadoById(id);
    }
}

package bd.edu.seu.vaadindemooc.model;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.OneToMany;import java.time.LocalDate;import java.util.List;@Data@AllArgsConstructor@NoArgsConstructor@Entitypublic class Student {    @Id    private long id;    private String name;    private LocalDate dateOfBirth;    private StudentAddress studentAddress;    private StudentPhone phone;}
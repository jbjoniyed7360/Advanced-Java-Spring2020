package bd.edu.seu.springbootdemo.model;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.ManyToOne;import java.time.LocalDate;@Entity@Data@AllArgsConstructor@NoArgsConstructorpublic class Result {    @Id    private long id;    private long semester;    private LocalDate entryDate;    private double gpa;    private double cgpa;}
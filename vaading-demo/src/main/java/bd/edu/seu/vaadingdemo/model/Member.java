package bd.edu.seu.vaadingdemo.model;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import javax.persistence.Entity;import javax.persistence.Id;@Data@AllArgsConstructor@NoArgsConstructor@Entitypublic class Member {    @Id    private long a_id;    private String name;    private String phone;    private String address;    private String bloodGroup;    private double age;    private String sex;    private String occupation;    private String grade;}
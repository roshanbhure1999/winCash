package payment.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")

public class Employee implements Serializable {

    private Long id;
    private String name;
    private String department;
    private String email;
    private String address;
    private String contact;
    private Long salary;


}

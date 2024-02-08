package com.springbootproject.object;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEACHERS")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email")
    private String email;

    public boolean equals(Object o){

        if (this == o) {
            return true;
        }

        if (!(o instanceof Teacher)) {
            return false;
        }

        Teacher compared = (Teacher) o;

        if (this.id == compared.id && this.name.equals(compared.name)
                && this.email.equals(compared.email)) {
            return true;
        }

        return false;
    }
}

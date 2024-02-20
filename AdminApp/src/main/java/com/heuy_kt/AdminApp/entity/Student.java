package com.heuy_kt.AdminApp.entity;

import com.heuy_kt.AdminApp.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "students")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstname;
    private String lastName;
    private Integer attendance;
    private Boolean blacklisted;
    //simply telling spring that it is an enum and the string value should be used
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    //creating a mat that flows with the year.
    String currentYear = String.valueOf(LocalDate.now().getYear());
    private String matNo = "S00"+currentYear+getId();

}

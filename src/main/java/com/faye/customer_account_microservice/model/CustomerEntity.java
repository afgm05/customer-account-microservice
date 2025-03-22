package com.faye.customer_account_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "customer_number", length = 20, nullable = false)
    private Long customerNumber;

    @NotBlank(message = "Customer name is required")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotBlank(message = "Customer mobile is required")
    @Column(name = "mobile", length = 20, nullable = false)
    private String mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "Address1 is required")
    @Column(name = "address1", length = 100, nullable = false)
    private String address1;

    @Column(name = "address2", length = 100)
    private String address2;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_number")
    private List<AccountEntity> accounts;

}

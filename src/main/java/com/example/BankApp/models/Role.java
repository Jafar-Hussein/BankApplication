package com.example.BankApp.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
    private String authority;

    public Role() {super();}

    /**
     * Konstruktor för att skapa ett Role-objekt.
     * @param authority är rollen som ska skapas.
     */
    public Role(String authority) {
        this.authority = authority;
    }
    public Role(Long roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }
    /**
     * Metod för att hämta rollen.
     * @return rollen.
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }
}

package com.example.BankApp.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;


    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
    @OneToMany
    private Set<Role> authorities;
    public User(Long id, String username, String password, BankAccount bankAccount, Set<Role> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
        this.authorities = authorities;
    }
    public User() {
        super();
        this.authorities = new HashSet<Role>();
    }


    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * Indikerar om användaren inte är låst ute.
     *
     * @return true, eftersom användarkontot aldrig låses.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * Indikerar om användarens autentiseringsuppgifter (lösenord) inte har förfallit.
     *
     * @return true, eftersom autentiseringsuppgifterna aldrig förfaller.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Indikerar om användaren är aktiverad.
     *
     * @return true, eftersom alla användare är aktiverade.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
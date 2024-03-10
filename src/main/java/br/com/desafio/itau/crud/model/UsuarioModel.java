package br.com.desafio.itau.crud.model;

import br.com.desafio.itau.crud.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
public class UsuarioModel implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;
    @Column(unique = true)
    private String login;
    private Double valorTotal;
    private String senha;
    private RoleEnum role;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<DesejosModel> Desejo;

    public UsuarioModel(UsuarioModel usuarioModel) {
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public List<DesejosModel> getDesejo() {
        return Desejo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDesejo(List<DesejosModel> desejo) {
        Desejo = desejo;
    }


    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public RoleEnum getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == RoleEnum.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );

        }
        return List.of(
                new SimpleGrantedAuthority(("ROLE_USER"))
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}



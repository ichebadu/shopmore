package com.sm.shopmore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name")
    private String roleName;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "permission", joinColumns= @JoinColumn(name="role_id"))
    @Column(name = "permission")
    private List<String> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.roleName));
        return authorities;
    }

}

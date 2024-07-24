package io.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.app.dto.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements UserDetails {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	@Column(nullable = false,unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Roles roles=Roles.USER;
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<Task> tasks=new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> set=new HashSet<>();
		set.add(new SimpleGrantedAuthority("ROLE_"+roles.name()));
		return set;
	}

	@Override
	public String getUsername() {
		return email;
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








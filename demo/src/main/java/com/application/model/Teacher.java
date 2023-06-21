package com.application.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Teacher implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
	private boolean accountExpired;

    @Column(nullable = false)
	private boolean accountLocked;

    @Column(nullable = false)
	private boolean credentialsExpired;

    @Column(nullable = false)
	private boolean enabled = true;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setUsername(username);
        this.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = null ;
	    authorities.add(new SimpleGrantedAuthority(UserType.TEACHER.toString()));
	    return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
	    return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
	    return enabled;
	}

    
}

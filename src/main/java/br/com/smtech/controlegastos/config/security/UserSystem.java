package br.com.smtech.controlegastos.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSystem extends User {

	private static final long serialVersionUID = 1L;

	private br.com.smtech.controlegastos.api.model.User usuario;

	public UserSystem(br.com.smtech.controlegastos.api.model.User usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getUsername(), usuario.getPassword(), authorities);
		this.usuario = usuario;
	}

	public br.com.smtech.controlegastos.api.model.User getUsuario() {
		return usuario;
	}
}

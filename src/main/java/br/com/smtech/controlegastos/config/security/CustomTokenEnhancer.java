package br.com.smtech.controlegastos.config.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.smtech.controlegastos.api.model.Person;
import br.com.smtech.controlegastos.api.repository.PersonRepository;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserSystem usuarioSistema = (UserSystem) authentication.getPrincipal();
		
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("login", usuarioSistema.getUsuario().getUsername());
		addInfo.put("id", usuarioSistema.getUsuario().getId());
		final Optional<Person> personOp = personRepository.findTopByUserId(usuarioSistema.getUsuario().getId());
		if(personOp.isPresent()){
			final Person person = personOp.get();
			addInfo.put("name", person.getName());
			addInfo.put("cpf", person.getCpf());
		}


		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}

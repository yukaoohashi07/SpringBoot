package sample.common.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	private final LoginMapper loginMapper;
	private final PasswordEncoder passwordEncoder;
	
	public LoginServiceImpl(LoginMapper loginMapper, 
			PasswordEncoder passwordEncoder) {
		this.loginMapper = loginMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	@Override
	public void register(String username, String rawPassword) {
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(passwordEncoder.encode(rawPassword));
		loginMapper.insert(login);
	}
	
	@Override
	public Optional<Login> authenticate(String username, 
			String rawPassword){
		Login user = loginMapper.findByUsername(username);
		if(user == null) return Optional.empty();
		
		if(passwordEncoder.matches(rawPassword, user.getPassword())) {
			return Optional.of(user);
		}
		return Optional.empty();
	}
}
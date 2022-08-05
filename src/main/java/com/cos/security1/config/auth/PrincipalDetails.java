package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다. 
//로그인을 진행이 안료가 되면 시큐리티 session을 만들어준다.(Security ContextHolder)
//오브젝트 타입 = Authentication 타입 객체
//Authentication 안에 User정보가 있어야 됨.
//User오브젝트타입 => UserDetails 타입 객체

//Security Session 세션 정보를 저장을 하는데 여기 들어갈 수 있는 객체는 Authentication으로 저장되어있다.
//Authentication은 UserDetail(PrincipalDetails)..객 체에서 꺼낸다.

public class PrincipalDetails implements UserDetails{
	
	private User user; //콤포지션 설정

	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//해당 User 의 권한을 리턴하는 곳 !! 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				//String을 return할 수 있다. 
				return user.getRole(); //String 타입이니 자체 리턴 불가능;
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
package com.study.lastlayer.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final JwtUtil jwtUtil;

	//	private final MemberRepository memberRepository;
	//    private final PasswordEncoder passwordEncoder;

	public AuthService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public String login(String email, String password) {
		//        Member member = memberRepository.findByEmail(email)
		//                .orElseThrow(() -> new BadRequestException("이메일을 찾을 수 없습니다."));
		//
		//        if (!passwordEncoder.matches(password, member.getPassword())) {
		//            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
		//        }

		// DB에서 가져온 Long 타입의 id를 전달
		return jwtUtil.createToken(email, 1L);
	}
}

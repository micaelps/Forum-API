package br.com.micaelps.forum.controller;


import br.com.micaelps.forum.config.security.TokenService;
import br.com.micaelps.forum.controller.dto.TokenDto;
import br.com.micaelps.forum.controller.form.loginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.Authenticator;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager am;

    @Autowired
    private TokenService tc;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid loginForm form){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = form.converter();
        System.out.println(usernamePasswordAuthenticationToken);
        try {
            Authentication authenticate = am.authenticate(usernamePasswordAuthenticationToken);
            String token = tc.gerarToken(authenticate);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }
        catch (AuthenticationException ae){
            return  ResponseEntity.badRequest().build();
        }
    }
}

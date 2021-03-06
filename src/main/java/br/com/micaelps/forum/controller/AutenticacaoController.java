package br.com.micaelps.forum.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.micaelps.forum.config.security.TokenService;
import br.com.micaelps.forum.controller.dto.TokenDto;
import br.com.micaelps.forum.controller.form.loginForm;


@Profile(value = {"prod", "test"})
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid loginForm form){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = form.converter();
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(usernamePasswordAuthenticationToken);

            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }
        catch (AuthenticationException ae){
            return  ResponseEntity.badRequest().build();
        }

    }




}

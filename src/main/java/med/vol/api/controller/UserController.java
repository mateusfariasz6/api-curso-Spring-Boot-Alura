package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.User.DataForUserAuthenticationRequestDTO;
import med.vol.api.models.User;
import med.vol.api.security.DataTokenJwtResponseDTO;
import med.vol.api.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DataTokenJwtResponseDTO> logIn(@RequestBody @Valid DataForUserAuthenticationRequestDTO authenticationRequestDTO){
       UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken(authenticationRequestDTO.login(), authenticationRequestDTO.password());
        Authentication authentication =  manager.authenticate(token);
        String tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJwtResponseDTO(tokenJWT));
    }
}

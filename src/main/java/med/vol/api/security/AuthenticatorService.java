package med.vol.api.security;

import lombok.RequiredArgsConstructor;
import med.vol.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatorService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        return userRepository.findByLogin(username);
    }




}

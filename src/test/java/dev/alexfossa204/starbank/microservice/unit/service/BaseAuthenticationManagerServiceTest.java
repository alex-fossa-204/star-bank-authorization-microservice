package dev.alexfossa204.starbank.microservice.unit.service;

import dev.alexfossa204.starbank.microservice.config.security.provider.JwtTokenProvider;
import dev.alexfossa204.starbank.microservice.config.security.user.JwtUserDetails;
import dev.alexfossa204.starbank.microservice.service.dto.HttpResponseDto;
import dev.alexfossa204.starbank.microservice.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.microservice.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.microservice.repository.model.Credential;
import dev.alexfossa204.starbank.microservice.repository.model.Role;
import dev.alexfossa204.starbank.microservice.repository.model.User;
import dev.alexfossa204.starbank.microservice.repository.CredentialRepository;
import dev.alexfossa204.starbank.microservice.service.impl.ApiUserDetailsServiceImpl;
import dev.alexfossa204.starbank.microservice.service.impl.LoginAttemptService;
import dev.alexfossa204.starbank.microservice.service.impl.PhoneAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static dev.alexfossa204.starbank.microservice.service.constant.ServiceExceptionConstant.STATUS_CODE_UNAUTHORIZED_INCORRECT_CREDENTIALS;
import static dev.alexfossa204.starbank.microservice.unit.AuthorizationMicroserviceTestConstant.*;
import static org.mockito.Mockito.doReturn;

@Slf4j
@ExtendWith({MockitoExtension.class})
public class BaseAuthenticationManagerServiceTest {

    private LoginRequestDto loginRequestDtoByPhoneDummy;

    private LoginResponseDto loginResponseDtoSuccessfulStub;

    private HttpResponseDto loginResponseDtoUnsuccessfulStub;

    private Role roleDummy;
    private User userDummy;

    private Credential credentialDummy;

    private UsernamePasswordAuthenticationToken authenticationToken;

    private JwtUserDetails jwtUserDetailsDummy;

    @Mock
    private CredentialRepository credentialRepository;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock(lenient = true)
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private LoginAttemptService loginAttemptService;

    @Mock
    private ApiUserDetailsServiceImpl apiUserDetailsService;

    @InjectMocks
    private PhoneAuthenticationService phoneAuthenticationService;

    @BeforeEach
    void populateLoginRequestDtoDummy() {
        loginRequestDtoByPhoneDummy = LoginRequestDto.builder()
                .login(DUMMY_PHONE.getDummyValue())
                .password(DUMMY_CORRECT_PASSWORD.getDummyValue())
                .build();
        loginResponseDtoSuccessfulStub = LoginResponseDto.builder()
                .jwtToken(DUMMY_TOKEN.getDummyValue())
                .role(new HashSet<>(List.of(DUMMY_ROLE.getDummyValue())))
                .build();
        loginResponseDtoUnsuccessfulStub = HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(STATUS_CODE_UNAUTHORIZED_INCORRECT_CREDENTIALS)
                .build();
        jwtUserDetailsDummy = JwtUserDetails.builder()
                .username(loginRequestDtoByPhoneDummy.getLogin())
                .password(loginRequestDtoByPhoneDummy.getPassword())
                .isNonLocked(true)
                .isActive(true)
                .isCredentialNonExpired(true)
                .role(roleDummy)
                .build();
        roleDummy = Role.builder()
                .roleName(DUMMY_ROLE.getDummyValue())
                .build();
        userDummy = User.builder()
                .role(roleDummy)
                .build();
        credentialDummy = Credential.builder()
                .phoneLogin(loginRequestDtoByPhoneDummy.getLogin())
                .password(loginRequestDtoByPhoneDummy.getPassword())
                .user(userDummy)
                .isNonLocked(true)
                .isActive(true)
                .isCredentialNonExpired(true)
                .build();
        authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDtoByPhoneDummy.getLogin(), loginRequestDtoByPhoneDummy.getPassword());
    }

//    @Test
//    void doLoginCorrectAnswer() {
//        //doReturn(DUMMY_TOKEN.getDummyValue()).when(jwtTokenProvider).generateToken(jwtUserDetailsDummy);
//        when(jwtTokenProvider.generateToken(jwtUserDetailsDummy))
//                .thenReturn(DUMMY_TOKEN.getDummyValue());
//        when(authenticationManager.authenticate(authenticationToken))
//                .thenReturn(authenticationToken);
//        when(loginAttemptService.hasExceededMaxAttempts(loginRequestDtoByPhoneDummy.getLogin()))
//                .thenReturn(false);
//        when(apiUserDetailsService.loadCredentialByPhoneIdentifier(loginRequestDtoByPhoneDummy.getLogin()))
//                .thenReturn(credentialDummy);
//        LoginResponseDto loginResponseDto = phoneAuthenticationService.login(loginRequestDtoByPhoneDummy);
//        Assertions.assertThat(loginResponseDto).isEqualTo(loginResponseDtoSuccessfulStub);
//    }

}

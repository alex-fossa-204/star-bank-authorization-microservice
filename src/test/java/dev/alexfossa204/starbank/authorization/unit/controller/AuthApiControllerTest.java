package dev.alexfossa204.starbank.authorization.unit.controller;

import dev.alexfossa204.starbank.authorization.config.security.filter.CorsFilter;
import dev.alexfossa204.starbank.authorization.config.security.filter.JwtAccessDeniedHandler;
import dev.alexfossa204.starbank.authorization.config.security.filter.JwtAuthenticationEntryPoint;
import dev.alexfossa204.starbank.authorization.config.security.provider.JwtTokenProvider;
import dev.alexfossa204.starbank.authorization.controller.impl.AuthApiController;
import dev.alexfossa204.starbank.authorization.service.dto.HttpResponseDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.LoginResponseDto;
import dev.alexfossa204.starbank.authorization.service.impl.BaseAuthenticationManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.*;
import static dev.alexfossa204.starbank.authorization.unit.AuthorizationMicroserviceTestConstant.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = AuthApiController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JwtTokenProvider.class, CorsFilter.class, JwtAccessDeniedHandler.class, JwtAuthenticationEntryPoint.class}),
})
@WebAppConfiguration
class AuthApiControllerTest {

    private LoginRequestDto loginRequestDtoByPhoneDummy;

    private LoginResponseDto loginResponseDtoCorrectStub;

    private HttpResponseDto loginResponseDtoIncorrectCredentialsStub;

    @MockBean
    private AuthApiController authApiController;

    @MockBean
    private BaseAuthenticationManagerService authenticationManagerService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void populateLoginRequestDtoDummy() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        loginRequestDtoByPhoneDummy = LoginRequestDto.builder()
                .login(DUMMY_PHONE.getDummyValue())
                .password(DUMMY_CORRECT_PASSWORD.getDummyValue())
                .build();
        loginResponseDtoCorrectStub = LoginResponseDto.builder()
                .jwtToken(DUMMY_TOKEN.getDummyValue())
                .role(new HashSet<>(List.of(DUMMY_ROLE.getDummyValue())))
                .build();
        loginResponseDtoIncorrectCredentialsStub = HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(STATUS_CODE_UNAUTHORIZED_INCORRECT_CREDENTIALS)
                .build();

    }

    @Test
    void loginByPassportControllerTest() throws Exception {
        when(authenticationManagerService.doLogin(loginRequestDtoByPhoneDummy, AUTH_TYPE_PHONE.getDummyValue()))
                .thenThrow(new BadCredentialsException(STATUS_CODE_UNAUTHORIZED_INCORRECT_CREDENTIALS));
        mockMvc.perform(post("/a-banking/auth/login", loginRequestDtoByPhoneDummy)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        //.content(new ObjectMapper().writeValueAsString(loginRequestDtoByPhoneDummy))
                        .header("Authorization-Method", AUTH_TYPE_PHONE.getDummyValue())
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

}

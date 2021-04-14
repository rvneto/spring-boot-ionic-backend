package br.com.udemy.ionicbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    public static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) { // se nos profiles ativos tiver teste acessa o H2
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable(); // armazenamento de cache desabilitado
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() // autoriza somente métodos GET
                .antMatchers(PUBLIC_MATCHERS).permitAll() // autoriza todos os caminhos do vetor
                .anyRequest().authenticated(); // pra todo resto exige autenticação
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // para garantir que o backend não crie sessão de usuário
    }

    @Bean
        // definindo um bean pra permitir o acesso por multiplas fontes por configurações básicas
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

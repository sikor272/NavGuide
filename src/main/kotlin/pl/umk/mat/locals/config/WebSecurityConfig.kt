package pl.umk.mat.locals.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import pl.umk.mat.locals.auth.utils.JwtAuthorizationFilter
import pl.umk.mat.locals.auth.utils.JwtTokenProvider
import pl.umk.mat.locals.auth.utils.UserDetailsServiceImpl

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        @Lazy private val userDetailsService: UserDetailsServiceImpl,
        @Lazy private val passwordEncoder: PasswordEncoder,
        private val jwtTokenProvider: JwtTokenProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/auth/**")
                .and()
                .ignoring().antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
                .antMatchers("/profile/**").authenticated()
                //.antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()

        http.addFilterBefore(JwtAuthorizationFilter(jwtTokenProvider, userDetailsService), UsernamePasswordAuthenticationFilter::class.java)
    }
}
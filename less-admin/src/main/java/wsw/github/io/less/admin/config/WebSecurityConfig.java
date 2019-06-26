package wsw.github.io.less.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wsw.github.io.less.admin.security.AdminAccessDecisionManager;
import wsw.github.io.less.admin.security.AdminAuthenticationEntryPoint;
import wsw.github.io.less.admin.security.AdminAuthorizationTokenFilter;
import wsw.github.io.less.admin.security.AdminFilterInvocationSecurityMetadataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("adminUserDetailService")
    private UserDetailsService adminUserDetailService;

    @Autowired
    private AdminAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private AdminAuthorizationTokenFilter adminAuthorizationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailService)
        .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests().withObjectPostProcessor(
                    new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                            o.setSecurityMetadataSource(adminFilterInvocationSecurityMetadataSource());
                            o.setAccessDecisionManager(adminAccessDecisionManager());
                            return o;
                        }
                    })
//                .antMatchers("/auth/*").permitAll();
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(adminAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/**");
        web.ignoring().antMatchers("/druid/**");
    }

    @Bean
    AdminFilterInvocationSecurityMetadataSource adminFilterInvocationSecurityMetadataSource() {
        return new AdminFilterInvocationSecurityMetadataSource();
    }

    @Bean
    AdminAccessDecisionManager adminAccessDecisionManager() {
        return new AdminAccessDecisionManager();
    }
}

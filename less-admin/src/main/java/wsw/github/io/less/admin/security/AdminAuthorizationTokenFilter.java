package wsw.github.io.less.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import wsw.github.io.less.common.exception.LessException;
import wsw.github.io.less.service.SysUserTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AdminAuthorizationTokenFilter extends OncePerRequestFilter{

    private final String tokenHeader = "Authorization";

    @Autowired
    @Qualifier("adminUserDetailService")
    private UserDetailsService adminUserDetailService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("processing authentication for '{}'", request.getRequestURL());

        final String header = request.getHeader(tokenHeader);

        String username = null;
        String authToken = null;

        // 先获取token值，然后得到用户名
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.substring(7);
            username = sysUserTokenService.getUsernameByToken(authToken);

            /*try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                log.error("an error occurred during getting username from token", e);
            } catch (LessException e) {
                log.warn("the token is expired and not valid anymore", e);
            }*/
        } else {
            log.warn("couldn't find bearer string, will ignore the header");
        }

        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("security context was null, so authorizing user");
            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            UserDetails userDetails;
            try {
                userDetails = adminUserDetailService.loadUserByUsername(username);
            } catch (LessException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMsg());
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));

            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
//            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                logger.info("authorized user '{}', setting security context", username);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
        }

        filterChain.doFilter(request, response);
    }
}

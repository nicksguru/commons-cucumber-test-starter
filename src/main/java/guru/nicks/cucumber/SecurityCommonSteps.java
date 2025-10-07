package guru.nicks.cucumber;

import io.cucumber.java.en.Given;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * WARNING: {@link Authentication} affects all scenarios running (possibly in parallel) in the same thread.
 */
@SuppressWarnings("java:S100") // allow underscores in method names
public class SecurityCommonSteps {

    @Given("current user has admin role")
    public void currentUserHasAdminRole() {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        var authentication = new UsernamePasswordAuthenticationToken(
                new User("admin", "N/A", authorities), null, authorities);

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @Given("current user is anonymous")
    public void currentUserIsAnonymous() {
        SecurityContextHolder
                .getContext()
                .setAuthentication(null);
    }

}

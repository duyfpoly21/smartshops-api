package vn.smartshop.server.infra.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.smartshop.server.infra.util.AuthUtil;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			return Optional.of(Optional.ofNullable(AuthUtil.getUserName()).orElse("Unknown"));
		} else {
			return Optional.of("Unknown");
		}
	}

}

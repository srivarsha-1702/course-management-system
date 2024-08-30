package com.aj.course_enrollment_service.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAwareImpl")
public class AuditAwareImpl implements AuditorAware<String>{
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("COURSE_MS");
    }
}

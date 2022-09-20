package com.winvents.email.application.infrastructure.repositories;

import com.winvents.email.application.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}

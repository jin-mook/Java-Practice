package com.example.ssedemo.eventlistener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListenerRepository extends JpaRepository<ListenerEntity, Long> {
}

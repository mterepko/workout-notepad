package com.maniek.software.workoutnotepad.quote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Quote findByCreationDate(LocalDate localDate);

}

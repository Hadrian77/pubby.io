package io.pubby.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.pubby.models.AnswerRecord;

public interface AnswerRecordRepository extends JpaRepository<AnswerRecord,Integer> {

}

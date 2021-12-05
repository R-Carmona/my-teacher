package com.rubencarmona.myteacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubencarmona.myteacher.domain.StudyPlan;
@Repository
public interface StudyPlanRespository extends JpaRepository<StudyPlan,Integer> {
}

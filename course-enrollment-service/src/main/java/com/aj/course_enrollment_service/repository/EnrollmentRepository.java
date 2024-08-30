package com.aj.course_enrollment_service.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.aj.course_enrollment_service.entity.Enrollment;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
//    Optional<Object> findByEmpIdAndCourseId(Integer empId, Integer courseId);
//    List<Enrollment> findByCourseId(Integer course_id);
//    @Transactional
//    @Modifying
//    void deleteByEmpIdAndCourseId(Integer empId, Integer courseId);
}

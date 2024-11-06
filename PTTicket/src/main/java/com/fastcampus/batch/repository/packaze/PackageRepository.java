package com.fastcampus.batch.repository.packaze;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {

  List<PackageEntity> findByCreatedAtAfter(LocalDateTime dateTime, Pageable pageable);

  @Transactional
  @Modifying
  @Query("UPDATE PackageEntity p"
  + "       SET p.count = :count" 
  + "       p.period = :period" 
  + "       WHERE p.packageSeq = :packageSeq")
  void updateCountAndPeriod(Integer packageSeq,Integer count, Integer period);

}

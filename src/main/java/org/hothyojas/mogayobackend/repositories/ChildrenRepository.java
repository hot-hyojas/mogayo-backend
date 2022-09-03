package org.hothyojas.mogayobackend.repositories;

import java.awt.print.Pageable;
import java.util.List;
import org.hothyojas.mogayobackend.entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChildrenRepository extends JpaRepository<Child, Integer> {

    @Query("select c from Child c where c.available = true")
    List<Child> findTargetChildren(Pageable pageable); // TODO: 진짜 랜덤 적용
}

package org.hothyojas.mogayobackend.repositories;

import java.util.Optional;
import org.hothyojas.mogayobackend.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsRepository extends JpaRepository<Parent, Integer> {

    Optional<Parent> findParentByInviteCode(String inviteCode);
}

package net.snoopgame.hr.repository;

import net.snoopgame.hr.model.DepartmentPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository <DepartmentPosition, Long> {
}

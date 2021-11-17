package apap.tutorial.pergipergi.repository;

import apap.tutorial.pergipergi.model.RoleModel;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long>{
}

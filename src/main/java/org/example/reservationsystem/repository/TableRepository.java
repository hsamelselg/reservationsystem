package org.example.reservationsystem.repository;

import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Restaurant_table, Integer> {
}

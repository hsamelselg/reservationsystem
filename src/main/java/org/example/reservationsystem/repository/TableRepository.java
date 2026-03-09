package org.example.reservationsystem.repository;

import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Restaurant_table, Integer> {
    List<Restaurant_table> findBySizeGreaterThanEqual(int size);
}

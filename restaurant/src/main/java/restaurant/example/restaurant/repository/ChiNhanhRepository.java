package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.ChiNhanh;
@Repository
public interface ChiNhanhRepository extends JpaRepository<ChiNhanh,String> {
}


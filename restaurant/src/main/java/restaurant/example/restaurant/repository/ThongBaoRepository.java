package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.ChiNhanh;
import restaurant.example.restaurant.entity.ThongBao;

@Repository
public interface ThongBaoRepository extends JpaRepository<ThongBao,String> {
}


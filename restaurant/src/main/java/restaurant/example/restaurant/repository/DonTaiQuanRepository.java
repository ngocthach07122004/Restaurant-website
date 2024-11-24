package restaurant.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.example.restaurant.entity.DonTaiQuan;

@Repository
public interface DonTaiQuanRepository extends JpaRepository<DonTaiQuan,String> {
}


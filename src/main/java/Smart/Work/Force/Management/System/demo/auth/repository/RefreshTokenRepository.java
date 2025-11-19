package Smart.Work.Force.Management.System.demo.auth.repository;

import Smart.Work.Force.Management.System.demo.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

}

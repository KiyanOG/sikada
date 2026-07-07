package sikada.bangkaselatan.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sikada.bangkaselatan.com.entity.Admin;

public interface AdminRepository
        extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

}
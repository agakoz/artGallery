//package com.example.artGallery.repositories;
//
//import com.example.artGallery.dto.user.UserDTO;
//import com.example.artGallery.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User, Integer> {
////    Optional<Integer> findIdByUsername(String )
//
//    Optional<User> findByUsername(@Param("username") String username);
//
//    Optional<User> findById(@Param("id") int id);
//
//    @Query("SELECT new com.example.artGallery.dto.user.UserDTO(u.id, u.username, u.password, u.role, u.name, u.surname, u.company, u.address, u.city, u.licenceNumber, u.specializations, u.professionalTitle, u.email, u.activated, u.activationKey, u.resetKey) FROM User u")
//    List<UserDTO> retrieveAllUserAsDTO();
//
//    @Query("DELETE FROM User u WHERE u.username=:currentUsername")
//    void deleteByUsername(Optional<String> currentUsername);
//}
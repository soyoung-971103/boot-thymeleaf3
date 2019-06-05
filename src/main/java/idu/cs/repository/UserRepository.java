package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.entity.UserEntity;

@Repository
public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {
	//findById, save, delete 선언없어도 구현 가능
	// 아래 메소드들은 선언해야 JPA 규첵에 의해 구현됨
	// find - select문, By - where, Order By - order by, ASC와 DESC를 함께 사용 
	List<UserEntity> findByName(String name);
	//id 내림차순으로 정렬
	//List<UserEntity> findByOrderByIdDESC(String name);
	List<UserEntity> findByCompany(String name);
	
	UserEntity findByUserId(String userId);//ByUserId == "where userId == "
	
}

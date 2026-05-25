package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.Login;

@Mapper
public interface LoginMapper {
	List<Login> findAll();
	void insert(Login login);
	Login findByUsername(String username);
}
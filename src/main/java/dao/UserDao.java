package dao;

import java.util.List;

import domain.User;

public interface UserDao {
	// ユーザーのリストを取得
	List<User> findAll();
	// ユーザーを追加
	void add(User user);
	// ログインユーザーの取得
	User findLoginUser(String email, String pass);
}
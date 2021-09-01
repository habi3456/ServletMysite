package dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.User;

public class UserDaoImpl implements UserDao {

	private DataSource ds;

	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();

		try (var con = ds.getConnection()) {
			String sql = "SELECT * FROM users";
			var rs = con.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				Integer age = (Integer) rs.getObject("age");
				list.add(new User(id, name, email, pass, age));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void add(User user) {
		try (var con = ds.getConnection()) {
			// SQLの実行を準備
			String sql = "INSERT INTO users "
					+ " (name, email, pass, age) "
					+ " VALUES (?, ?, ?, ?)";
			var stmt = con.prepareStatement(sql);

			// パスワードのハッシュ化
			String hashed = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
			System.out.println(hashed);

			// SQL内の ? に値をセット
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, hashed);
			stmt.setObject(4, user.getAge(), Types.INTEGER);

			// SQLを実行
			stmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// emailとpassが正しい場合、User情報が返る
	// email/passが違う場合、nullが返る
	@Override
	public User findLoginUser(String email, String pass) {
		User user = null;

		try(var con = ds.getConnection()) {
			String sql = "SELECT * FROM users "
			        + "WHERE email = ?";
			var stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			var rs = stmt.executeQuery();

			if(rs.next()) {
				System.out.println("メールはOK");
				// パスワードのチェック
				String hashed = rs.getString("pass");
				if(BCrypt.checkpw(pass, hashed)) {
					System.out.println("パスワードもOK");
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					Integer age = (Integer) rs.getObject("age");
					user = new User(id, name, email, null, age);
				}
				else {
					System.out.println("パスワードは違う");
				}
			}
			else {
				System.out.println("メールが間違い");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return user;
	}

}
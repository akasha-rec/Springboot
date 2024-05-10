package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDao {
	private Connection con;

	public MemberDao() {
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getAllMembers() {
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> list = new ArrayList<>();
		
		String query = "SELECT * FROM members";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MemberVO mem = new MemberVO();
				
				mem.setId(rs.getInt("id"));
				mem.setName(rs.getString("name"));
				mem.setPass(rs.getString("pass"));
				mem.setRegidate(rs.getDate("regidate"));
				
				list.add(mem);
			}
		}
		catch (Exception e) {
			System.out.println("전체 멤버 목록 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public MemberVO getMemberId(Integer id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberVO m = new MemberVO();
		
		String query = "SELECT * FROM members where id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
			}
		}
		catch (Exception e) {
			System.out.println("조건에 맞는 멤버 검색 중 오류");
			e.printStackTrace();
		}
		finally {
			try {
				if(psmt != null) psmt.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		MemberVO m = new MemberVO();
		
		String query = "INSERT INTO members(pass, name) values (?, ?)";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("데이터 삽입 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if(psmt != null) psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		
		//update members set pass='pass6', name='name6' where id=6;
		String query = "UPDATE members SET pass=?, name=? where id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.setInt(3, memberVO.getId());
			psmt.executeUpdate();
			
			return getMemberId(memberVO.getId());
		} catch (Exception e) {
			System.out.println("수정 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null) psmt.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int removeMember(Integer id) {
		PreparedStatement psmt = null;
		
		String query = "DELETE from members where id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null) psmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
}

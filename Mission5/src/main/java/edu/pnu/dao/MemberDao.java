package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {
	private final DataSource dataSource;
	
	public List<MemberVO> getAllMembers() {
		Statement st = null;
		ResultSet rs = null;
		
		List<MemberVO> list = new ArrayList<>();
		
		String query = "Select * from members";
		
		try {
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				MemberVO mem = new MemberVO();
				
				mem.setId(rs.getInt("id"));
				mem.setName(rs.getString("name"));
				mem.setPass(rs.getString("pass"));
				mem.setRegidate(rs.getDate("regidate"));
				
				list.add(mem);
			}
		} catch (Exception e) {
			System.out.println("전체 목록 검색 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public MemberVO getMemberID(Integer id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberVO m = new MemberVO();
		
		String query = "Select * from members where id=?";
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setRegidate(rs.getDate("regidate"));
			}
		} catch (Exception e) {
			System.out.println("조건에 맞는 멤버 검색 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null) psmt.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return m;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		
		String query = "INSERT INTO members(pass, name) VALUES (?, ?)";
		
		try {
			psmt= dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.execute();
		} catch (Exception e) {
			System.out.println("멤버 삽입 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null) psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return memberVO;
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		
		String query = "UPDATE members SET "
				+ "pass = COALESCE(?, pass), "
				+ "name = COALESCE(?, name) "
				+ "WHERE id = ?";
		int result = 0;
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.setInt(3, memberVO.getId());
			
			result = psmt.executeUpdate();
			System.out.println(result + "개가 수정됐습니다.");
		} catch (Exception e) {
			System.out.println("수정 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null) psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return memberVO;
	}
	
	public int deleteMember(Integer id) {
		PreparedStatement psmt = null;
		
		String query = "DELETE FROM members where id=?";
		int result = 0;
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setInt(1, id);
			
			result = psmt.executeUpdate();
			System.out.println(result + "개가 삭제되었습니다.");
		} catch (Exception e) {
			System.out.println("삭제 중 오류 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (psmt != null) psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}

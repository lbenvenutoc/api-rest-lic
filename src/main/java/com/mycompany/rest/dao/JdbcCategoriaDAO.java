package com.mycompany.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.rest.domain.Categoria;
import com.mycompany.rest.domain.Cliente;

public class JdbcCategoriaDAO implements CategoriaDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Categoria> obtenerCategorias(int codCli) {
		String sql = "SELECT cat.codCat, cat.desCat FROM categoria cat INNER JOIN suscripcion s ON cat.codCat=s.codCat WHERE s.codCli=? and cat.estCat=1 and s.estSus=1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codCli);
			List<Categoria> lstCat = new ArrayList<Categoria>();
			ResultSet rs = ps.executeQuery();
			Categoria cat = null;

			while (rs.next()) {

				cat = new Categoria();
				cat.setCodCat(rs.getInt("codCat"));
				cat.setDesCat(rs.getString("desCat"));
				lstCat.add(cat);

			}
			rs.close();
			ps.close();
			return lstCat;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}
	}

	public List<Categoria> listarCategorias() {
		String sql = "SELECT cat.codCat, cat.desCat FROM categoria cat WHERE cat.estCat=1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Categoria> lstCat = new ArrayList<Categoria>();
			ResultSet rs = ps.executeQuery();
			Categoria cat = null;

			while (rs.next()) {
				cat = new Categoria();
				cat.setCodCat(rs.getInt("codCat"));
				cat.setDesCat(rs.getString("desCat"));
				lstCat.add(cat);

			}
			rs.close();
			ps.close();
			return lstCat;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}
	}

	public void insertarSuscription(Cliente cliente) {

		String query = "INSERT INTO suscripcion (codCli, codCat, estSus) VALUES (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			for (Categoria cat : cliente.getLstCat()) {
				con = dataSource.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, cliente.getCodCli());
				ps.setInt(2, cat.getCodCat());
				ps.setInt(3, cat.getEstaSeleccionado() ? 1 : 0);
				int out = ps.executeUpdate();
				ps.close();
				con.close();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

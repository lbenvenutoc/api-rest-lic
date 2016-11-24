package com.mycompany.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.rest.domain.Cliente;
import com.mysql.jdbc.Statement;

public class JdbcClienteDAO implements ClienteDAO {

	private DataSource dataSource;

	@Autowired
	CategoriaDAO categoriaDao;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Cliente insertarCliente(Cliente cliente) {
		String query = "INSERT INTO cliente (rucCli, razSocCli, corCli, pasCli, fecRegCli, tipTarCli, numTarCli, flgAboCli, estCli) VALUES (?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cliente.getRucCli());
			ps.setString(2, cliente.getRazSocCli());
			ps.setString(3, cliente.getCorCli());
			ps.setString(4, cliente.getPasCli());
			ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			if (cliente.getFlgAboCli() == 1) {
				ps.setString(6, cliente.getTipTarCli());
				ps.setString(7, cliente.getNumTarCli());
			} else {
				ps.setString(6, "");
				ps.setString(7, "");
			}
			ps.setInt(8, cliente.getFlgAboCli());
			ps.setInt(9, 1);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			int key = keys.getInt(1);
			if (key != 0) {
				cliente.setCodCli(key);
				categoriaDao.insertarSuscription(cliente);
				Cliente respuesta = null;
				respuesta = obtenerCliente(cliente.getRucCli(),
						cliente.getPasCli());
				return respuesta;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Cliente obtenerCliente(String rucCli, String claCli) {
		String sql = "SELECT c.* FROM cliente c  WHERE c.rucCli = ? AND c.pasCli = ? AND c.estCli=1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rucCli);
			ps.setString(2, claCli);
			Cliente cliente = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setCodCli(rs.getInt("codCli"));
				cliente.setRazSocCli(rs.getString("razSocCli"));
				cliente.setRucCli(rs.getString("rucCli"));
				cliente.setCorCli(rs.getString("corCli"));
				cliente.setFlgAboCli(rs.getInt("flgAboCli"));
				cliente.setLstCat(categoriaDao.obtenerCategorias(cliente
						.getCodCli()));
			}
			rs.close();
			ps.close();
			return cliente;
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

	public int existeCliente(Cliente cliente) {
		String sql = "SELECT count(c.codCli) as tiene FROM cliente c  WHERE c.rucCli = ? AND c.estCli=1";
		Connection conn = null;
		int resultado = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getRucCli());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				resultado = rs.getInt("tiene");
			}
			rs.close();
			ps.close();
			return resultado;
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
}

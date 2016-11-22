package com.mycompany.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.mycompany.rest.domain.Licitacion;

public class JdbcLicitacionDAO implements LicitacionDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Licitacion> obtenerLicitaciones(int codCat) {
		String sql = "SELECT lic.*, ec.rucEnt, ec.desEnt, ec.dirEnt, cat.desCat from licitacion lic INNER JOIN entidad_convocante ec ON lic.codEnt=ec.codEnt INNER JOIN categoria cat ON lic.codCat=cat.codCat WHERE lic.codCat=? AND lic.estLic=1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codCat);
			List<Licitacion> lstLic = new ArrayList<Licitacion>();
			ResultSet rs = ps.executeQuery();
			Licitacion lic = null;

			while (rs.next()) {
				lic = new Licitacion();
				lic.setCodLic(rs.getInt("codLic"));
				lic.setNomLic(rs.getString("nomLic"));
				lic.setNorAplLic(rs.getString("norAplLic"));
				lic.setCodEnt(rs.getInt("codEnt"));
				lic.setCodCat(rs.getInt("codCat"));
				lic.setDesEnt(rs.getString("desEnt"));
				lic.setDesLic(rs.getString("desLic"));
				lic.setDirEnt(rs.getString("dirEnt"));
				lic.setDesCat(rs.getString("desCat"));
				lic.setFecPubLic(rs.getString("fecPubLic"));
				lic.setFecTerLic(rs.getString("fecTerLic"));
				lic.setValRef(rs.getDouble("valRef"));
				lic.setVerSeaLic(rs.getInt("verSeaLic"));
				lic.setMonLic(rs.getString("monLic"));
				lstLic.add(lic);

			}
			rs.close();
			ps.close();
			return lstLic;
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
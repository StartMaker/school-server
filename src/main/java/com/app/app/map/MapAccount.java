package com.app.app.map;
import com.app.app.model.MolAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapAccount implements RowMapper<MolAccount> {

    @Override
    public MolAccount mapRow(ResultSet resultSet, int i) throws SQLException {
        MolAccount molAccount = new MolAccount();
        molAccount.setId(resultSet.getInt("id"));
        molAccount.setName(resultSet.getString("name"));
        molAccount.setPhone(resultSet.getString("password"));
        return molAccount;
    }
}

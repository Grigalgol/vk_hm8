package dao;

import commons.JDBCCredentials;
import entity.Company;
import generated.Tables;
import generated.tables.records.CompanyRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CompanyDao {
    private static final @NotNull JDBCCredentials CREDS = JDBCCredentials.DEFAULT;

    public @NotNull Company getCompanyByName(@NotNull String name) {
        try (var connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            var context = DSL.using(connection, SQLDialect.POSTGRES);
            CompanyRecord record = context.fetchOne(Tables.COMPANY, Tables.COMPANY.NAME.eq(name));
            if (record != null) return new Company(record.getId(), record.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalStateException("Company " + name + " is not exist");
    }

    public void saveCompany(@NotNull Company company) {
        try (var connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            var context = DSL.using(connection, SQLDialect.POSTGRES);
            context.newRecord(Tables.COMPANY)
                    .setName(company.getName())
                    .store();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

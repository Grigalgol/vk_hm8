package dao;

import commons.JDBCCredentials;
import entity.Company;
import entity.Product;
import generated.Tables;
import generated.tables.records.ProductRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final @NotNull JDBCCredentials CREDS = JDBCCredentials.DEFAULT;

    CompanyDao companyDao = new CompanyDao();

    public @NotNull List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try (var connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            var context = DSL.using(connection, SQLDialect.POSTGRES);
            Result<ProductRecord> result = context.fetch(Tables.PRODUCT);
            for (var record : result) {
                list.add(new Product(record.getId(), record.getName(), record.getCompanyId(), record.getCount()));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull List<Product> getAllProductsByCompany(String name) {
        Company company = null;
        try {
            company = companyDao.getCompanyByName(name);
        } catch (IllegalStateException e) {
            return new ArrayList<>();
        }
        List<Product> list = new ArrayList<>();
        try (var connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            var context = DSL.using(connection, SQLDialect.POSTGRES);
            Result<ProductRecord> result = context.fetch(Tables.PRODUCT);
            for (var record : result) {
                if (record.getCompanyId().equals(company.getId())) {
                    list.add(new Product(record.getId(), record.getName(), record.getCompanyId(), record.getCount()));
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveProduct(@NotNull Product product) {
        try (var connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            var context = DSL.using(connection, SQLDialect.POSTGRES);
            context.newRecord(Tables.PRODUCT)
                    .setName(product.getName())
                    .setCompanyId(product.getCompanyId())
                    .setCount(product.getCount())
                    .store();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteProduct(String name) {
        try (var connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            var context = DSL.using(connection, SQLDialect.POSTGRES);
            int i = context.delete(Tables.PRODUCT).where(Tables.PRODUCT.NAME.eq(name)).execute();
            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

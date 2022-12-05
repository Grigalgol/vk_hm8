package dao;

import com.google.inject.Inject;
import entity.Company;
import entity.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DataManager {

    private final ProductDao productDao;
    private final CompanyDao companyDao;

    @Inject
    public DataManager(ProductDao productDao, CompanyDao companyDao) {
        this.productDao = productDao;
        this.companyDao = companyDao;
    }

    public void saveProduct(@NotNull String name, @NotNull String companyName, @NotNull Integer count) {
        Company company;
        try {
            company = companyDao.getCompanyByName(companyName);
        } catch (IllegalStateException e) {
            companyDao.saveCompany(new Company(0, companyName));
            company = companyDao.getCompanyByName(companyName);
        }
        productDao.saveProduct(new Product(0, name, company.getId(), count));
    }

    public @NotNull List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public boolean deleteProductByName(String name) {
        return productDao.deleteProduct(name);
    }

    public List<Product> getProductByCompany(String name) {
        return productDao.getAllProductsByCompany(name);
    }
}

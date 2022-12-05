package api;

import com.google.inject.Inject;
import dao.DataManager;
import entity.Product;
import entity.ProductDto;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public final class ProductRest {

    private final DataManager dataManager;

    @Inject
    public ProductRest(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getProducts(){
        return Response
                .ok(dataManager.getAllProducts())
                .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("injected", "true")
                .build();
    }

    @GET
    @Path("/allByCompany")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getProductsByCompany(@QueryParam("name") String productName){
        return Response
                .ok(dataManager.getProductByCompany(productName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("injected", "true")
                .build();
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduct(@QueryParam("name") String productName) {
            boolean isDelete = dataManager.deleteProductByName(productName);
            if(isDelete) return Response.ok().header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("injected", "true").build();
            else return Response.status(404).header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("injected", "true").build();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ProductDto product) {
        dataManager.saveProduct(product.getName(), product.getCompanyName(), product.getCount());
        return Response.ok().header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("injected", "true").build();
    }
}

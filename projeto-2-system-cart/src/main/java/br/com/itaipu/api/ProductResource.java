package br.com.itaipu.api;

import br.com.itaipu.model.Product;
import br.com.itaipu.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product API", description = "API para gerenciamento de produtos")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos disponíveis")
    public Response getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obter produto por ID", description = "Retorna um produto específico")
    public Response getProduct(@PathParam("id") Long id) {
        try {
            Product product = productService.getProduct(id);
            return Response.ok(product).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Produto não encontrado: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/category/{category}")
    @Operation(summary = "Listar produtos por categoria", description = "Retorna produtos de uma categoria específica")
    public Response getProductsByCategory(@PathParam("category") String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos por categoria: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/search")
    @Operation(summary = "Buscar produtos por nome", description = "Busca produtos que contenham o nome especificado")
    public Response searchProducts(@QueryParam("name") String name) {
        try {
            List<Product> products = productService.searchProducts(name);
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar produtos: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Operation(summary = "Criar novo produto", description = "Cria um novo produto")
    public Response createProduct(Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return Response.status(Response.Status.CREATED).entity(createdProduct).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar produto: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza um produto existente")
    public Response updateProduct(@PathParam("id") Long id, Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return Response.ok(updatedProduct).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar produto: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir produto", description = "Remove um produto")
    public Response deleteProduct(@PathParam("id") Long id) {
        try {
            productService.deleteProduct(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao excluir produto: " + e.getMessage())
                    .build();
        }
    }
}
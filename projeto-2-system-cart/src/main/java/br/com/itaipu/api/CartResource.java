package br.com.itaipu.api;

import br.com.itaipu.model.Cart;
import br.com.itaipu.service.CartService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cart API", description = "API para gerenciamento de carrinho de compras")
public class CartResource {

    @Inject
    CartService cartService;

    @POST
    @Operation(summary = "Criar novo carrinho", description = "Cria um novo carrinho de compras")
    public Response createCart() {
        try {
            Cart cart = cartService.createCart();
            return Response.status(Response.Status.CREATED).entity(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar carrinho: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{cartId}")
    @Operation(summary = "Obter carrinho", description = "Retorna os detalhes de um carrinho específico")
    public Response getCart(@PathParam("cartId") String cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Carrinho não encontrado: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/{cartId}/items")
    @Operation(summary = "Adicionar item ao carrinho", description = "Adiciona um produto ao carrinho")
    public Response addItem(
            @PathParam("cartId") String cartId,
            @QueryParam("productId") Long productId,
            @QueryParam("quantity") Integer quantity) {
        try {
            Cart cart = cartService.addItem(cartId, productId, quantity);
            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao adicionar item: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{cartId}/items/{productId}")
    @Operation(summary = "Remover item do carrinho", description = "Remove um produto do carrinho")
    public Response removeItem(
            @PathParam("cartId") String cartId,
            @PathParam("productId") Long productId) {
        try {
            Cart cart = cartService.removeItem(cartId, productId);
            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao remover item: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{cartId}/items/{productId}")
    @Operation(summary = "Atualizar quantidade do item", description = "Atualiza a quantidade de um produto no carrinho")
    public Response updateItemQuantity(
            @PathParam("cartId") String cartId,
            @PathParam("productId") Long productId,
            @QueryParam("quantity") Integer quantity) {
        try {
            Cart cart = cartService.updateItemQuantity(cartId, productId, quantity);
            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar quantidade: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{cartId}")
    @Operation(summary = "Limpar carrinho", description = "Remove todos os itens do carrinho")
    public Response clearCart(@PathParam("cartId") String cartId) {
        try {
            Cart cart = cartService.clearCart(cartId);
            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao limpar carrinho: " + e.getMessage())
                    .build();
        }
    }
} 
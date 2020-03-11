
package com.mycompany.mavenproject2;

import com.hudsons.classes.Burger;
import com.hudsons.daos.DAOBurger;
import com.hudsons.daos.MyConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("burgers")
public class HudsonResource {

    @Context
    private UriInfo context;

   
    public HudsonResource() {
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Burger> lista;
        try {
            lista = DAOBurger.getInstance().getAll();
            GenericEntity<List<Burger>> burgers = new GenericEntity<List<Burger>>(lista) {
            };
            return Response.status(200).entity(burgers).build();
        } catch (SQLException ex) {
            Logger.getLogger(HudsonResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }

    }

    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response post(Burger burger) {
        try {
            DAOBurger.getInstance().create(burger);
            return Response.accepted().build();
        } catch (SQLException ex) {
            Logger.getLogger(HudsonResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
    }

    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response put(Burger content) {
        try {
            DAOBurger.getInstance().update(content);
            return Response.accepted().build();
        } catch (SQLException ex) {
            Logger.getLogger(HudsonResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        try {
            Burger burger = DAOBurger.getInstance().findById(id);
            if (burger == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(burger, MediaType.APPLICATION_JSON).build();
        } catch (SQLException ex) {
            Logger.getLogger(HudsonResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    @DELETE
    @Path("{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            DAOBurger.getInstance().delete(id);
            return Response.accepted().build();
        } catch (SQLException ex) {
            Logger.getLogger(HudsonResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

}

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import metier.BanqueLocal;
import metier.entities.Compte;

@Stateless
@Path("/")
public class BanqueRestService {
    @EJB
    private BanqueLocal banqueEJB;

    @POST
    @Path("/comptes")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte addCompte(Compte cp) {
        return banqueEJB.addCompte(cp);
    }

    @GET
    @Path("/comptes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte getCompte(@PathParam(value = "code") Long code) {
        return banqueEJB.getCompte(code);
    }

    @GET
    @Path("/comptes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compte> listCompes() {
        return banqueEJB.listCompes();
    }

    @PUT
    @Path("/comptes/verser")
    @Produces(MediaType.APPLICATION_JSON)
    public void verser(@FormParam(value = "code") Long code, @FormParam(value = "montant") double mt) {
        banqueEJB.verser(code, mt);
    }

    @PUT
    @Path("/comptes/retirer")
    @Produces(MediaType.APPLICATION_JSON)
    public void retirer(@FormParam(value = "code") Long code, @FormParam(value = "montant") double mt) {
        banqueEJB.retirer(code, mt);
    }

    @PUT
    @Path("/comptes/retirer")
    @Produces(MediaType.APPLICATION_JSON)
    public void virement(@FormParam(value = "code1") Long cp1, @FormParam(value = "code2") Long cp2, @FormParam(value = "montant") double mt) {
        banqueEJB.virement(cp1, cp2, mt);
    }
}

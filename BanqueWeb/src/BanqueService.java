import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.BanqueLocal;
import metier.entities.Compte;

@WebService
public class BanqueService {
    @EJB
    private BanqueLocal banqueEJB;

    @WebMethod
    public Compte addCompte(@WebParam(name = "solde") double solde) {
        Compte cp = new Compte();
        cp.setSolde(solde);
        cp.setDateCreation(new Date());
        return banqueEJB.addCompte(cp);
    }

    @WebMethod
    public Compte getCompte(Long code) {
        return banqueEJB.getCompte(code);
    }

    @WebMethod
    public List<Compte> listCompes() {
        return banqueEJB.listCompes();
    }

    @WebMethod
    public void verser(Long code, double mt) {
        banqueEJB.verser(code, mt);
    }

    @WebMethod
    public void retirer(Long code, double mt) {
        banqueEJB.retirer(code, mt);
    }

    @WebMethod
    public void virement(Long cp1, Long cp2, double mt) {
        banqueEJB.virement(cp1, cp2, mt);
    }

}

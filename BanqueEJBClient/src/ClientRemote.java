import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.BanqueRemote;
import metier.entities.Compte;

public class ClientRemote {

    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();

            BanqueRemote proxy = (BanqueRemote) ctx.lookup(beanFullName());

            createComptes(proxy);
            Compte cp = proxy.getCompte(1L);
            System.out.println("Comte : " + cp);
            makeoperations(proxy);
            printComptes(proxy);

        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String beanFullName() {
        String appName = "BanqueEAR";
        String moduleName = "BanqueEJB";
        String beanName = "BK";
        String remoteInterface = BanqueRemote.class.getName();
        // "/BanqueEJB/BK!metier.BanqueRemote" lorsque appname est vide
        String name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;
        return name;
    }

    private static void createComptes(BanqueRemote proxy) {
        proxy.addCompte(new Compte());
        proxy.addCompte(new Compte());
        proxy.addCompte(new Compte());
    }

    private static void makeoperations(BanqueRemote proxy) {
        proxy.verser(1L, 4000);
        proxy.retirer(1L, 2000);
        proxy.virement(1L, 2L, 1000);
    }

    private static void printComptes(BanqueRemote proxy) {
        proxy.listCompes().forEach(c -> System.out.println(c.toString()));
    }

}

package graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entities.Etudiant;
import entities.Option;
import repository.EtudiantBusiness;
import repository.OptionBusiness;

public class Mutations implements GraphQLMutationResolver {

    private final OptionBusiness optionBusiness;
    private final EtudiantBusiness etudiantBusiness;

    public Mutations(OptionBusiness optionBusiness, EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // ---------- Options ----------
    public boolean addOption(int codeOption, String libelle, String domaine, String responsable,
                             int credits, int semestre, int capacite) {
        Option option = new Option(codeOption, libelle, domaine, responsable, credits, semestre, capacite);
        return optionBusiness.addOption(option);
    }

    public boolean updateOption(int codeOption, String libelle, String domaine, String responsable,
                                int credits, int semestre, int capacite) {
        Option option = new Option(codeOption, libelle, domaine, responsable, credits, semestre, capacite);
        return optionBusiness.updateOption(codeOption, option);
    }

    public boolean deleteOption(int codeOption) {
        return optionBusiness.deleteOption(codeOption);
    }

    // ---------- Etudiants ----------
    public boolean addEtudiant(String identifiant, String nom, String prenom, int anneeEtude,
                               String email, int codeOption) {
        // addEtudiant du repository ne lit que le codeOption puis retrouve l'option complète
        Option option = new Option();
        option.setCodeOption(codeOption);
        Etudiant etudiant = new Etudiant(identifiant, nom, prenom, option, anneeEtude, email);
        return etudiantBusiness.addEtudiant(etudiant);
    }

    public boolean updateEtudiant(String identifiant, String nom, String prenom, int anneeEtude,
                                  String email, int codeOption) {
        // updateEtudiant du repository remplace l'objet tel quel : il faut donc l'option complète
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return false;
        }
        Etudiant etudiant = new Etudiant(identifiant, nom, prenom, option, anneeEtude, email);
        return etudiantBusiness.updateEtudiant(identifiant, etudiant);
    }

    public boolean deleteEtudiant(String identifiant) {
        return etudiantBusiness.deleteEtudiant(identifiant);
    }
}

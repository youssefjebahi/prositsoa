package graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import entities.Etudiant;
import entities.Option;
import repository.EtudiantBusiness;
import repository.OptionBusiness;

import java.util.Collections;
import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final OptionBusiness optionBusiness;
    private final EtudiantBusiness etudiantBusiness;

    public Query(OptionBusiness optionBusiness, EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // ---------- Options ----------
    public List<Option> getAllOptions() {
        return optionBusiness.getListeOptions();
    }

    public Option getOptionByCode(int codeOption) {
        return optionBusiness.getOptionByCode(codeOption);
    }

    public List<Option> getOptionsByDomaine(String domaine) {
        return optionBusiness.getOptionsByDomaine(domaine);
    }

    // ---------- Etudiants ----------
    public List<Etudiant> getAllEtudiants() {
        return etudiantBusiness.getAllEtudiants();
    }

    public Etudiant getEtudiantByIdentifiant(String identifiant) {
        return etudiantBusiness.getEtudiantByIdentifiant(identifiant);
    }

    public List<Etudiant> getEtudiantsByOption(int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return Collections.emptyList();
        }
        return etudiantBusiness.getEtudiantsByOption(option);
    }
}

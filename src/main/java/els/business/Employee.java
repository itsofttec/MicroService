package els.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee  {

    private final long id;
    private final String trigramme;
    private final String nom;
    private final String prenom;
    private final String job;
    
    @JsonCreator
    public Employee(@JsonProperty(value = "id") long id, @JsonProperty(value = "trigramme") String trigramme, @JsonProperty(value = "nom") String nom, @JsonProperty(value = "prenom") String prenom, @JsonProperty(value = "job") String job) {
        this.id = id;
        this.trigramme = trigramme;
        this.nom = nom;
        this.prenom = prenom;
        this.job = job;
    }

    public long getId() {
        return id;
    }

    public String getTrigramme() {
        return trigramme;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public String getJob() {
        return job;
    }
    
    @Override
    public String toString() {
        return "Employee:{id="+id+",trigramme="+trigramme+",prenom="+prenom+",nom="+nom+",job="+job+"}";
    }
}

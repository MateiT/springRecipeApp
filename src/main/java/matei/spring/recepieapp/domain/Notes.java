package matei.spring.recepieapp.domain;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recepieNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecepie() {
        return recipe;
    }

    public void setRecepie(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecepieNotes() {
        return recepieNotes;
    }

    public void setRecepieNotes(String recepieNotes) {
        this.recepieNotes = recepieNotes;
    }
}

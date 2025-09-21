package StreamingProject.demo.entity;

import jakarta.persistence.*;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Perfil perfil;
    @ManyToOne
    private Video video;
    private int nota;
    private String comantario;

    public Avaliacao()  {
    }

    public Avaliacao(int id, Perfil perfil, Video video, int nota, String comantario) {
        this.id = id;
        this.perfil = perfil;
        this.video = video;
        this.nota = nota;
        this.comantario = comantario;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Video getVideo() {
        return video;
    }
    public void setVideo(Video video) {
        this.video = video;
    }

    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComantario() {
        return comantario;
    }
    public void setComantario(String comantario) {
        this.comantario = comantario;
    }
}

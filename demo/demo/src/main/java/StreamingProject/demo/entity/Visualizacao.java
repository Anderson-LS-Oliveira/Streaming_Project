package StreamingProject.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Visualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;
    private LocalDateTime data_hora;
    private int progresso;

    public Visualizacao()  {
    }

    public Visualizacao(int id, Perfil perfil, Video video, LocalDateTime data_hora, int progresso) {
        this.id = id;
        this.perfil = perfil;
        this.video = video;
        this.data_hora = data_hora;
        this.progresso = progresso;
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

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        this.progresso = progresso;
    }
}

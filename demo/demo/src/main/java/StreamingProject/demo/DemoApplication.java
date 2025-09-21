package StreamingProject.demo;

import StreamingProject.demo.entity.Categoria;
import StreamingProject.demo.entity.Usuario;
import StreamingProject.demo.entity.Video;
import StreamingProject.demo.repository.CategoriaRepository;
import StreamingProject.demo.repository.UsuarioRepository;
import StreamingProject.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> buscarPorTitulo(scanner);
                case 2 -> listarPorCategoria(scanner);
                case 3 -> listarTop10Avaliados();
                case 4 -> listarTop10Assistidos();
                case 5 -> usuarioQueMaisAssistiu();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n==============================");
        System.out.println("       SISTEMA DE STREAMING");
        System.out.println("==============================");
        System.out.println("1 - Buscar vídeos por título");
        System.out.println("2 - Listar vídeos de uma categoria");
        System.out.println("3 - Top 10 vídeos mais bem avaliados");
        System.out.println("4 - Top 10 vídeos mais assistidos");
        System.out.println("5 - Usuário que mais assistiu vídeos");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void buscarPorTitulo(Scanner scanner) {
        System.out.print("Digite parte do título: ");
        String titulo = scanner.nextLine();

        List<Video> videos = videoRepository.findByTituloContainingOrderByTituloAsc(titulo);

        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com esse título.");
        } else {
            System.out.println("\nResultados da busca:");
            videos.forEach(this::exibirVideo);
        }
    }

    private void listarPorCategoria(Scanner scanner) {
        System.out.print("Digite o nome da categoria: ");
        String nomeCategoria = scanner.nextLine();

        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(nomeCategoria);

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            List<Video> videos = videoRepository.findByCategoriaOrderByTituloAsc(categoria);

            if (videos.isEmpty()) {
                System.out.println("Nenhum vídeo encontrado nessa categoria.");
            } else {
                System.out.println("\nVídeos da categoria " + nomeCategoria + ":");
                videos.forEach(this::exibirVideo);
            }
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    private void listarTop10Avaliados() {
        System.out.println("\nTop 10 vídeos mais bem avaliados:");
        List<Video> videos = videoRepository.findTop10MaisBemAvaliados(PageRequest.of(0, 10));

        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            videos.forEach(this::exibirVideo);
        }
    }

    private void listarTop10Assistidos() {
        System.out.println("\nTop 10 vídeos mais assistidos:");
        List<Video> videos = videoRepository.findTop10MaisAssistidos(PageRequest.of(0, 10));

        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            videos.forEach(this::exibirVideo);
        }
    }

    private void usuarioQueMaisAssistiu() {
        System.out.println("\nUsuário que mais assistiu vídeos:");
        Usuario usuario = usuarioRepository.findUsuarioQueMaisAssistiu();

        if (usuario != null) {
            System.out.println("Usuário: " + usuario.getNome());
        } else {
            System.out.println("Nenhum usuário encontrado.");
        }
    }

    private void exibirVideo(Video v) {
        System.out.println("- " + v.getTitulo() +
                " | " + v.getDescricao() +
                " | " + v.getDuracao() + "min" +
                " | Categoria: " + (v.getCategoria() != null ? v.getCategoria().getNome() : "N/A"));
    }
}

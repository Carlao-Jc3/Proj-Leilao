/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Carlos Eduardo Henrique Garibaldi
 */
/*
Método construtor
 */
public class ProdutosDTO {

    private Integer id;
    private String nome;
    private Integer valor;
    private String status;

    /*
    Método construtor vazio
     */
    public ProdutosDTO() {
    }

    public ProdutosDTO(Integer id, String nome, Integer valor, String status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Define o nome do produto com validações.
     *
     * @param nome Nome a ser definido.
     * @throws IllegalArgumentException se o nome for nulo, vazio, maior que 100
     * caracteres ou contiver caracteres inválidos.
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio!");
        }
        if (nome.length() > 100) {
            throw new IllegalArgumentException("O nome do produto não pode exceder 100 caracteres!");
        }
        if (!nome.matches("^[a-zA-Z0-9\\s]+$")) {
            throw new IllegalArgumentException("O nome do produto deve conter apenas letras, números e espaços!");
        }
        this.nome = nome.trim();
    }

}

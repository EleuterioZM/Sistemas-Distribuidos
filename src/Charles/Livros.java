package Charles;



import java.io.Serializable;

/**
 *
 * @author Cadu Piuza
 */
public class Livros implements Serializable {

    private String nome;
    private String autor;
    private String editora;
    private int data_de_publicacao;
    private String Disponibilidade;
    private Client2 Client;
    private Client2 Reserva;

    public Livros(String nome, String autor, String editora, int data_de_publicacao,String Disponibilidade, Client2 Client, Client2 Reserva) {
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
        this.data_de_publicacao = data_de_publicacao;
        this.Disponibilidade = "Available";
        this.Client = null;
        this.Reserva = null;
    }

    public String getNome(){
        return nome;
    }

    public String getAutor(){
        return autor;
    }

    public String getEditora(){
        return editora;
    }

    public int getdata_de_publicacao(){
        return data_de_publicacao;
    }

    public String getDisponibilidade(){
        return Disponibilidade;
    }

    public void setDisponibilidade(String Disponibilidade) {
        this.Disponibilidade = Disponibilidade;
    }

    public Client2 getCliente(){
        return Client;
    }

    public void setClient(Client2 client) {
        this.Client = client;
    }

    public Client2 getReserva(){
        return Reserva;
    }

    public void setReserva(Client2 Reserva) {
        this.Reserva = Reserva;
    }




    public String toString(){
        return ("Livros => " +
                "Nome: \n" + nome +
                "Autor: \n" + autor +
                "Editora: \n" + editora +
                "Data_de_publicacao: \n" + data_de_publicacao +
                "Disponibilidade: \n" + Disponibilidade +
                "Cliente: \n" + Client +
                "Reserva: \n" + Reserva);
    }

}



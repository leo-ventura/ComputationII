public class Form {
    String nome = "Banana";
    String sala = "f15";

    @Campo(maxLength = 5, required = true, label = "Nome:")
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
    @Campo(maxLength=10, label="# Sala")
    public void setSala(String sala) {this.sala=sala;}
    public String getSala() {return this.sala;}

}
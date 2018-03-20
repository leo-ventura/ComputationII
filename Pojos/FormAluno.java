class FormAluno {
    private String nome;
    private String celular;

    public void setNome(String n) {  this.nome = n;  }
    public String getNome() {  return this.nome;  }

    public void setCelular(String c) {  this.celular = c;  }
    public String getCelular() {  return this.celular;  }

    public String toString() {  return getNome() + " | " + getCelular();  }
}
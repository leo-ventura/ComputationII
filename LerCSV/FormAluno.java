class FormAluno {
    private String nome;
    private String celular;
    private String id;
    private String email;

    public void setNome(String n) {  this.nome = n;  }
    public String getNome() {  return this.nome;  }

    public void setCelular(String c) {  this.celular = c;  }
    public String getCelular() {  return this.celular;  }

    public void setId(String id) {  this.id = id;  }
    public String getId() {  return this.id;  }

    public void setEmail(String email) {  this.email = email;  }
    public String getEmail() {  return this.email;  }

    public void setIdade(String idade) {}

    public String toString() {  return "{id =>" + getId() + " | " + "nome => " + getNome() + " | " + "celular => " + getCelular() +
                                " | " + "email => " + getEmail() + "}";}
}
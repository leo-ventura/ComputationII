class Aluno {
    private String nome;
    private String celular;
    private String email;
    private Integer id;
    private String nascimento;

    public void setNascimento(String n) {  this.nascimento = n;  }
    public String getNascimento() {  return this.nascimento;  }

    public void setNome(String n) {  this.nome = n;  }
    public String getNome() {  return this.nome;  }

    public void setCelular(String c) {  this.celular = c;  }
    public String getCelular() {  return this.celular;  }

    public void setId(Integer id) {  this.id = id;  }
    public Integer getId() {  return this.id;  }

    public void setEmail(String email) {  this.email = email;  }
    public String getEmail() {  return this.email;  }

    public void setIdade(String idade) {}

    public String toString() {  return "{id =>" + getId() + " | " + "nome => " + getNome() + " | " + "celular => " + getCelular() +
                                " | " + "email => " + getEmail() + "}\n";}
}
package br.com.medpro.model.dto;

public record DadosEndereco(
    String logradouro,
    String bairro,
    String cep,
    String cidade,
    String uf,
    String numero,
    String complemento) {
    
    public void atualizarInformacoes(DadosEndereco dados){
        if(dados.logradouro() != null{
            if(dados.logradouro().isBlank()){
                throw new IllegalArgumentException("Logradouro não pode estar em branco");
            }
        }
    }
}

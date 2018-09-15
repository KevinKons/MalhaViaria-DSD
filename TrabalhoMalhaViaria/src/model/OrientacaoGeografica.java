package model;

public enum OrientacaoGeografica {
    
    NORTE("norte"),
    SUL("sul"),
    LESTE("leste"),
    OESTE("oeste");
    
    private String orientacao;

    OrientacaoGeografica(String orientacao) {
        this.orientacao = orientacao;
    }
    
}

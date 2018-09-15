package model.fabricaDeMalha;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.MalhaViaria;

public abstract class FabricaAbstrataDeMalha {
    
    public abstract MalhaViaria criarMalha(String nomeArquivo) throws FileNotFoundException, IOException;
}

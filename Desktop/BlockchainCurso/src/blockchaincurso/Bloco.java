/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchaincurso;

/**
 *
 * @author bruno
 */

import java.util.Date;

public class Bloco {
    
    public String hash; //guardará a assinatura digital
    public String hashAnterior;
    private String dados; //dados serao mensagem simples
    private long timeStamp; //numero de milisegundos desde 1/1/1970
    private int nonce;
    
    //Construtor do bloco
    
    public Bloco (String dados, String hashAnterior ){
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.timeStamp = new Date().getTime();
        this.hash = calcularHash(); //adicionar depois da implementacao
    }
    
    public String calcularHash() {
        //concatenação que formará o hash
        //1a parte
//        String hashCalculado = StringUtil.applySha256(
//        hashAnterior + Long.toString(timeStamp) + dados);

//2a parte
        String hashCalculado = StringUtil.applySha256(
        hashAnterior + Long.toString(timeStamp) + Integer.toString(nonce) + dados);
        return hashCalculado;
        //adicionar o método para o construtor do bloco 
    }
    
    public void minerarBloco(int dificuldade) {
        String target = new String(new char[dificuldade]).replace('\0', '0'); //criar
        while(!hash.substring(0, dificuldade).equals(target)) {
            nonce++;
            hash = calcularHash();
        }
        System.out.println("Bloco minerado: " + hash);
    }
    
    
    
}

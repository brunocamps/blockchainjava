/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/gson-2.6.2.jar
package blockchaincurso;

/**
 *
 * @author bruno
 */

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;


public class BlockchainCurso {
    
public static ArrayList<Bloco> blockchain = new ArrayList<Bloco>();
	public static HashMap<String,TransactionOutputs> UTXOs = new HashMap<String,TransactionOutputs>(); //list of all unspent transactions. 
	public static int dificuldade = 5;
	public static Wallet walletA;
	public static Wallet walletB;
        
    public static void main(String[] args) {
        
        //4a parte
        //Setup Bouncey castle as a Security Provider
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		//Create the new wallets
		walletA = new Wallet();
		walletB = new Wallet();
		//Test public and private keys
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		//Create a test transaction from WalletA to walletB 
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		//Verify the signature works and verify it from the public key
		System.out.println("Is signature verified");
		System.out.println(transaction.verifiySignature());
        
        //1a parte
        //testar as classes Bloco e StringUtil
        //o primeiro bloco é chamado de GenesisBlock
//        Bloco genesisBlock = new Bloco("Primeiro bloco", "0"); //hash é zero
//        System.out.println("Hash do bloco 1: " + genesisBlock.hash);
//        
//        Bloco segundoBloco = new Bloco("Segundo bloco", genesisBlock.hash); 
//        System.out.println("Hash do bloco 2: " + segundoBloco.hash);
//        
//        Bloco terceiroBloco = new Bloco("Terceiro bloco", segundoBloco.hash);
//        System.out.println("Hash do bloco 3: " + terceiroBloco.hash);
       //Cada bloco terá sua própria assinatura digital de acordo com seus dados
       //e na informação do bloco anterior
       
       //2a parte
       
       //add our blocks to the blockchain ArrayList:
//		blockchain.add(new Bloco("Primeiro bloco", "0"));		
//		blockchain.add(new Bloco("Segundo bloco",blockchain.get(blockchain.size()-1).hash)); 
//		blockchain.add(new Bloco("Terceiro bloco",blockchain.get(blockchain.size()-1).hash));
//		
//		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
//                System.out.println(blockchainJson);
       //3a parte
//       blockchain.add(new Bloco("Primeiro bloco", "0"));
//		System.out.println("Minerando bloco 1 ");
//		blockchain.get(0).minerarBloco(dificuldade);
//		
//		blockchain.add(new Bloco("Segundo bloco",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Minerando bloco 2 ");
//		blockchain.get(1).minerarBloco(dificuldade);
//		
//		blockchain.add(new Bloco("Terceiro bloco",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Minerando bloco 3 ");
//		blockchain.get(2).minerarBloco(dificuldade);	
//		
//		System.out.println("\nValidade do blockchain: " + validarBlockchain());
//		
//		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//		System.out.println("\nA blockchain: ");
//                System.out.println(blockchainJson);

    
    }
    
    //quualquer mudança nos blocos fara com que esse método retorne falso
    public static Boolean validarBlockchain() {
       Bloco blocoAtual;
       Bloco blocoAnterior;
       String hashTarget = new String(new char[dificuldade]).replace('\0', '0');
       
       //loop por toda a blockchain para verificar hashes
       for(int i=1; i < blockchain.size(); i++){
           blocoAtual = blockchain.get(i);
           blocoAnterior = blockchain.get(i-1);
           
           
           //comparar o hash anterior e o hash calculado
           if(!blocoAtual.hash.equals(blocoAtual.calcularHash())){
               System.out.println("Hashes atuais não são iguais");
               return false;
           }
           
           //comparar o hash anterior e o hash registrado  
           if(!blocoAnterior.hash.equals(blocoAtual.hashAnterior)){
               System.out.println("Hashes anteriores não são iguais");
               return false;
           }
           
           //checar se o hash já foi minerado
           if(!blocoAtual.hash.substring(0, dificuldade).equals(hashTarget)) {
		System.out.println("O bloco não foi minerado");
		return false;
            }
           
       }
       return true;
    }
    
}


/*Your blockchain:
> Is made up of blocks that store data. 
> Has a digital signature that chains your blocks together.
> Requires proof of work mining to validate new blocks.
> Can be check to see if data in it is valid and unchanged.

*/
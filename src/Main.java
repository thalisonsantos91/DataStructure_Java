import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		Random random = new Random(); 
		int numero = random.nextInt(40);
		System.out.println(numero);
		
		
		
		System.out.println("Quantidade de arvores: ");
		int n = sc.nextInt(); 
		
		
		ArvoreBinaria ab = new ArvoreBinaria(new Elemento(numero));
		ArvoreAVL avl = new ArvoreAVL(new Elemento(numero));
		
	
		long comecoinserir = System.currentTimeMillis();
		for(int i = 1; i < n; i++) {
			Elemento ele = new Elemento (random.nextInt(40));	
			ab.inserir(ele);	
			avl.inserir(ele);
		}
		
		long fiminserir = System.currentTimeMillis();
		long totalINS = fiminserir -comecoinserir;
		System.out.println("tempo de inser��o: "+totalINS);
		
		
		System.out.println();
		System.out.println("Voc� deseja imprimir as duas �rvores? (1- Sim/ 2- N�o)");
		int arv = sc.nextInt();
		if(arv == 1) {
			long comeco = System.currentTimeMillis();
			
			System.out.println("-----------------ARVORE Binaria----------------");
			ab.calcularBalanceamento();
			System.out.println(ab.printArvore(0));
			long fim = System.currentTimeMillis();
			long total = fim - comeco;
			long comeco2 = System.currentTimeMillis();
			
			System.out.println("-------------------ARVORE AVL------------------");
			
			
			avl = avl.verificaBalanceamento();
			avl.calcularBalanceamento();
			System.out.println(avl.printArvore(0));
			long fim2 = System.currentTimeMillis();
			long total2 = fim2 - comeco2;
			System.out.println("Tempo  para rodar a Arvore Binaria: "+(total)+" (Mil�simo)");
			System.out.println("Tempo  para rodar a Arvore AVL: "+(total2)+" (Mil�simo)");
			System.out.println();
			
			long dif = total - total2;
			long dif2 = total2 - total;
			if(total > total2) {
				System.out.println("A ARVORE BINARIA demorou mais para rodar com "+dif+ " de diferen�a de tempo (Mil�simo)");
			}else if(total2 > total) {
				System.out.println("A ARVORE AVL demorou mais para rodar com "+dif2+ " de diferen�a de tempo (Mil�simo)");
			}else {
				System.out.println("Mesmo tempo");
			}
			
		}
		
		System.out.println();
		System.out.println("Voc� quer imprimir as arvoresAVL e binaria em  in orden, pos ordem e pre ordem");
		System.out.println("1- Sim / 2- N�o");
		int ord = sc.nextInt();
		long comecoImpressao = System.currentTimeMillis();
		if(ord == 1) {
			
			System.out.println("PRE ORDEM BINARIAS");
			ab.imprimirPreOrdem();
		
			System.out.println();
			System.out.println("IN ORDEM BINARIA");
			ab.imprimirInOrdem();
			
			System.out.println();
			System.out.println("POS ORDEM BINARIA");
			ab.imprimirPosOrdem(); 
			
			System.out.println();
			System.out.println("PRE ORDEM AVL");
			avl.imprimirPreOrdem();
		
			System.out.println();
			System.out.println("IN ORDEM AVL");
			avl.imprimirInOrdem();
			
			System.out.println();
			System.out.println("POS ORDEM AVL");
			avl.imprimirPosOrdem(); 
			
			
		}else {
			System.out.println("Proxima pergunta");
		}
		
		long fimImpressao = System.currentTimeMillis();
		long totalIMP =fimImpressao - comecoImpressao;
		System.out.println("tempo de Impress�o: "+totalIMP);
		System.out.println();
		
		
		
		System.out.println("Escolha um n�mero para remover: ");
		int n4 = sc.nextInt();
		
		long comecoRemocao = System.currentTimeMillis();
		
		Elemento elem = new Elemento(n4);
		ab = ab.remover(elem);
		
		
		long fimRemo��o = System.currentTimeMillis();
		long totalREM =fimRemo��o - comecoRemocao;
		System.out.println("tempo de Remo��o: "+totalREM);
		
		System.out.println("Nova arvore");
		ab.imprimirInOrdem();
		System.out.println();
		
		System.out.println("Deseja remover algum outro numero? 1- sim/ 2- n�o ");
				 
		int n3 = sc.nextInt();
		
		while(n3 == 1) {
			System.out.println("Escolha o numero: ");
			avl.imprimirInOrdem();
			int n5 = sc.nextInt();
			Elemento elem1 = new Elemento(n5);
			
			 
			 ab = ab.remover(elem1);
			
			System.out.println("Nova arvore");
			ab.imprimirInOrdem();
			System.out.println();
			
			System.out.println("Deseja remover algum outro numero? 1- sim/ 2- n�o ");
			 n3 = sc.nextInt();
		}
		
	
		
		System.out.println("Escolha um numero que deseja ver se exite: ");
		n4 = sc.nextInt();
		long comecoExiste = System.currentTimeMillis();
		
		System.out.println("O elemento "+n4+ (ab.busca(n4)?" Existe": " N�o existe"));
		
		long fimExiste = System.currentTimeMillis();
		long totalEXI =fimExiste - comecoExiste;
		
		
		System.out.println("Deseja ver se outra arvore existe? 1- sim / 2- n�o ");
		
		n3 = sc.nextInt();
		
		while(n3 == 1) {
			System.out.println("Escolha o numero: ");
			n4 = sc.nextInt();
			
			System.out.println("O elemento "+n4+ (ab.busca(n4)?" Existe": " N�o existe"));
			System.out.println("Deseja ver se outra arvore existe? 1- sim / 2- n�o ");
			n3 = sc.nextInt();
		}
		
		System.out.println("tempo de Existe: "+totalEXI);
		if(n3 != 1) {
			
			
			if(totalEXI < totalIMP && totalEXI <  totalINS && totalEXI < totalREM) {
				System.out.println("(existe) foi mais eficiente");
			}
			else if(totalEXI > totalIMP && totalIMP <  totalINS && totalIMP < totalREM) {
				System.out.println("(impress�o) foi mais eficiente");
			}
			else if(totalINS < totalIMP && totalEXI >  totalINS && totalINS < totalREM) {
				System.out.println("(inserir) foi mais eficiente");
			}
			else if(totalREM < totalIMP && totalREM <  totalINS && totalEXI > totalREM) {
				System.out.println("(Remo��o) foi mais eficiente");
			}else {
				System.out.println("teve tempos iguais");
			}
			
			
			
			
			System.out.println("Obrigado, volte sempre ");
		}	
	}
}
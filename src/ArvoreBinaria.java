


public class ArvoreBinaria {
	private Elemento elemento;
	private ArvoreBinaria direita;
	private ArvoreBinaria esquerda;
	private int bal;
	
	

	public ArvoreBinaria(Elemento elemento) {
		this.elemento = elemento;
		
	}
	
	public int calcularAltura() {
	
		if(this.esquerda == null & this.direita == null) {
			return 1; 
		}
		else if (this.esquerda != null && this.direita == null){
			return 1 + this.esquerda.calcularAltura(); 
		}
		else if (this.esquerda == null && this.direita != null) {
			return 1 + this.direita.calcularAltura();		
		}
		else {
			return 1 + Math.max(this.esquerda.calcularAltura(), this.direita.calcularAltura());
		}
	}
	
	public void calcularBalanceamento() {

		if(this.direita == null && this.esquerda == null) {
			this.bal = 0;
		}
	
		else if(this.esquerda == null && this.direita != null) {
			this.bal = this.direita.calcularAltura() - 0;                                                //direita - 0 que é o nulo da esquerda
		}
		else if(this.esquerda != null && this.direita == null) {
			this.bal = 0 - this.esquerda.calcularAltura();
		}
		else {
			this.bal = this.direita.calcularAltura() - this.esquerda.calcularAltura();		
		}
		if(this.direita != null) this.direita.calcularBalanceamento();                                                // só para nó raiz
		if(this.esquerda != null) this.esquerda.calcularBalanceamento();                                            // só para nó raiz
	}
	
	
	public ArvoreBinaria remover(Elemento elemento) {
	
		if(this.elemento.getValor() == elemento.getValor()) {
			
			 if(this.direita == null && this.esquerda == null) {
				 return null;
			 }else {
				
				 if(this.esquerda != null && this.direita == null) {
					 return this.esquerda;
				 } 
				
				 else if(this.esquerda == null && this.direita != null) {
					 return this.direita;
				 } 
				
				 else {
					 ArvoreBinaria aux = this.esquerda;
					 while(aux.direita != null) {
						 aux = aux.direita;
					 }
				
					 this.elemento = aux.getElemento();
				
					 aux.setElemento(elemento);
					 aux.remover(elemento);
					 this.esquerda = esquerda.remover(elemento);
				 }
			 }
		}
		else if(elemento.getValor() < this.elemento.getValor()) {
			this.esquerda = this.esquerda.remover(elemento); 
		}
		else if(elemento.getValor() > this.elemento.getValor()) {
			this.direita = this.direita.remover(elemento); 
		} 
		return this;
	}
	

	public boolean isEmpty() {
		return (this.elemento == null);
	}
	
	
	public void inserir(Elemento novoElemento) {
		if(isEmpty()) {
		
			this.elemento = novoElemento;
		}else {
			
		
			ArvoreBinaria novaArvore = new ArvoreBinaria(novoElemento);
			if(novoElemento.getValor() < this.elemento.getValor()) { 
				if(this.esquerda == null) { 
					this.esquerda = novaArvore;
					
				}else {
					this.esquerda.inserir(novoElemento); 
				}
			}else if(novoElemento.getValor() > this.elemento.getValor()){
				if(this.direita == null) {
					this.direita = novaArvore;
				
				}else {
					this.direita.inserir(novoElemento);
				}
				
			}else {
				System.out.println("O elemento "+novoElemento.getValor()+" foi removido por ser repetido");
				
			}
		}
	}
	
	public boolean busca(int valor) {
		if(isEmpty()) {
			return false;
		}
		if(this.elemento.getValor() == valor){
			return true;
		}else {
			if(valor < this.elemento.getValor()) {
				if(this.esquerda == null) {
					return false;
				}else {
					return this.esquerda.busca(valor); 
				}
			}
			else if(valor > this.elemento.getValor()) {
				if(this.direita == null) {
					return false;
				}else {
					return this.direita.busca(valor); 
				}
			}
			return false;
		}
	}

	public void imprimirPreOrdem() {
		if(!isEmpty()) {
			System.out.print(this.elemento.getValor()+" ");
			if(this.esquerda != null) {
				this.esquerda.imprimirPreOrdem();
			}
			if(this.direita != null){
				this.direita.imprimirPreOrdem();
			}
		}
	}

	public void imprimirInOrdem() {
		if(!isEmpty()) {
			if(this.esquerda != null) {
				this.esquerda.imprimirInOrdem();
			}
			System.out.print(this.elemento.getValor() + " ");
			if(this.direita != null) {
				this.direita.imprimirInOrdem();
			}
		}
	}
	
	public void imprimirPosOrdem() {
		if(!isEmpty()) {
			if(this.esquerda != null) {
				this.esquerda.imprimirPosOrdem();
			}
			if(this.direita != null) {
				this.direita.imprimirPosOrdem();
			}
			System.out.print(this.elemento.getValor() + " ");
		}
	}
	
	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public ArvoreBinaria getDireita() {
		return direita;
	}

	public void setDireita(ArvoreBinaria direita) {
		this.direita = direita;
	}

	public ArvoreBinaria getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(ArvoreBinaria esquerda) {
		this.esquerda = esquerda;
	}
	
	
	public String printArvore(int level) {
		String str = this.toString()+"\n";
		for (int i=0; i<level; i++){
            str += "\t";
		}
	    if (this.esquerda != null){
	        str += "+-ESQ: "+this.esquerda.printArvore(level + 1);
	    }
	    else{
	        str += "+-ESQ: NULL";
	    }
	    str += "\n";
	    for (int i=0; i<level; i++){
	             str += "\t";
	    }
	    if (this.direita != null){
	        
	        str += "+-DIR: "+this.direita.printArvore(level + 1); 
	    }
	    else{
	        str += "+-DIR: NULL";
	    }
	    str += "\n";
	    return str;
		
	}

	@Override
	public String toString() { 
		return "["+this.elemento.getValor()+"] "+"("+this.bal+")";
	}
	
	
}

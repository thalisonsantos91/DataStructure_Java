
public class ArvoreAVL{

	private Elemento ele;
	private ArvoreAVL dir;
	private ArvoreAVL esq;
	private int bal;
	
	public ArvoreAVL() {
		
	}

	public ArvoreAVL(Elemento ele) {
		this.ele = ele;
		
	}
	
	public int calcularAltura() {
		
		if(this.esq == null & this.dir == null) {
			return 1;
		}
		else if (this.esq != null && this.dir == null){
			return 1 + this.esq.calcularAltura(); 
		}
		else if (this.esq == null && this.dir != null) {
			return 1 + this.dir.calcularAltura();		
		}
		else {
			return 1 + Math.max(this.esq.calcularAltura(), this.dir.calcularAltura());
		}
	}
	
	
	public void calcularBalanceamento() {
		
		if(this.dir == null && this.esq == null) {
			this.bal = 0;
		}
		
		else if(this.esq == null && this.dir != null) {
			this.bal = this.dir.calcularAltura() - 0;
		}
		else if(this.esq != null && this.dir == null) {
			this.bal = 0 - this.esq.calcularAltura();
		}
		else {
			this.bal = this.dir.calcularAltura() - this.esq.calcularAltura();		
		}
		if(this.dir != null) this.dir.calcularBalanceamento();
		if(this.esq != null) this.esq.calcularBalanceamento();
	}
	
	public ArvoreAVL verificaBalanceamento() {
		
		if (this.bal >=2 || this.bal <= -2){
            if (this.bal >= 2){
            	if (this.bal * this.dir.getBal() > 0){
                    System.out.println("Rotacao Simples Direita");
               
                    return rotacaoSimplesDireita();
                }
                else{ //rotação dupla
                    System.out.println("Rotacao Dupla Direita");
                    
                    return rotacaoDuplaDireita();
                }
            }
            else{  
                if (this.bal * this.esq.getBal() > 0){
                    System.out.println("Rotacao Simples Esquerda");
                    
                    return rotacaoSimplesEsquerda();
                }
                else{
                    System.out.println("Rotacao Dupla Esquerda");
                    return rotacaoDuplaEsquerda();
                }
            }
        }
        this.calcularBalanceamento(); 
        if (this.esq != null) this.esq = this.esq.verificaBalanceamento();
        if (this.dir != null) this.dir = this.dir.verificaBalanceamento();
        return this;
    }
	
	public ArvoreAVL rotacaoSimplesDireita() {
		ArvoreAVL filhoDir;
		ArvoreAVL filhoDoFilho = null;
		
		filhoDir = this.getDir(); 
		if(this.dir != null) {
			if(this.dir.getEsq() != null) {
				
				filhoDoFilho = filhoDir.getEsq();
			}
		}
		filhoDir.setEsq(this); 
		this.setDir(filhoDoFilho); 
		return filhoDir;
	}
	
	public ArvoreAVL rotacaoSimplesEsquerda() {
		ArvoreAVL filhoEsq;
		ArvoreAVL filhoDoFilho = null;
        filhoEsq = this.getEsq();
        if (this.esq != null){
            if (this.esq.getDir()!= null){
                filhoDoFilho = filhoEsq.getDir();
            }
        }
        filhoEsq.setDir(this);
        this.setEsq(filhoDoFilho);
        return filhoEsq;
    }
	
	
	public ArvoreAVL rotacaoDuplaDireita() {
		ArvoreAVL arvore = this;
		ArvoreAVL filhoDir = this.getDir();
		ArvoreAVL filhoDoFilho = filhoDir.getEsq();
		ArvoreAVL noInsreido = filhoDoFilho.getDir();
		
		filhoDir.setEsq(noInsreido); 
		filhoDoFilho.setDir(filhoDir);
		this.setDir(filhoDoFilho);
		 
		
		ArvoreAVL novoFilhoDireita = this.getDir(); 
		arvore.setDir(null);
		novoFilhoDireita.setEsq(arvore);
		return novoFilhoDireita;
	}
	
	public ArvoreAVL rotacaoDuplaEsquerda() {
		ArvoreAVL arvore       = this;
		ArvoreAVL filhoDir     = this.getEsq();
		ArvoreAVL filhoDoFilho = filhoDir.getDir();
		ArvoreAVL noInserido   = filhoDoFilho.getEsq();
      
        filhoDir.setDir(noInserido);
        filhoDoFilho.setEsq(filhoDir);
        this.setEsq(filhoDoFilho);
       
        ArvoreAVL novoFilhoDireita = this.getEsq();
        arvore.setEsq(null);
        novoFilhoDireita.setDir(arvore);
        return novoFilhoDireita;
	}
	
	public ArvoreAVL remover(Elemento elem) {
		if(this.ele.getValor() == elem.getValor()) {
			if(this.dir == null && this.esq == null) {
				return null;
			}
			else {
				if(this.dir == null && this.esq != null) {
					return this.esq;
				}
				else if(this.dir != null && this.esq == null){
					return this.dir;
				}
				else {
					ArvoreAVL aux = this.esq;
					while(aux.dir != null) {
						aux = aux.dir;
					}
					this.ele = aux.getEle();
					
					aux.setEle(elem);
					this.esq = esq.remover(elem);
				}
			}
		}
		else if(elem.getValor() < this.ele.getValor()) {
			this.esq = this.esq.remover(elem);
		}
		else if(elem.getValor() > this.ele.getValor()) {
			this.dir = this.dir.remover(elem);
		}
		return this;
	}
	
	public boolean isEmpty() {
		return (this.ele == null);
	}
	
	public void imprimirPreOrdem() {
		if(!isEmpty()) {
			System.out.print(this.ele.getValor()+" ");
			if(this.esq != null) {
				this.esq.imprimirPreOrdem();
			}
			if(this.dir != null) {
				this.dir.imprimirPreOrdem();
			}
		}
	}
	
	public void imprimirInOrdem() {
		if(!isEmpty()) {
			if(this.esq != null) {
				this.esq.imprimirInOrdem();
			}
			System.out.print(this.ele.getValor()+" ");
			if(this.dir != null) {
				this.dir.imprimirInOrdem();
			}
		}		
	}
	
	public void imprimirPosOrdem() {
		if(!isEmpty()) {
			if(this.esq != null) {
				this.esq.imprimirPosOrdem();
			}
			if(this.dir != null) {
				this.dir.imprimirPosOrdem();
			}
			System.out.print(this.ele.getValor()+" ");
		}
	}
	public void imprimirNumero() {
		//calcularAltura()
		System.out.print(this.ele.getValor()+ " ");
		
	}
	
	public ArvoreAVL inserir(Elemento novo) {
		if(isEmpty()) {
			this.ele = novo;
		}else {
			ArvoreAVL novaArvore = new ArvoreAVL(novo);
			if(novo.getValor() < this.ele.getValor()) {
				if(this.esq == null) {
					this.esq = novaArvore;
					
				}
				else {
					this.esq = this.esq.inserir(novo); 
				}
			}
			else if(novo.getValor() > this.ele.getValor()){
				 if(this.dir == null) {
					 this.dir = novaArvore;
					
				 }
				 else {
					 this.dir = this.dir.inserir(novo); 
				 }
			}
		}
		return this;
	}

	public boolean busca(int valor) {
		if (isEmpty()){
            return false;
        }
        if (this.ele.getValor() == valor){
            return true;
        }
        else{
            if (valor < this.ele.getValor() ){
                if (this.esq == null){
                    return false;
                }
                else{
                    return this.esq.busca(valor); 
                }
            }
            else if (valor > this.ele.getValor()){
                if (this.dir == null){
                    return false;
                }
                else{
                    return this.dir.busca(valor);
                }
            }
            return false;
        }
    }
	
	
	public Elemento getEle() {
		return ele;
	}

	public void setEle(Elemento ele) {
		this.ele = ele;
	}

	public ArvoreAVL getDir() {
		return dir;
	}

	public void setDir(ArvoreAVL dir) {
		this.dir = dir;
	}

	public ArvoreAVL getEsq() {
		return esq;
	}

	public void setEsq(ArvoreAVL esq) {
		this.esq = esq;
	}
	
	public int getBal() {
		return bal;
	}

	public void setBal(int bal) {
		this.bal = bal;
	}

	
	public String printArvore(int level) {
		String str = this.toString()+"\n";
		for (int i=0; i<level; i++){
            str += "\t";
		}
	    if (this.esq != null){
	        str += "+-ESQ: "+this.esq.printArvore(level + 1);
	    }
	    else{
	        str += "+-ESQ: NULL";
	    }
	    str += "\n";
	    for (int i=0; i<level; i++){
	             str += "\t";
	    }
	    if (this.dir != null){
	        
	        str += "+-DIR: "+this.dir.printArvore(level + 1); 
	    }
	    else{
	        str += "+-DIR: NULL";
	    }
	    str += "\n";
	    return str;
		
	}

	@Override
	public String toString() { 
		return "["+this.ele.getValor()+"] ";
	}
	
	
	
	
	
	
}

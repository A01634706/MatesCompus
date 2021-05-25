
/*
Data Structures and algorithims in java de RobertLafore


*/
package proyectoa01634706;
/**
 *
 * @author Amilc
 */
import java.util.Stack;

public class ProyectoA01634706 {
	private String valorfijo;
            
	
	public ProyectoA01634706(String valorfijo) {
		this.valorfijo = valorfijo;
	}
	
	public void setValorfijo(String valorfijo) {
		this.valorfijo = valorfijo;
	}
	
	public double resolver() {
		Stack<String> fuente = new Stack<String>();
		String valorFutura = valorFuturo();
		String[] dist = valorFutura.split(" ");
		double pr1,pr2,ans;
		for(String t:dist) {
			switch(t) {
				default:
					fuente.push(t);
					break;
				case "+":
					pr2 = Double.parseDouble(fuente.pop());
					pr1 = Double.parseDouble(fuente.pop());
					fuente.push(String.valueOf(pr1+pr2));
					break;
				case "-":
					pr2 = Double.parseDouble(fuente.pop());
					pr1 = Double.parseDouble(fuente.pop());
					fuente.push(String.valueOf(pr1-pr2));
					break;
				case "*":
					pr2 = Double.parseDouble(fuente.pop());
					pr1 = Double.parseDouble(fuente.pop());
					fuente.push(String.valueOf(pr1*pr2));
					break;
			}
		}
		ans = Double.parseDouble(fuente.pop());
		return ans;
	}
	
	public String valorFuturo() {
		//Regresa un String con la representacion de expresionInfija pero en postfijo.
		//El formato del string de retorno debera ser separando por un espacio en blanco cada elemento de la expreison
		Stack<String> fuente = new Stack<String>();
		String[] dist = this.valorfijo.split(" ");
		String pop,
			   valorFutura = "";
		for(String t:dist) {
			boolean flag = false;
			switch(t) {
				default:
					valorFutura += t + " ";
					break;
				case "(":
					fuente.push(t);
					break;
				case ")":
					while(fuente.isEmpty()==false && flag != true) {
						pop = fuente.pop();
						//System.out.println(pop);
						if(pop.equals("(")==false){
							valorFutura += pop + " ";
						}else {
							flag = true;
						}
					}break;
				case "+":
				case "-":
				case "*":
					if(fuente.isEmpty()==true) {
						fuente.push(t);
						break;
					}else {
						while(fuente.isEmpty()==false && flag != true) {
							pop = fuente.pop();
							//System.out.println(pop);
							switch(pop) {
								case "(":
									fuente.push(pop);
									flag = true;
									break;
								case "+":
								case "-":
								case "*":
									if(opPrecede(pop,t)==true) {
										fuente.push(pop);
										//System.out.println(pop);
										flag = true;
									}else {
										valorFutura += pop + " ";
									}
									break;
							}
						}
						fuente.push(t);
					}
			}
		}
		while(fuente.isEmpty()==false) {
			pop = fuente.pop();
			//System.out.println(pop);
			valorFutura += pop + " ";
		}
		return valorFutura;
	}
	
	public boolean opPrecede(String op1, String op2) {//Funcion que revisa la precedencia de operadores
		if(op1.equals("+") == true && op2.equals("*") == true) {
			return true;
		}else if(op1.equals("+") == true && op2.equals("/") == true){
			return true;
		}else if(op1.equals("-") == true && op2.equals("*") == true){
			return true;
		}else if(op1.equals("-") == true && op2.equals("/") == true){
			return true;
		}else {
			return false;
		}
	}
	public boolean comprueba(String str) {//Checa que el string obtenido sea un numero de tipo double
		try {
			Double.parseDouble(str);
			return true;
		}catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		ProyectoA01634706 prueba = new ProyectoA01634706("8 * ( 2 + 3 )  - 3");
		//System.out.println(prueba.resolver());
		System.out.println(prueba.valorFuturo());
		
	}
}

package delimitadores;

public class AppPostFija {
    public static void menu(){
    	System.out.println("******************************************************************");
        System.out.println("\t\tAPLICACIONES CON USO DE PILAS");
        System.out.println("******************************************************************");
        System.out.println(" 2) Convertir Expresion Infija  a Postfija");
        System.out.println("------------------------------------------------------------------");
        String expresionInfija = "a+b*c";//expresión valida
		String expresionPostfijo;
		double a = 3.0, b = 2.0;
		
		if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			//InfijaPostFija.calcularOperacion(a, b, '+');
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión prefija: " + expresionPostfijo);
	       
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else{
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Infija no válida");
			
		}	
		
        System.out.println("--------------------------------------------");
        expresionInfija = "(a + b) * c"; //expresión valida
        if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión postfijo: " + expresionPostfijo);
	        
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else{
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Postfija no válida");
		}	
        
        System.out.println("--------------------------------------------");
        expresionInfija = "(a + (e+z)+b) * c"; //expresión valida
        if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión postfijo: " + expresionPostfijo);
	        
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else{
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Infija no válida");
		}
        
        System.out.println("--------------------------------------------");
        expresionInfija = "(a + (e+z)/b) * c"; //expresión valida
        if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión postfijo: " + expresionPostfijo);
	        
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else{
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Infija no válida");
		}	
        
        System.out.println("--------------------------------------------");
        expresionInfija = "(X + Z) * W / T ^ Y - V";//expresión valida
        if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión postfijo: " + expresionPostfijo);
	        
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else{
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Infija no válida");
		}	
        
        System.out.println("--------------------------------------------");
        expresionInfija = "(X + Z) * W / [T ^ Y - V"; //expresión inválida
        if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión postfijo: " + expresionPostfijo);
	        
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else {
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Infija no válida");
		}	
        
        System.out.println("--------------------------------------------");
        expresionInfija = "(X + Z)) * W / [T ^ Y - V"; //expresión inválida
        if (new Delimitadores().evaluacionDelimitadores(expresionInfija)) {
			expresionPostfijo = InfijaPostFija.convertirAPostfijo(expresionInfija);
			System.out.println("Expresión infija: " + expresionInfija);
	        System.out.println("Expresión postfijo: " + expresionPostfijo);
	        
	        System.out.println("Resultado = " + InfijaPostFija.evaluarPostfija(expresionPostfijo));
		}
		else {
			System.out.println("Expresión infija: " + expresionInfija);
			System.out.println("Expresión Infija no válida");
		}
        System.out.println("__________________________________________________________________________________________\n");
    }
}
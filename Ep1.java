import java.util.Scanner;

public class Ep1 {
	
	static int bits;
	
	// Conversoes
	public static short[] longToArray(long a) {
		
		long b = a;
		short[] result = new short[(int) Ep1.bits];
		short i = (short) (Ep1.bits - 1);
		
		while (b > 0) {
			result[i] = (short) (b % 10);
			b /= 10;
			i--;
		}
		
		return result;
	}
	
	public static short[] PositiveToNegative(short[] array, int tamanhoDeA) {

        short[] returnArray = new short[Ep1.bits];
        short[] b = new short[Ep1.bits];
        b[Ep1.bits - 1] = 1;

		// Inverte a partir do tamanho de a
		for (int i = tamanhoDeA; i < Ep1.bits; i++) {
			if (array[i] == 1) {
				returnArray[i] = 0;
			} else {
				returnArray[i] = 1;
            }
        }
        
        // Soma 1
        return longToArray(Long.parseLong(somaBinario(returnArray, b), 10));
    }
    
    public static String arrayToString(short[] x) {

        StringBuffer y = new StringBuffer();

        for (short z : x) {
            y.append(z);
        }

        return y.toString();
    }

    // Baseado no http://www.guj.com.br/t/transforma-decimal-em-binario/47061
    // public static long DecimalParaBinario(long decimal) {

    //     if (decimal == 0) {
    //         return 0;
    //     }

    //     long d = decimal;
    //     StringBuffer binario = new StringBuffer(); // guarda os dados
    //     while (d > 0) {
    //         long b = d % 2;
    //         binario.append(b);
    //         d = d >> 1; // é a divisão que você deseja
    //     }

    //     return Long.parseLong(binario.reverse().toString(), 10); // inverte a ordem e imprime
    // }

    // public static long BinarioParaDecimal(long binario) {

    //     String myString = new StringBuilder(Long.toString(binario)).reverse().toString();
    //     long result = 0;

    //     for (int i = 0; i < myString.length(); i++) {
    //         result += Character.getNumericValue(myString.charAt(i)) * Math.pow(2, i);
    //     }

    //     return result;
    // }

    public static String somaBinario(short[] a, short[] b) {
    	
    	short somaProx = 0;
    	short[] resposta = new short[Ep1.bits];
    	
    	for (int i = Ep1.bits - 1; i >= 0; i--) {
    		if (a[i] + b[i] == 0) {
				 resposta[i] = (short) (0 + somaProx);
				 somaProx = 0;
			 }
			 else if (a[i] + b[i] == 1) {
				 if ((short) (1 + somaProx) != 2) {
					 resposta[i] = 1;
					 somaProx = 0;
				 } else {
					 resposta[i] = 1;
					 somaProx = 1;
				 }
			 }
			 else if (a[i] + b[i] == 2) {
				 resposta[i] = somaProx;
				 somaProx = 1;
			 }
		 }

        String c = arrayToString(resposta);
        
        // Overflow verificator
        if (somaProx == 1) {
            System.out.print("Overflow! O primeiro dígito é do overflow: ");
            c = "1" + c;
    	}
                
        return c;
    }
    
    public static String subtraiBinario(short[] a, short[] b) {
        
        int i = 0;

        while (a[i] == 0 ) {
            i++;
        }

        return somaBinario(a, PositiveToNegative(b, i));
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        System.out.print("Primeiro escolha o número de bits: ");
        Ep1.bits = scan.nextInt();
        
        System.out.println("\n1. Calculadora  de binários inteiros");
        System.out.println("2. Calculadora  de binários em decimal");
        System.out.print("Escolha uma das opções acima: ");

        short tipoCalculadora = scan.nextShort();

        System.out.println("");

        // Calculadora  de binários inteiros
        if (tipoCalculadora == 1) {
            System.out.print("Digite um valor em binário para 'A': ");
            short[] a = longToArray(scan.nextLong());
            //long a = BinarioParaDecimal(scan.nextLong());

            System.out.print("Digite um valor em binário para 'B': ");
            short[] b = longToArray(scan.nextLong());
            //long b = BinarioParaDecimal(scan.nextLong());
            
            System.out.println("\nA + B = " + somaBinario(a, b));
            System.out.println("A - B = " + subtraiBinario(a, b));
            /*System.out.println("A * B = " + DecimalParaBinario(a * b));
            System.out.println("A / B = " + DecimalParaBinario(a / b));*/

        // Calculadora  de binários em decimal
        } else {
            System.out.println("");
        }

        scan.close();
    }
}
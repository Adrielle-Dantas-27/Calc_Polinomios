import java.util.Scanner;

public class PolynomialCalculator {
    // Função para somar dois polinômios (coeficientes em ordem decrescente)
    public static int[] addPoly(int[] p1, int[] p2) {
        int m = p1.length;
        int n = p2.length;
        int size = Math.max(m, n);
        int[] sum = new int[size];
        // Calcula offsets para alinhar coeficientes pelo fim
        int offset1 = size - m;
        int offset2 = size - n;
        for (int i = 0; i < size; i++) {
            // Se não existe coeficiente (vetor menor), usa 0
            int a = (i - offset1 >= 0) ? p1[i - offset1] : 0;
            int b = (i - offset2 >= 0) ? p2[i - offset2] : 0;
            sum[i] = a + b;  // soma dos coeficientes
        }
        return sum;
    }

    // Função para multiplicar dois polinômios (coeficientes em ordem decrescente)
    public static int[] multiplyPoly(int[] p1, int[] p2) {
        int m = p1.length;
        int n = p2.length;
        int[] product = new int[m + n - 1]; // grau máximo = (m-1)+(n-1)
        // Loops aninhados para multiplicar cada termo de p1 por cada termo de p2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                product[i + j] += p1[i] * p2[j];
            }
        }
        return product;
    }

    // Função para formatar um vetor de coeficientes (ordem decrescente) como string polinomial
    public static String formatPoly(int[] poly) {
        StringBuilder sb = new StringBuilder();
        int degree = poly.length - 1;
        boolean first = true;  // indica se já escreveu o primeiro termo

        for (int i = 0; i < poly.length; i++) {
            int coeff = poly[i];
            int exp = degree - i;
            if (coeff == 0) continue; // pula coeficientes zero
            // Define sinal e ajusta coeficiente se negativo (evita “+-”)
            if (!first) {
                if (coeff > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                    coeff = -coeff;
                }
            } else {
                // Primeiro termo: coloca sinal negativo se necessário
                if (coeff < 0) {
                    sb.append("-");
                    coeff = -coeff;
                }
                first = false;
            }
            // Imprime coeficiente (omitindo “1” se grau > 0)
            if (coeff != 1 || exp == 0) {
                sb.append(coeff);
            }
            // Imprime a parte “x^exp” se exp > 0
            if (exp > 0) {
                sb.append("x");
                if (exp > 1) {
                    sb.append("^").append(exp);
                }
            }
        }
        // Se todos os coeficientes foram zero, retorna "0"
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Lê coeficientes do polinômio 1
        System.out.print("Digite os coeficientes do polinômio 1 (ordem decrescente): ");
        String[] in1 = scanner.nextLine().trim().split("\\s+");
        int[] p1 = new int[in1.length];
        for (int i = 0; i < in1.length; i++) {
            p1[i] = Integer.parseInt(in1[i]);
        }
        // Lê coeficientes do polinômio 2
        System.out.print("Digite os coeficientes do polinômio 2 (ordem decrescente): ");
        String[] in2 = scanner.nextLine().trim().split("\\s+");
        int[] p2 = new int[in2.length];
        for (int i = 0; i < in2.length; i++) {
            p2[i] = Integer.parseInt(in2[i]);
        }

        // Calcula soma e produto usando as funções acima
        int[] soma = addPoly(p1, p2);
        int[] produto = multiplyPoly(p1, p2);

        // Exibe polinômios originais e resultados formatados
        System.out.println("Polinômio 1: " + formatPoly(p1));
        System.out.println("Polinômio 2: " + formatPoly(p2));
        System.out.println("Soma: "       + formatPoly(soma));
        System.out.println("Produto: "    + formatPoly(produto));
    }
}

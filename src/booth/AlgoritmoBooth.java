package booth;

public class AlgoritmoBooth extends OperacionesBinarias {

    private static final int ASP_FILAS = 3;
    private static final int BIT_EXTRA = 1;
    private static int BYTE = 4;
    private static final int NUM_VALIDO = 4;
    private int tam;
    private boolean negativos;
    private int[][] tablero;

    /**
     * Este metodo coloca las filas ASP para poder empezar a hacer las
     * operaciones
     *
     * @param bin1 Multiplicando binario
     * @param bin2 Multiplicador binario
     */
    private void colocarASP(String bin1, String bin2) {
        char[] multiplicando = bin1.toCharArray();
        char[] multiplicador = bin2.toCharArray();
        char[] complemento = complementoADos(bin1).toCharArray();

        int a[] = new int[tablero[0].length];
        int s[] = new int[tablero[0].length];
        int p[] = new int[tablero[0].length];

        for (int i = 0; i < multiplicando.length; i++) {
            a[i] = Integer.parseInt("" + multiplicando[i]);
            s[i] = Integer.parseInt("" + complemento[i]);
            p[i + tam] = Integer.parseInt("" + multiplicador[i]);
        }

//        for (int i = 0; i < s.length; i++) {
//            System.out.print(s[i]);
//        }
        //System.out.println(" ya largo: " + s.length);
        //System.out.println("Multiplicador " + bin2);
        //System.out.println("Vector " + Arrays.toString(p));
        //System.out.println(tablero[0].length + " tablero");
        //System.out.println(multiplicando.length +  "multiplicando");
        for (int i = 0; i < tablero[0].length; i++) {
            tablero[0][i] = a[i];
        }

        for (int i = 0; i < tablero[0].length; i++) {
            tablero[1][i] = s[i];
            //System.out.println("esto" + tablero[1][i]);
        }

        for (int i = multiplicador.length; i < tablero[0].length; i++) {
            tablero[2][i] = p[i];
        }

        for (int i = multiplicando.length; i < tablero[0].length; i++) {
            //tablero [2] [i] = multiplicador [i];
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                //tablero[i][j] = 1;
            }
        }
    }

    /**
     * Este metodo imprime en pantalla el array o tablero del algoritmo
     */
    private void imprimirTablero() {
        int penultimo = tablero[0].length - 2;
        for (int i = 0; i < tablero.length; i++) {

            switch (i) {
                case 0:
                    System.out.print(" A : ");
                    break;
                case 1:
                    System.out.print(" S : ");
                    break;
                case 2:
                    System.out.print(" P : ");
                    break;
                default:
                    System.out.print(i - 2 + "P*: ");
            }

            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + "");
                if ((j + 1) % 4 == 0) {
                    System.out.print("  ");
                }
                if (j == tablero[0].length - 2) {
                    //System.out.print(" ");
                }
            }
            if (i > 1) {

                if ((i + 1) >= tablero.length) {
                    System.out.print("\tFinal");
                    System.out.println("");
                    continue;
                }

                System.out.print("\tUltimos valores: " + tablero[i][penultimo] + " y "
                        + tablero[i][penultimo + 1]);

                if (tablero[i][penultimo] == tablero[i][penultimo + 1]) {
                    System.out.print(" se hace recorrido");
                } else if (tablero[i][penultimo] == 1) {
                    System.out.print(" P=P+S y se hace recorrido");
                } else {
                    System.out.print(" P=P+A y se hace recorrido");
                }
            }
            System.out.println("");
        }
        String r = "";

        for (int i = 0; i < tablero[0].length - 1; i++) {
            r += tablero[tablero.length - 1][i];
        }

        if (!negativos) {
            System.out.println("El resultado es: " + r);
            System.out.println("En decimal es: " + binarioADec(r));
        } else {
            System.out.println("El resultado es: " + r);
            System.out.println("En decimal es: -" + binarioADec(complementoADos(r)));
            System.out.println("ATENCION Este numero lo mas probable es que este equivocado se "
                    + "recomienda hacer la conversion manualmmente");
        }
    }

    /**
     *
     * @param bin El numero binario que se desea introducir
     * @param fila La fila en la cual de desea introducir
     */
    private void modificarFila(String bin, int fila) {
        for (int i = 0; i < bin.length(); i++) {
            int a = Integer.parseInt("" + bin.charAt(i));
            tablero[fila][i] = a;
        }
    }

    /**
     *
     * @param bin1 EL primer numero en decimal Multiplicando
     * @param bin2 EL segundo numero en decimal Multiplicador
     * @param negativos Se permiten numeros negativos NO LO RECOMIENDO
     * @return Resultado de esa multiplicaion con el signo indicado
     */
    public String multiplicaionBinaria(int bin1, int bin2, boolean negativos) {
        return multiplicaionBinaria(decimalABinario(bin1), decimalABinario(bin2), negativos);
    }

    public String multiplicaionBinaria(int bin1, int bin2) {
        return multiplicaionBinaria(decimalABinario(bin1), decimalABinario(bin2), false);
    }

    /**
     *
     * @param bin1 EL primer numero binario Multiplicando
     * @param bin2 EL segundo numero binario Multiplicador
     * @param negativos Se permiten numeros negativos NO LO RECOMIENDO
     * @return Resultado de esa multiplicaion con el signo indicado
     */
    public String multiplicaionBinaria(String bin1, String bin2, boolean negativos) {
        String r = "";
        this.negativos = negativos;
        int[][] t = obtenerTablero(bin1, bin2, negativos);

        for (int i = 0; i < tablero[0].length - 1; i++) {
            r += tablero[tablero.length - 1][i];
        }

        return r;
    }

    public String multiplicaionBinaria(String bin1, String bin2) {
        return multiplicaionBinaria(bin1, bin2, false);
    }

    /**
     * Este metodo devuelve una matriz o tablero con los valores que se usaron
     * para resolver este algoritmo
     *
     * @param bin1 El multiplicando binario
     * @param bin2 El multiplicador binario
     * @param negativos Se necesita operar un numero negativo?
     * @return Una matriz con los resultados
     */
    public int[][] obtenerTablero(String bin1, String bin2, boolean negativos) {
        //System.out.println(bin1.length());

        //System.out.println(bin1.length());
        //COMENTARIO IMPORTANTE PARA QUE FUNCIONE
        //Si el valor que se ingreso comienza con 1 se tienen que agragar mas bits para quefuncione
        //por eso es >= y no simplemente > lo mismo para <=
        //Pero esto quita funcionalidad a numeros "Negativos"
        //Si se quieren numeros negativos cambiar a solo < o >
        //Implemente esta funcion pero hay demasiados bugs
        //Si no escogio la opcion con soporte a negativos
        //y el primer bit de los 2 binarios es 0 esta opcion es verdadera
        //y NO se agregan columnas extra a la tabla
        if ((!negativos) && !(bin1.charAt(0) == '0' && bin2.charAt(0) == '0')) {

            if (bin1.length() >= BYTE || bin2.length() >= BYTE) {

                if (bin1.length() > bin2.length()) {

                    while (BYTE <= bin1.length() || BYTE % NUM_VALIDO != 0) {
                        BYTE++;
                        //System.out.println("aqui");
                    }
                } else {
                    while (BYTE <= bin2.length() || BYTE % NUM_VALIDO != 0) {
                        BYTE++;
                    }
                }
            }
        } else {
            if (bin1.length() > BYTE || bin2.length() > BYTE) {

                if (bin1.length() > bin2.length()) {

                    while (BYTE < bin1.length() || BYTE % NUM_VALIDO != 0) {
                        BYTE++;
                        //System.out.println("aqui");
                    }
                } else {
                    while (BYTE < bin2.length() || BYTE % NUM_VALIDO != 0) {
                        BYTE++;
                    }
                }
            }
        }
        System.out.println("El tamaño para el tablero es de: " + BYTE);
        System.out.println();
        if (bin1.length() <= BYTE) {
            while (bin1.length() < BYTE) {
                String tmp = bin1;
                bin1 = "0" + bin1;
            }
        } else {
            System.out.println("Error 1");
            return new int[2][2];
        }

        if (bin2.length() <= BYTE) {
            while (bin2.length() < BYTE) {
                String tmp = bin2;
                bin2 = "0" + bin2;
            }
        } else {
            System.out.println("Error 2");
            return new int[2][2];
        }
        //TODO Verificar si desde aqui esto es necesario
        if (bin1.length() > bin2.length()) {
            this.tam = bin1.length();
        } else {
            this.tam = bin2.length();
        }
        String[] a = nivelarBinario(bin1, bin2);

        bin1 = a[0];
        bin2 = a[1];
        //TODO Hasta aqui

        System.out.println("El multiplicando binario es: " + bin1);
        System.out.println("En decimal su valor es de: " + binarioADec(bin1));
        System.out.println("El multiplicador binario es: " + bin2);
        System.out.println("En decimal su valor es de: " + binarioADec(bin2));

        System.out.println("El resultado deberia ser de: " + binarioADec(bin1) * binarioADec(bin2));
        System.out.println("El complemento a dos del multiplicando es: " + complementoADos(bin1));
        System.out.println();

        //System.out.println("ESTO 1: " + bin1);
        //tablero = new int[tam + ASP_FILAS][(tam * 2) + BIT_EXTRA];
        tablero = new int[tam + ASP_FILAS][(tam * 2) + BIT_EXTRA];

        //Inicializar todos los valores en cero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = 0;
            }
        }
        //SE colocan las filas ASP
        colocarASP(bin1, bin2);

        //Se empieza a comprobar para hacer las operaciones la fila actual en 2 es porque se empieza en P
        int filaActual = 2;

        for (int i = filaActual; i < tam + 2; i++) {
            String actual = "";
            for (int j = 0; j < tablero[0].length; j++) {
                actual += tablero[i][j];
            }
            //Si los 2 ultimos valores del tablero son iguales se hace un recorrido a la derecha

            if (tablero[i][tablero[i].length - 2] == tablero[i][tablero[i].length - 1]) {
                //TODO: acabar esto

                //System.out.println("Valores " + tablero[i][tablero[i].length - 2] + " " + tablero[i][tablero[i].length - 1]);
                actual = corrimientoDerecha(actual, 1);
                modificarFila(actual, i + 1);
                //System.out.println("El vector actual: " + actual);
            } else if (tablero[i][tablero[i].length - 2] == 1) {
                //System.out.println("Valores " + tablero[i][tablero[i].length - 2] + " " + tablero[i][tablero[i].length - 1]);
                String b = "";
                for (int j = 0; j < tablero[0].length; j++) {
                    b += tablero[1][j];
                }

                b = sumaBinaria(actual, b, false);
                actual = corrimientoDerecha(b, 1);
                modificarFila(actual, i + 1);
            } else if (tablero[i][tablero[i].length - 2] == 0) {
                //System.out.println("alores " + tablero[i][tablero[i].length - 2] + " " + tablero[i][tablero[i].length - 1]);
                String b = "";
                for (int j = 0; j < tablero[0].length; j++) {
                    b += tablero[0][j];
                }

                //TODO hacer el recorrido a la derecha
                b = sumaBinaria(actual, b, false);
                actual = corrimientoDerecha(b, 1);
                modificarFila(actual, i + 1);
            }

            //System.out.println(i);
            //System.out.println("actual " + actual);
        }

        imprimirTablero();
        return tablero;
    }

}

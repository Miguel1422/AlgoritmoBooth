package booth;

import java.util.ArrayList;

public class OperacionesBinarias {

    private static final int TAM_BYTE = 8;

    /**
     *
     * @param binario El numero binario
     * @return El decimal equivalente a ese binario
     */
    public int binarioADec(String binario) {
        char[] arrayChar = binario.toCharArray();
        int resultado = 0;
        int a = 0;
        for (int i = binario.length() - 1; i >= 0; i--) {
            if (arrayChar[i] == '1') {
                resultado += Math.pow(2, a);
            }
            a++;
        }
        return resultado;
    }

    /**
     *
     * @param bin El numero binario
     * @return El complemento a dos de ese numero binario en los bits en los que
     * se ingreso en 4 8 12 16 20 etc bits dependiendo la cantidad que se
     * ingreso
     */
    public String complementoADos(String bin) {

        String complemento = "";
        int tam = TAM_BYTE / 2;
        int tamIn = TAM_BYTE / 2;
        int tamBin = bin.length();
        if (tamBin > tamIn) {
            //System.out.println("Tamano biio " + tamBin);
            while (tamIn < tamBin || tamIn % tam != 0) {
                tamIn++;
            }
        }

        //System.out.println(tamIn);
        int diferencia = tamIn - tamBin;
        String aux = "";
        //System.out.println("diferencia: " + diferencia);

        for (int i = 0; i < diferencia; i++) {
            aux += "0";
            //System.out.println("Repeticiones " + i);
        }

        bin = aux + bin;

//        System.out.println("Auxiliar: " + aux);
//        System.out.println("Complemento: " + complemento);
//        System.out.println("Binario: " + bin);
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                complemento += '0';
            } else {
                complemento += '1';
            }
        }
        //System.out.println("Al reves");
        complemento = sumaBinaria(complemento, "1", false);
        //System.out.println("");
        return complemento;
    }

    /**
     * Convierte de decimal a binario
     *
     * @param a el Numero a convertir
     * @return Un String en binario del numero ingresado en n bytes necesarios
     */
    public String decimalABinario(int a) {
        return decimalABinario(a, true);
    }

    /**
     *
     * @param a el Numero a convertir
     * @param regresarEnByte Se quiere que se regrese en Bytes llenando con 0
     * los espacios faltantes para completar el byte
     * @return El numero binario
     */
    public String decimalABinario(int a, boolean regresarEnByte) {
        String resultado = "";
        int aux = a;
        ArrayList<Integer> l = new ArrayList<>();
        while (aux != 0) {
            if (aux % 2 == 1) {
                l.add(1);
            } else {
                l.add(0);
            }
            aux /= 2;
        }

        if (regresarEnByte) {
            while (l.size() % TAM_BYTE != 0) {
                l.add(0);
            }
        }
        for (int i = l.size() - 1; i >= 0; i--) {
            resultado += l.get(i);
        }
        return resultado;
    }

    /**
     *
     * @param bin El numero binario
     * @param desplazamientos El numero de desplazamientos a la derecha deseados
     * @param conservarSigno Si se desea conservar el signo de la operacion
     * @return El numero binario con el desplazamiento deseado
     */
    public String corrimientoDerecha(String bin, int desplazamientos, boolean conservarSigno) {
        String result = "";
        char[] r = bin.toCharArray();
        int inicio = r.length - 1;
        char signo = r[0];
        //System.out.println("El valor inicial del binario es: " + bin);

        for (int i = inicio; i >= 0; i--) {
            if (i - desplazamientos < 0) {
                for (int j = i; j >= 0; j--) {
                    r[j] = '0';
                }
                break;
            }
            r[i] = r[i - desplazamientos];
        }

        if (conservarSigno) {
            r[0] = signo;
        }

        for (int i = 0; i < r.length; i++) {
            //System.out.print(r[i] + " ");
            result += r[i];
        }
        //System.out.println("");
        return result;
    }

    /**
     *
     * @param bin El numero binario
     * @param desplazamientos El numero de desplazamientos a la derecha deseados
     * @return El numero binario con el desplazamiento deseado conservando el
     * signo
     */
    public String corrimientoDerecha(String bin, int desplazamientos) {
        return corrimientoDerecha(bin, desplazamientos, true);
    }

    //TODO hacer un metodo corrimiento a la izquierda solo por tenerlo
    /**
     *
     * @param h Los valores que se desean nivelar
     * @return Un array con los numeros ya nivelados con el mismo numerod e bits
     */
    public String[] nivelarBinario(String... h) {

        for (int i = 0; i < h.length - 1; i++) {
            for (int j = i + 1; j < h.length; j++) {
                //System.out.println("Comparando: " + h[i] + " con: " + h[j]);

                if (h[i].length() < h[j].length()) {
                    while (h[i].length() < h[j].length()) {
                        String tmp = h[i];
                        h[i] = "0" + tmp;
                        //System.out.println("Agregando 0 a: " + h[i]);
                    }
                } else {
                    while (h[j].length() < h[i].length()) {
                        String tmp = h[j];
                        h[j] = "0" + tmp;
                        //System.out.println("Agregando 0 a: " + h[i]);
                    }
                }

            }
        }
        for (int i = 0; i < h.length; i++) {
            //System.out.println(h[i]);
        }
        return h;
    }

    /**
     *
     * @param bin1 El primer valor binario
     * @param bin1Negativo Indica si el primer valor es negativo
     * @param bin2 El segundo valor binario
     * @param bin2Negativo Indica si el segundo valor es negativo
     * @return El resultado de la resta indicada
     */
    public String restaBinaria(String bin1, boolean bin1Negativo, String bin2, boolean bin2Negativo) {
        String result = "";

        if (bin1Negativo) {
            bin1 = complementoADos(bin1);
        }
        if (bin2Negativo) {
            bin2 = complementoADos(bin2);
        }
        result = sumaBinaria(bin1, bin2, false);

        return result;
    }

    /**
     *
     * @param bin1 El primer valor binario
     * @param bin2 El valor binario a restar
     * @return El resultado de la resta
     */
    public String restaBinaria(String bin1, String bin2) {
        return restaBinaria(bin1, false, bin2, true);
    }

    /**
     *
     * @param b1 El binario numero 1
     * @param b2 El binario numero 2
     * @param keepOverflow Se desea que se mantenga el digito a pesar de que se
     * salga de los bits ingresados
     * @return Los numeros bnarios sumados
     */
    public String sumaBinaria(String b1, String b2, boolean keepOverflow) {
        String resultado = "";
        char[] bin1;
        char[] bin2;
        char[] r;
        boolean acarreo = false;
        //System.out.println("Valor inicial del primer valor: " + b1 + " su tamaño es de: " + b1.length());
        //System.out.println("Valor inicial del segundo valor: " + b2 + " su tamaño es de: " + b2.length());
        //Se hacen del mismo tamano las 2 entradas si una es mayor
        String[] nivelados = nivelarBinario(b1, b2);
        b1 = nivelados[0];
        b2 = nivelados[1];

        //System.out.println("Valor despues de nivelar del primer valor: " + b1 + " su tamaño es de: " + b1.length());
        //System.out.println("Valor despues de nivelar del segundo valor: " + b2 + " su tamaño es de: " + b2.length());
        //System.out.println("Uno: " + b1 + "\nDos: " + b2);
        bin1 = b1.toCharArray();
        bin2 = b2.toCharArray();
        if (keepOverflow) {
            r = new char[bin1.length + 1]; //+1 del bit extra por si hay acarreo al final
        } else {
            r = new char[bin1.length];
        }
        for (int i = bin1.length - 1; i >= 0; i--) {
            //Si son iguales
            if (bin1[i] == bin2[i]) {
                //Si no hay acarreo y son iguales
                if (!acarreo) {
                    //Si no hay acarreo y son igualesro a cero
                    if (bin1[i] == '0') {
                        r[i] = '0';
                        acarreo = false;
                    } //Si no hay acarreo y son iguales a uno
                    else {
                        r[i] = '0';
                        acarreo = true;
                    }
                } //si son iguales y hay acarreo
                else {
                    //si son iguales y hay acarreo y son cero
                    if (bin1[i] == '0') {
                        r[i] = '1';
                        acarreo = false;
                    } //si son iguales y hay acarreo y son uno
                    else {
                        r[i] = '1';
                        acarreo = true;
                    }
                }
            }//Si son iguales
            else {
                //Si son iguales y NO hay acarreo
                if (!acarreo) {
                    r[i] = '1';
                    acarreo = false;
                } //Si son iguales y SI hay acarreo
                else {
                    r[i] = '0';
                    acarreo = true;
                }
            }
            if (i == 0) {

            }
            //System.out.println("valor " + i + " es: " + r[i]);
        }
        //si al ultimo de tódo quedo un acarreo y se quiere guardar el overflow
        if (acarreo && keepOverflow) {
            System.out.println("Hay un acarreo al finale");

            for (int i = r.length - 1; i > 0; i--) {
                r[i] = r[i - 1];
            }
            r[0] = '1';
        }

        //System.out.print("Valor del resultado: ");
        for (int i = 0; i < r.length; i++) {
            //System.out.print(r[i]);
            resultado += r[i];
        }
        //System.out.println(resultado);
        return resultado;
    }

}

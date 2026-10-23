package br.com.dourado.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController
{
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        String[] texts = {numberOne, numberTwo};
        throwExceptionIfNotIsNumeric(texts);
        
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping("/subtracao/{numberOne}/{numberTwo}")
    public Double subtracao(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        String[] texts = {numberOne, numberTwo};
        throwExceptionIfNotIsNumeric(texts);

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping("/multiplicacao/{numberOne}/{numberTwo}")
    public Double multiplicacao(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        String[] texts = {numberOne, numberTwo};
        throwExceptionIfNotIsNumeric(texts);

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping("/divisao/{numberOne}/{numberTwo}")
    public Double divisao(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        String[] texts = {numberOne, numberTwo};
        throwExceptionIfNotIsNumeric(texts);

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping("/media/{numberOne}/{numberTwo}")
    public Double media(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    )
    {
        String[] texts = {numberOne, numberTwo};
        throwExceptionIfNotIsNumeric(texts);

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping("/raiz-quadrada/{number}")
    public Double raizQuadrada(
        @PathVariable("number") String number
    )
    {
        String[] texts = {number};
        throwExceptionIfNotIsNumeric(texts);

        return calcularRaizQuadrada(convertToDouble(number), 2);
    }

    public static double calcularRaizQuadrada(double numero, int casasDecimais)
    {
        if (numero < 0 || !Double.isFinite(numero)) {
            throw new IllegalArgumentException("Número inválido");
        }

        if (casasDecimais < 0 || casasDecimais > 10) {
            throw new IllegalArgumentException("Precisão inválida");
        }

        String[] partes = Double.toString(numero).split("\\.");
        String inteiros = partes[0];
        String decimais = partes[1];

        if (inteiros.length() % 2 != 0) {
            inteiros = "0" + inteiros;
        }

        if (decimais.length() % 2 != 0) {
            decimais += "0";
        }

        String algarismos = inteiros + decimais;
        int paresInteiros = inteiros.length() / 2;
        int totalPares = paresInteiros + casasDecimais;

        long raiz = 0;
        long resto = 0;

        for (int i = 0; i < totalPares; i++) {
            int par = 0;

            if (i * 2 + 2 <= algarismos.length()) {
                par = Integer.parseInt(
                        algarismos.substring(i * 2, i * 2 + 2)
                );
            }

            resto = resto * 100 + par;

            long base = raiz * 20;

            for (int digito = 9; digito >= 0; digito--) {
                long produto = (base + digito) * digito;

                if (produto <= resto) {
                    resto -= produto;
                    raiz = raiz * 10 + digito;
                    break;
                }
            }
        }

        return raiz / Math.pow(10, casasDecimais);
    }

    private void throwExceptionIfNotIsNumeric(String[] texts)
    {
        for (String text : texts) {
            if (!isNumeric(text))
                throw new UnsupportedOperationException("Please set a numeric value!");
        }
    }

    private Double convertToDouble(String text) throws IllegalArgumentException
    {
        if (text == null || text.isBlank())
            throw new UnsupportedOperationException("Please set a numeric value!");;

        String textFormated = text.replace(",", ".");

        return Double.parseDouble(textFormated);
    }

    private boolean isNumeric(String text)
    {
        if (text == null || text.isBlank())
            return false;

        String textFormated = text.replace(",", ".");

        return textFormated.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

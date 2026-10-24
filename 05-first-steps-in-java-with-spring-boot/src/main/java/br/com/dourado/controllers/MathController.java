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
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping("/subtracao/{numberOne}/{numberTwo}")
    public Double subtracao(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping("/multiplicacao/{numberOne}/{numberTwo}")
    public Double multiplicacao(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping("/divisao/{numberOne}/{numberTwo}")
    public Double divisao(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception
    {
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping("/media/{numberOne}/{numberTwo}")
    public Double media(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    )
    {
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping("/raiz-quadrada/{number}")
    public Double raizQuadrada(
        @PathVariable("number") String number
    )
    {
        return Math.sqrt(convertToDouble(number)) ;
    }

    private Double convertToDouble(String text) throws IllegalArgumentException
    {
        throwExceptionIfNotIsNumeric(text);

        String textFormated = text.replace(",", ".");

        return Double.parseDouble(textFormated);
    }

    private void throwExceptionIfNotIsNumeric(String... texts)
    {
        for (String text : texts) {
            if (!isNumeric(text))
                throw new UnsupportedOperationException("Please set a numeric value!");
        }
    }

    private boolean isNumeric(String text)
    {
        if (text == null || text.isBlank())
            return false;

        String textFormated = text.replace(",", ".");

        return textFormated.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

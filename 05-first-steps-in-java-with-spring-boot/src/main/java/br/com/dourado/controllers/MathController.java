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
        if (! isNumeric(numberOne) || !isNumeric(numberTwo)) 
            throw new UnsupportedOperationException("Please set a numeric value!");
        
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
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

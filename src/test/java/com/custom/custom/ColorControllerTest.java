package com.custom.custom;

import com.custom.controller.ColorController;
import com.custom.model.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class ColorControllerTest {

    private ColorController colorController;

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @Before
    public void setUp(){

        colorController = new ColorController();
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void close() {
        validatorFactory.close();
    }

    @Test
    public void getColorCode_RED() {
        Color color = new Color();
        color.setColorName("RED");
        assertEquals("R100",colorController.getColorCode(color).getCode());
    }

    @Test
    public void getColorCode_GREEN() {
        Color color = new Color();
        color.setColorName("GREEN");
        assertEquals("G200",colorController.getColorCode(color).getCode());
    }

    @Test
    public void getColorCode_BLUE() {
        Color color = new Color();
        color.setColorName("BLUE");
        assertEquals("B300",colorController.getColorCode(color).getCode());
    }

    @Test
    public void getColorCode_color_InvalidColor() {
        Color color = new Color();
        color.setColorName("BLACK");
        Set<ConstraintViolation<Color>> violations = validator.validate(color);
        String errorMessage = violations.stream()
                .map(value -> value.getMessageTemplate()).collect(Collectors.toList()).get(0);

        assertEquals("Invalid color: must be RED, GREEN or BLUE", errorMessage);

    }


}
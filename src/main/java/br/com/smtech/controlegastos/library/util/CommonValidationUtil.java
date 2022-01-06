package br.com.smtech.controlegastos.library.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.com.smtech.controlegastos.library.exception.ValidException;

public abstract class CommonValidationUtil {

    private static final String UM_DEVE_SER_INFORMADO = "Ao menos um%s %s deve ser informad%s.";
    private static final String DEVE_SER_INFORMADO = "%s deve ser informad%s.";
    private static final String EXCEDEU_QUANTIDADE_MAX_CARACTERES = "\"%s\" excedeu a quantidade máxima de caracteres de %s.";
    private static final String DEVE_SER_MAIOR_QUE_ZERO = "%s deve ser maior que zero.";
    private static final String DEVE_SER_MAIOR_OU_IGUAL_ZERO = "%s deve ser maior ou igual zero.";
    private static final String DEVE_SER_MAIOR_OU_IGUAL = "%s deve ser maior ou igual à %s.";
    private static final String DEVE_SER_MENOR_OU_IGUAL = "%s deve ser menor ou igual à %s.";

    private static List<String> validString(String text, String name, char end, List<String> errors, Integer qtdChar,
            TypeValidEnum... typeValidEnums) {
        if (!StringUtils.hasText(text)) {
            if (TypeValidEnum.contains(TypeValidEnum.NOT_NULL, typeValidEnums)) {
                errors.add(String.format(DEVE_SER_INFORMADO, name, end));
            }
        } else if (TypeValidEnum.contains(TypeValidEnum.COUNT, typeValidEnums) && text.length() > qtdChar) {
            errors.add(String.format(EXCEDEU_QUANTIDADE_MAX_CARACTERES, name, qtdChar));
        }
        return errors;
    }

    private static List<String> validNumber(Number number, String name, char end, List<String> errors, Double max,
            Double min, TypeValidEnum... typeValidEnums) {
        if (number == null) {
            if (TypeValidEnum.contains(TypeValidEnum.NOT_NULL, typeValidEnums)) {
                errors.add(String.format(DEVE_SER_INFORMADO, name, end));
            }
        }
        for (int i = 0; i < typeValidEnums.length; i++) {
            errors = validNumberAfterNotNul(number, name, errors, max, min, typeValidEnums[i]);
        }
        return errors;
    }

    private static List<String> validNumberAfterNotNul(Number number, String name, List<String> errors, Double max,
            Double min, TypeValidEnum typeValidEnum) {
        switch (typeValidEnum) {
            case GREATER_THAN_ZERO:
                if (number.doubleValue() <= 0) {
                    errors.add(String.format(DEVE_SER_MAIOR_QUE_ZERO, name));
                }
                return errors;
            case GREATER_THAN_EQUAL_ZERO:
                if (number.doubleValue() < 0) {
                    errors.add(String.format(DEVE_SER_MAIOR_OU_IGUAL_ZERO, name));
                }
                return errors;
            case MAX:
                if (number.doubleValue() > max) {
                    errors.add(String.format(DEVE_SER_MENOR_OU_IGUAL, name, max));
                }
                return errors;
            case MIN:
                if (number.doubleValue() < min) {
                    errors.add(String.format(DEVE_SER_MAIOR_OU_IGUAL, name, min));
                }
                return errors;
            default:
                return errors;
        }
    }

    private static void treatErrors(List<String> errors) throws ValidException {
        if (errors.size() > 0) {
            throw new ValidException(errors);
        }
    }

    public static List<String> validString(String text, String name, char end, List<String> errors) {
        return validString(text, name, end, errors, 0, TypeValidEnum.NOT_NULL);
    }

    public static List<String> validString(String text, String name, char end, List<String> errors, Integer qtdChar) {
        return validString(text, name, end, errors, qtdChar, TypeValidEnum.NOT_NULL, TypeValidEnum.COUNT);
    }

    public static List<String> validString(String text, String name, List<String> errors, Integer qtdChar) {
        return validString(text, name, 'o', errors, qtdChar, TypeValidEnum.COUNT);
    }

    public static void validString(String text, String name, char end) {
        treatErrors(validString(text, name, end, new LinkedList<>(), 0, TypeValidEnum.NOT_NULL));
    }

    public static void validString(String text, String name, char end, Integer qtdChar) {
        treatErrors(
                validString(text, name, end, new LinkedList<>(), qtdChar, TypeValidEnum.NOT_NULL, TypeValidEnum.COUNT));
    }

    public static void validString(String text, String name, Integer qtdChar) {
        treatErrors(validString(text, name, 'o', new LinkedList<>(), qtdChar, TypeValidEnum.COUNT));
    }

    public static List<String> validNotNull(Object object, String name, char end, List<String> errors) {
        if (object == null) {
            errors.add(String.format(DEVE_SER_INFORMADO, name, end));
        }
        return errors;
    }

    public static void validNotNull(Object object, String name, char end) {
        if (object == null) {
            throw new ValidException(String.format(DEVE_SER_INFORMADO, name, end));
        }
    }

    public static List<String> validNumberGreaterThanZero(Number number, String name, char end, List<String> errors) {
        return validNumber(number, name, end, errors, null, null, TypeValidEnum.NOT_NULL,
                TypeValidEnum.GREATER_THAN_ZERO);
    }

    public static List<String> validNumberGreaterThanEqualZero(Number number, String name, char end,
            List<String> errors) {
        return validNumber(number, name, end, errors, null, null, TypeValidEnum.NOT_NULL,
                TypeValidEnum.GREATER_THAN_EQUAL_ZERO);
    }

    public static List<String> validNumber(Number number, String name, char end, List<String> errors, Double max,
            Double min) {
        max = max != null ? max : Double.MAX_VALUE;
        min = min != null ? min : Double.MIN_VALUE;
        return validNumber(number, name, end, errors, max, min, TypeValidEnum.NOT_NULL, TypeValidEnum.MAX,
                TypeValidEnum.MIN);
    }

    public static void validNumberGreaterThanZero(Number number, String name, char end) {
        treatErrors(validNumber(number, name, end, new LinkedList<>(), null, null, TypeValidEnum.NOT_NULL,
                TypeValidEnum.GREATER_THAN_ZERO));
    }

    public static void validNumberGreaterThanEqualZero(Number number, String name, char end) {
        treatErrors(validNumber(number, name, end, new LinkedList<>(), null, null, TypeValidEnum.NOT_NULL,
                TypeValidEnum.GREATER_THAN_EQUAL_ZERO));
    }

    public static void validNumber(Number number, String name, char end, Double max, Double min) {
        max = max != null ? max : Double.MAX_VALUE;
        min = min != null ? min : Double.MIN_VALUE;
        treatErrors(validNumber(number, name, end, new LinkedList<>(), max, min, TypeValidEnum.NOT_NULL,
                TypeValidEnum.MAX, TypeValidEnum.MIN));
    }

    public static void validObjectAndId(Object objeto, String name, char end) throws Exception {
        List<String> errors = new LinkedList<>();
        errors = validObjectAndId(objeto, name, end, errors);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidException(errors.get(0));
        }
    }

    public static List<String> validObjectAndId(Object objeto, String name, char end, List<String> errors) {
        try {
            if (objeto == null) {
                errors.add(String.format(DEVE_SER_INFORMADO, name, end));
            } else if (objeto.getClass().getMethod("getId").invoke(objeto) == null) {
                errors.add(String.format(DEVE_SER_INFORMADO, name, end));
            } else if (Double.valueOf(objeto.getClass().getMethod("getId").invoke(objeto).toString()) <= 0) {
                errors.add(String.format(DEVE_SER_MAIOR_QUE_ZERO, name));
            }
        } catch (Exception e) {
            errors.add(String.format(DEVE_SER_INFORMADO, name, end));
        }
        return errors;
    }

    public static void validBoolean(boolean objeto, String name, char end) throws ValidException {
        if (!objeto) {
            throw new ValidException(String.format(DEVE_SER_INFORMADO, name, end));
        }
    }

    public static List<String> validBoolean(boolean objeto, String name, char end, List<String> errors) {
        if (!objeto) {
            errors.add(String.format(DEVE_SER_INFORMADO, name, end));
        }
        return errors;
    }

    public static List<String> validCollection(Collection<?> collection, String name, char end, List<String> errors) {
        if (CollectionUtils.isEmpty(collection)) {
            errors.add(String.format(UM_DEVE_SER_INFORMADO, end == 'o' ? "" : end, name, end));
        }
        return errors;
    }

    public static void validCollection(Collection<?> collection, String name, char end) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ValidException(String.format(UM_DEVE_SER_INFORMADO, end == 'o' ? "" : end, name, end));
        }
    }
}

enum TypeValidEnum {
    NOT_NULL, GREATER_THAN_ZERO, GREATER_THAN_EQUAL_ZERO, MAX, MIN, COUNT;

    public static boolean contains(TypeValidEnum typeValidEnum, TypeValidEnum[] typeValidEnums) {
        for (int i = 0; i < typeValidEnums.length; i++) {
            if (typeValidEnum.equals(typeValidEnums[i])) {
                return true;
            }
        }
        return false;
    }
}
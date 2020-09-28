package application.service;

import application.dto.ErrorResponseDto;
import application.dto.ResponseDto;
import application.dto.UserDtoWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * This class describes the public static methods of validation
 * of data fields of the object user. And returns the response
 * entity based on the results of the validation check
 *
 * @author Sergey Kadochnikov
 */

@Component
public class ValidatorUserForm {

    private UserService userService;


    @Autowired
    public ValidatorUserForm(UserService userService) {
        this.userService = userService;
    }

    public static ResponseDto validate(UserDtoWithRoles userDtoWithRoles) {
        List<String> errors = validateFields(userDtoWithRoles);
        if (!errors.isEmpty()) {
            ErrorResponseDto responseDto = new ErrorResponseDto(false,errors);
            return responseDto;
        } else {
            ResponseDto responseDto = new ResponseDto(true);
            return responseDto;
        }
    }

    private static List<String> validateFields(UserDtoWithRoles userDtoWithRoles) {
        List<String> errors = new ArrayList<>();
        if (isFieldNullOrEmpty(userDtoWithRoles.getLogin())) {
            errors.add("Login can not be empty");
        } if (isFieldNullOrEmpty(userDtoWithRoles.getName())){
            errors.add("Name can not be empty");
        } if (isFieldNullOrEmpty(userDtoWithRoles.getPassword())) {
            errors.add("Password can not be empty");
        }if (!validatePassword(userDtoWithRoles.getPassword())) {
            errors.add("The password must contain at least one digit and a capital letter");
        }
        return errors;
    }

    private static boolean isFieldNullOrEmpty(String field) {
        return field == null || field.equals("") ;
    }

    private static boolean validatePassword(String password) {
        boolean isValid = true;
        Pattern pattern = Pattern.compile("[A-Z]+");
        Pattern pattern1 = Pattern.compile("[0-9]+");
        Pattern[] patterns = {pattern,pattern1};
        for (Pattern p : patterns) {
            Matcher matcher = p.matcher(password);
            if (!matcher.find()) {
                isValid = false;
            }
        }
        return isValid;
    }
}


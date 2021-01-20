package com.markomilanovits.dataverseAssignment.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private static final String NAME_PATTERN = "^[a-zA-Z]*$";
    private static final String MAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,60}$";
    private static final String PHONE_PATTERN = "^((\\+)\\d{2}|(00)\\d{2})?\\d{10}$";
    private static final String COMPANY_PATTERN = "^[a-zA-Z0-9]*$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private static final int NAME_MIN_SIZE = 4;
    private static final int NAME_MAX_SIZE = 50;
    private static final int EMAIL_MIN_SIZE = 5;
    private static final int EMAIL_MAX_SIZE = 70;
    private static final int PHONE_MIN_SIZE = 10;
    private static final int PHONE_MAX_SIZE = 14;
    private static final int COMPANY_MIN_SIZE = 2;
    private static final int COMPANY_MAX_SIZE = 160;
    private static final int PASSWORD_MIN_SIZE = 8;
    private static final int PASSWORD_MAX_SIZE = 70;

    @Pattern(regexp = NAME_PATTERN, message = "{register.firstname.pattern.invalid}")
    @Size(min = NAME_MIN_SIZE, max = NAME_MAX_SIZE, message = "{register.firstname.size.invalid}")
    @NotEmpty(message = "{register.firstname.not.null}")
    private String firstName;

    @Pattern(regexp = NAME_PATTERN, message = "{register.lastname.pattern.invalid}")
    @Size(min = NAME_MIN_SIZE, max = NAME_MAX_SIZE, message = "{register.lastname.size.invalid}")
    @NotEmpty(message = "{register.lastname.not.null}")
    private String lastName;

    @Pattern(regexp = MAIL_PATTERN, message = "{register.email.pattern.invalid}")
    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE, message = "{register.email.size.invalid}")
    @NotEmpty(message = "{register.email.not.null}")
    private String email;

    @Pattern(regexp = PHONE_PATTERN, message = "{register.phone.pattern.invalid}")
    @Size(min = PHONE_MIN_SIZE, max = PHONE_MAX_SIZE, message = "{register.phone.size.invalid}")
    private String phone;

    @Pattern(regexp = COMPANY_PATTERN, message = "{register.company.pattern.invalid}")
    @Size(min = COMPANY_MIN_SIZE, max = COMPANY_MAX_SIZE, message = "{register.company.size.invalid}")
    private String company;

    @Pattern(regexp = PASSWORD_PATTERN, message = "{register.password.pattern.invalid}")
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "{register.password.size.invalid}")
    @NotEmpty(message = "{register.password.not.null}")
    private String password;

    private Long id;

}
